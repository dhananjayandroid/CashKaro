package com.dhananjay.cashkaro_poc.core.api.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.dhananjay.cashkaro_poc.core.api.commands.GetAllDetailsCommand;
import com.dhananjay.cashkaro_poc.core.api.commands.ServiceCommand;
import com.dhananjay.cashkaro_poc.core.api.exception.ResponseException;
import com.dhananjay.cashkaro_poc.core.api.helpers.AllDetailsHelper;
import com.dhananjay.cashkaro_poc.core.api.helpers.BaseHelper;
import com.dhananjay.cashkaro_poc.core.utils.ErrorUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This class extends {@link Service} and executes all commands in background
 *
 * @author Dhananjay Kumar
 */
public class APIService extends Service {

    public static final int ALL_DETAILS_HELPER = 1;

    private static final String TAG = APIService.class.getSimpleName();

    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private final BlockingQueue<Runnable> threadQueue;
    private IBinder binder = new ServiceBinder();

    private Map<String, ServiceCommand> serviceCommandMap = new HashMap<>();
    private ThreadPoolExecutor threadPool;

    private Map<Integer, BaseHelper> helpersMap;

    public APIService() {
        threadQueue = new LinkedBlockingQueue<>();
        helpersMap = new HashMap<>();

        initThreads();

        initHelpers();
        initCommands();
    }

    private void initThreads() {
        threadPool = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, threadQueue);
        threadPool.allowCoreThreadTimeOut(true);
    }

    private void initHelpers() {
        helpersMap.put(ALL_DETAILS_HELPER, new AllDetailsHelper(this));
    }

    private void initCommands() {
        registerLoadNotesCommand();
    }


    private void registerLoadNotesCommand() {
        AllDetailsHelper allDetailsHelper = (AllDetailsHelper) getHelper(ALL_DETAILS_HELPER);
        ServiceCommand getAllDetailsCommand = new GetAllDetailsCommand(this, allDetailsHelper,
                ServiceConsts.GET_ALL_DETAILS_SUCCESS_ACTION,
                ServiceConsts.GET_ALL_DETAILS_FAIL_ACTION);

        serviceCommandMap.put(ServiceConsts.GET_ALL_DETAILS_ACTION, getAllDetailsCommand);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        String action;
        if (intent != null && (action = intent.getAction()) != null) {
            Log.d(TAG, "service started with resultAction=" + action);
            ServiceCommand command = serviceCommandMap.get(action);
            if (command != null) {
                startAsync(command, intent);
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return binder;
    }

    public BaseHelper getHelper(int helperId) {
        return helpersMap.get(helperId);
    }

    private void startAsync(final ServiceCommand command, final Intent intent) {
        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    command.execute(intent.getExtras());
                } catch (ResponseException e) {
                    ErrorUtils.logError(e);
                } catch (Exception e) {
                    ErrorUtils.logError(e);
                }
            }
        });
    }


    public class ServiceBinder extends Binder {

        public APIService getService() {
            return APIService.this;
        }
    }

}
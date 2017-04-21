package com.dhananjay.cashkaro_poc.core.api.commands;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.dhananjay.cashkaro_poc.core.api.exception.ResponseException;
import com.dhananjay.cashkaro_poc.core.api.service.ServiceConsts;
import com.dhananjay.cashkaro_poc.core.utils.ErrorUtils;


/**
 * This class extends @{@link Command} and
 * execute specific command action
 *
 * @author Dhananjay Kumar
 */
public abstract class ServiceCommand implements Command {

    private final Context context;
    private final String successAction;
    private final String failAction;

    /**
     * Instantiates a new Service command.
     *
     * @param context       the context
     * @param successAction the success action
     * @param failAction    the fail action
     */
    public ServiceCommand(Context context, String successAction, String failAction) {
        this.context = context;
        this.successAction = successAction;
        this.failAction = failAction;
    }

    /**
     * Executes the command
     *
     * @param bundle the bundle
     * @throws Exception
     */
    public void execute(Bundle bundle) throws Exception {
        Bundle result;
        try {
            result = perform(bundle);
            sendResult(result, successAction);
        } catch (ResponseException e) {
            ErrorUtils.logError(e);
            bundle.putSerializable(ServiceConsts.EXTRA_ERROR, e);
            bundle.putInt(ServiceConsts.EXTRA_ERROR_CODE, e.getHttpStatusCode());
            bundle.putString(ServiceConsts.COMMAND_ACTION, failAction);
            sendResult(bundle, failAction);
            throw e;
        } catch (Exception e) {
            ErrorUtils.logError(e);
            bundle.putSerializable(ServiceConsts.EXTRA_ERROR, e);
            bundle.putString(ServiceConsts.COMMAND_ACTION, failAction);
            sendResult(bundle, failAction);
            throw e;
        }
    }

    /**
     * sends result after command excution
     *
     * @param result
     * @param action
     */
    private void sendResult(Bundle result, String action) {
        Intent intent = new Intent(action);
        if (null != result) {
            intent.putExtras(result);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /**
     * Perform action with the bundle provided.
     *
     * @param extras the extras
     * @return the bundle
     * @throws Exception the exception
     */
    protected abstract Bundle perform(Bundle extras) throws Exception;
}
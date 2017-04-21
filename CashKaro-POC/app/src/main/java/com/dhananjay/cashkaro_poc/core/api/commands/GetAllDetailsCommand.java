package com.dhananjay.cashkaro_poc.core.api.commands;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dhananjay.cashkaro_poc.core.api.helpers.AllDetailsHelper;
import com.dhananjay.cashkaro_poc.core.api.service.APIService;
import com.dhananjay.cashkaro_poc.core.api.service.ServiceConsts;

/**
 * This class extends @{@link ServiceCommand} and
 * execute command to provide all the necessary details
 *
 * @author Dhananjay Kumar
 */
public class GetAllDetailsCommand extends ServiceCommand {

    private AllDetailsHelper allDetailsHelper;


    /**
     * Instantiates a new Get all details command.
     *
     * @param context          the context
     * @param allDetailsHelper the all details helper
     * @param successAction    the success action
     * @param failAction       the fail action
     */
    public GetAllDetailsCommand(Context context, AllDetailsHelper allDetailsHelper, String successAction,
                                String failAction) {
        super(context, successAction, failAction);
        this.allDetailsHelper = allDetailsHelper;
    }

    /**
     * Starts the command
     *
     * @param context the context
     */
    public static void start(Context context) {
        Intent intent = new Intent(ServiceConsts.GET_ALL_DETAILS_ACTION, null, context, APIService.class);
        context.startService(intent);
    }

    @Override
    public Bundle perform(Bundle extras) throws Exception {
        return allDetailsHelper.getAllDetails(extras);
    }
}
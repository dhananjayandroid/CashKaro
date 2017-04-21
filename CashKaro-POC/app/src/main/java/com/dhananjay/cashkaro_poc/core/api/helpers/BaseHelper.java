package com.dhananjay.cashkaro_poc.core.api.helpers;

import android.content.Context;

/**
 * Abstract class extends for other helper classes
 *
 * @author Dhananjay Kumar
 */
public abstract class BaseHelper {

    /**
     * The Context.
     */
    protected Context context;

    /**
     * Instantiates a new Base helper.
     *
     * @param context the context
     */
    BaseHelper(Context context) {
        this.context = context;
    }
}
package com.dhananjay.cashkaro_poc.core.api.commands;

import android.os.Bundle;

/**
 * Interface for Command
 *
 * @author Dhananjay Kumar
 */
public interface Command {

    /**
     * To execute action passed into command
     *
     * @param bundle the bundle
     * @throws Exception the exception
     */
    void execute(Bundle bundle) throws Exception;
}
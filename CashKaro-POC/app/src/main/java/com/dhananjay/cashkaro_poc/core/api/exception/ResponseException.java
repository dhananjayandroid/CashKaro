package com.dhananjay.cashkaro_poc.core.api.exception;


import com.dhananjay.cashkaro_poc.core.api.helpers.ToStringHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class extends @{@link IOException}
 *
 * @author Dhananjay Kumar
 */
public class ResponseException extends IOException {
    private List<String> errors;
    private int httpStatusCode;

    /**
     * Instantiates a new Response exception.
     *
     * @param msg the msg
     */
    public ResponseException(String msg) {
        super(msg);
        this.errors = new ArrayList(1);
        this.errors.add(msg);
        this.httpStatusCode = -1;
    }

    /**
     * Instantiates a new Response exception.
     *
     * @param statusCode the status code
     * @param errors     the errors
     */
    public ResponseException(int statusCode, List<String> errors) {
        super(ToStringHelper.toString(errors, ","));
        this.errors = errors;
        this.httpStatusCode = statusCode;
    }

    /**
     * Instantiates a new Response exception.
     *
     * @param errors the errors
     */
    public ResponseException(List<String> errors) {
        this(-1, errors);
    }

    /**
     * Gets errors.
     *
     * @return the errors
     */
    public List<String> getErrors() {
        return this.errors;
    }

    /**
     * Gets http status code.
     *
     * @return the http status code
     */
    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }
}

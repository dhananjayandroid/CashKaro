package com.dhananjay.cashkaro_poc.core.api.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Model class extends {@link Serializable} acts as a model class for api calls
 *
 * @author Dhananjay Kumar
 */
public class JsonResponse implements Serializable {
    @SerializedName("userid")
    private String userId;
    @SerializedName("user")
    private String user;
    @SerializedName("status")
    private String status;
    @SerializedName("reason")
    private String reason;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

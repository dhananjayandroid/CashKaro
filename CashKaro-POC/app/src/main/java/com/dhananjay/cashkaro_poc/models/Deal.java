package com.dhananjay.cashkaro_poc.models;

/**
 * Model class for Deal
 *
 * @author Dhananjay Kumar
 */
public class Deal {
    public Deal(int dealId, int dealIconDrawable, boolean isFavorite, String dealDescription, String dealExtra, int offersCount) {
        this.dealId = dealId;
        this.dealIconDrawable = dealIconDrawable;
        this.isFavorite = isFavorite;
        this.dealDescription = dealDescription;
        this.dealExtra = dealExtra;
        this.offersCount = offersCount;
    }

    private int dealId;
    private int dealIconDrawable;
    private boolean isFavorite;
    private String dealDescription;
    private String dealExtra;
    private int offersCount;

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public int getDealIconDrawable() {
        return dealIconDrawable;
    }

    public void setDealIconDrawable(int dealIconDrawable) {
        this.dealIconDrawable = dealIconDrawable;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getDealDescription() {
        return dealDescription;
    }

    public void setDealDescription(String dealDescription) {
        this.dealDescription = dealDescription;
    }

    public String getDealExtra() {
        return dealExtra;
    }

    public void setDealExtra(String dealExtra) {
        this.dealExtra = dealExtra;
    }

    public int getOffersCount() {
        return offersCount;
    }

    public void setOffersCount(int offersCount) {
        this.offersCount = offersCount;
    }
}

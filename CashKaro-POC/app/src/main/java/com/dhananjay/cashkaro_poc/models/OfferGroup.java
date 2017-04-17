package com.dhananjay.cashkaro_poc.models;

import java.util.ArrayList;

/**
 * Created by Dhananjay on 17-04-2017.
 */
public class OfferGroup {
    public OfferGroup(ArrayList<Deal> deals, int offerGroupId, String offerTitle) {
        this.deals = deals;
        this.offerGroupId = offerGroupId;
        this.offerTitle = offerTitle;
    }

    private ArrayList<Deal> deals;
    private int offerGroupId;
    private String offerTitle;

    public OfferGroup(ArrayList<Deal> deals) {
        this.deals = deals;
    }

    public int getOfferGroupId() {
        return offerGroupId;
    }

    public void setOfferGroupId(int offerGroupId) {
        this.offerGroupId = offerGroupId;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public ArrayList<Deal> getDeals() {
        return deals;
    }

    public void setDeals(ArrayList<Deal> deals) {
        this.deals = deals;
    }
}

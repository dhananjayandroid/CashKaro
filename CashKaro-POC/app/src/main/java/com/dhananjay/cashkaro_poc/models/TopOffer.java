package com.dhananjay.cashkaro_poc.models;

import java.io.Serializable;

/**
 * Created by Dhananjay on 17-04-2017.
 */
public class TopOffer implements Serializable{
    private int offerId;
    private String offerLink;
    private String imageLink;
    private int drawableId;


    public TopOffer(int offerId, String offerLink, int drwableId) {
        this.offerId = offerId;
        this.offerLink = offerLink;
        this.drawableId = drwableId;
    }

    public String getOfferLink() {
        return offerLink;
    }

    public void setOfferLink(String offerLink) {
        this.offerLink = offerLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}

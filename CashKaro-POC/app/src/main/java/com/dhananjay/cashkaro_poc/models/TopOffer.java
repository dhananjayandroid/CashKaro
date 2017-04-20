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
    private String offerSite;
    private int siteLogoDrawable;

    public TopOffer(int offerId, String offerLink, int drawableId, String offerSite, int siteLogoDrawable) {
        this.offerId = offerId;
        this.offerLink = offerLink;
        this.drawableId = drawableId;
        this.offerSite = offerSite;
        this.siteLogoDrawable = siteLogoDrawable;
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

    public String getOfferSite() {
        return offerSite;
    }

    public void setOfferSite(String offerSite) {
        this.offerSite = offerSite;
    }

    public int getSiteLogoDrawable() {
        return siteLogoDrawable;
    }

    public void setSiteLogoDrawable(int siteLogoDrawable) {
        this.siteLogoDrawable = siteLogoDrawable;
    }
}

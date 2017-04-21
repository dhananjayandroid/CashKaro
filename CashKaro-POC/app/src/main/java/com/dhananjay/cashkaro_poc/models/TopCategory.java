package com.dhananjay.cashkaro_poc.models;

import java.io.Serializable;

/**
 * Model class for TopCategory
 *
 * @author Dhananjay Kumar
 */
public class TopCategory implements Serializable{
    private int categoryId;
    private String categoryName;
    private String imageLink;
    private int drawableId;


    public TopCategory(int categoryId, String categoryName, int drawableId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.drawableId = drawableId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

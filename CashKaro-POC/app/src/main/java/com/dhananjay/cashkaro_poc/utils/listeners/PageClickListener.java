package com.dhananjay.cashkaro_poc.utils.listeners;

import android.view.View;

import com.dhananjay.cashkaro_poc.models.TopOffer;

/**
 * Interface for PageClick
 *
 * @author Dhananjay Kumar
 */
public interface PageClickListener {
    void onPageClick(View view, TopOffer topOffer);
}
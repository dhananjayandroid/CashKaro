package com.dhananjay.cashkaro_poc.utils;

import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.models.Deal;
import com.dhananjay.cashkaro_poc.models.OfferGroup;
import com.dhananjay.cashkaro_poc.models.TopCategory;
import com.dhananjay.cashkaro_poc.models.TopOffer;

import java.util.ArrayList;

/**
 * Created by Dhananjay on 17-04-2017.
 */
public class DemoDataCreator {

    public static ArrayList<TopOffer> createDemoTopOffers() {
        ArrayList<TopOffer> topOffers = new ArrayList<>();
        TopOffer topOffer_1 = new TopOffer(1, "http://www.flipkart.com", R.drawable.flipkart_top_offer);
        topOffers.add(topOffer_1);
        TopOffer topOffer_2 = new TopOffer(2, "http://www.amazon.in", R.drawable.amazon_top_offer);
        topOffers.add(topOffer_2);
        TopOffer topOffer_3 = new TopOffer(3, "http://www.jabong.com", R.drawable.jabong_top_offer);
        topOffers.add(topOffer_3);
        TopOffer topOffer_4 = new TopOffer(4, "http://www.nykaa.com", R.drawable.nykaa_top_offer);
        topOffers.add(topOffer_4);
        TopOffer topOffer_5 = new TopOffer(5, "http://www.myntra.com", R.drawable.myntra_top_offer);
        topOffers.add(topOffer_5);
        TopOffer topOffer_6 = new TopOffer(6, "http://www.shopclues.com", R.drawable.shopclues_top_offer);
        topOffers.add(topOffer_6);
        return topOffers;
    }

    public static ArrayList<OfferGroup> createDemoOffers() {
        ArrayList<OfferGroup> offerGroups = new ArrayList<>();
        ArrayList<Deal> deals_1 = new ArrayList<>();
        Deal deal_1 = new Deal(1, R.drawable.deal_amazon, false, "Upto 70% off all the books and stationery on Amazon", "+ Upto 7.5% Extra Cashback", 25);
        deals_1.add(deal_1);
        Deal deal_2 = new Deal(2, R.drawable.deal_snapdeal, false, "Upto 70% off all the books and stationery on Snapdeal", "+ Upto 7.5% Extra Cashback", 25);
        deals_1.add(deal_2);
        Deal deal_3 = new Deal(3, R.drawable.deal_myntra, false, "Upto 70% off all the books and stationery on Myntra", "+ Upto 7.5% Extra Cashback", 25);
        deals_1.add(deal_3);
        OfferGroup offerGroup = new OfferGroup(deals_1, 1, "Office Stationery Deals");
        offerGroups.add(offerGroup);

        ArrayList<Deal> deals_2 = new ArrayList<>();
        Deal deal_4 = new Deal(1, R.drawable.deal_amazon, false, "Upto 20% off Mobile cases & Covers on Amazon", "+ Upto 7.5% Extra Cashback", 25);
        deals_2.add(deal_4);
        Deal deal_5 = new Deal(2, R.drawable.deal_snapdeal, false, "Upto 20% off Mobile cases & Covers on Snapdeal", "+ Upto 7.5% Extra Cashback", 25);
        deals_2.add(deal_5);
        Deal deal_6 = new Deal(3, R.drawable.deal_myntra, false, "Upto 20% off Mobile cases & Covers on Myntra", "+ Upto 7.5% Extra Cashback", 25);
        deals_2.add(deal_6);
        OfferGroup offerGroup_2 = new OfferGroup(deals_2, 2, "Mobile Accessories under ₹500");
        offerGroups.add(offerGroup_2);

        ArrayList<Deal> deals_3 = new ArrayList<>();
        Deal deal_7 = new Deal(1, R.drawable.deal_amazon, false, "Upto 50% Pressure Cooker on Amazon", "+ Upto 7.5% Extra Cashback", 25);
        deals_3.add(deal_7);
        Deal deal_8 = new Deal(2, R.drawable.deal_snapdeal, false, "Upto 50% Pressure Cooker on Amazon", "+ Upto 7.5% Extra Cashback", 25);
        deals_3.add(deal_8);
        Deal deal_9 = new Deal(3, R.drawable.deal_myntra, false, "Upto 50% Pressure Cooker on Amazon", "+ Upto 7.5% Extra Cashback", 25);
        deals_3.add(deal_9);
        OfferGroup offerGroup_3 = new OfferGroup(deals_3, 3, "Home & Kitchen");
        offerGroups.add(offerGroup_3);

        return offerGroups;
    }

    public static ArrayList<TopCategory> createDemoTopCategories() {
        ArrayList<TopCategory> topCategories = new ArrayList<>();
        TopCategory topCategory_1 = new TopCategory(1, "Clothing", R.drawable.cloth);
        topCategories.add(topCategory_1);
        TopCategory topCategory_2 = new TopCategory(2, "Electronics", R.drawable.electronics);
        topCategories.add(topCategory_2);
        TopCategory topCategory_3 = new TopCategory(3, "Fashion", R.drawable.fashion);
        topCategories.add(topCategory_3);
        TopCategory topCategory_4 = new TopCategory(4, "Recharge", R.drawable.recharge);
        topCategories.add(topCategory_4);
        TopCategory topCategory_5 = new TopCategory(5, "Travel", R.drawable.travel);
        topCategories.add(topCategory_5);
        TopCategory topCategory_6 = new TopCategory(6, "Grocery", R.drawable.grocery);
        topCategories.add(topCategory_6);
        return topCategories;
    }
}

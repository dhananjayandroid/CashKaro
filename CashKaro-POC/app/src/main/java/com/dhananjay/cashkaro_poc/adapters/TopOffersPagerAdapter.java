package com.dhananjay.cashkaro_poc.adapters;

/**
 * Created by Dhananjay on 17-04-2017.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.models.TopOffer;

import java.util.ArrayList;

public class TopOffersPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<TopOffer> topOffers;
    private LayoutInflater layoutInflater;
    ;

    public TopOffersPagerAdapter(Context context, ArrayList<TopOffer> topOffers) {
        mContext = context;
        this.topOffers = topOffers;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TopOffer topOffer = topOffers.get(position);
        View itemView = layoutInflater.inflate(R.layout.item_top_offer, container, false);
        ImageView ivTopOffer = (ImageView) itemView.findViewById(R.id.iv_top_offer);
        ivTopOffer.setImageResource(topOffer.getDrawableId());
        container.addView(itemView);
        return itemView;
    }


    @Override
    public int getCount() {
        return topOffers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
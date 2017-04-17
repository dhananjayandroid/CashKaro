package com.dhananjay.cashkaro_poc;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dhananjay.cashkaro_poc.adapters.OffersGroupAdapter;
import com.dhananjay.cashkaro_poc.adapters.TopOffersPagerAdapter;
import com.dhananjay.cashkaro_poc.models.OfferGroup;
import com.dhananjay.cashkaro_poc.models.TopOffer;
import com.dhananjay.cashkaro_poc.utils.DemoDataCreator;

import java.util.ArrayList;

/**
 * Created by Dhananjay on 17-04-2017.
 */
public class HomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private RecyclerView rvDealGroup;
    private AutoScrollViewPager vpTopOffers;
    private GridView gvTopCategories;
    private ArrayList<TopOffer> topOffers;
    private ArrayList<OfferGroup> offerGroups;
    private ImageView[] dots;
    private int dotsCount;
    private TopOffersPagerAdapter topOffersPagerAdapter;
    private LinearLayout pager_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);

        initViews();
        initFields();
    }

    private void initFields() {
        topOffers = DemoDataCreator.createDemoTopOffers();
        offerGroups = DemoDataCreator.createDemoOffers();
        topOffersPagerAdapter = new TopOffersPagerAdapter(this, topOffers);
        vpTopOffers.setAdapter(topOffersPagerAdapter);
        vpTopOffers.startAutoScroll();
        vpTopOffers.addOnPageChangeListener(this);
        setUiPageViewController();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvDealGroup.setLayoutManager(mLayoutManager);
        rvDealGroup.setItemAnimator(new DefaultItemAnimator());
//        rvDealGroup.setNestedScrollingEnabled(false);
        rvDealGroup.setAdapter(new OffersGroupAdapter(offerGroups));
    }

    private void setUiPageViewController() {
        dotsCount = topOffersPagerAdapter.getCount();
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.non_selected_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(4, 0, 4, 0);
            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageResource(R.drawable.selected_dot);
    }


    private void initViews() {
        vpTopOffers = (AutoScrollViewPager) findViewById(R.id.vp_top_offers);
        rvDealGroup = (RecyclerView) findViewById(R.id.rv_deal_group);
        gvTopCategories = (GridView) findViewById(R.id.gv_top_categories);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageResource(R.drawable.non_selected_dot);
        }
        dots[position].setImageResource(R.drawable.selected_dot);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
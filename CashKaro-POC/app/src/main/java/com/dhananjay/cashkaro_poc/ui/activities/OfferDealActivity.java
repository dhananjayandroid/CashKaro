package com.dhananjay.cashkaro_poc.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.adapters.OfferDealAdapter;
import com.dhananjay.cashkaro_poc.models.Deal;
import com.dhananjay.cashkaro_poc.models.TopOffer;
import com.dhananjay.cashkaro_poc.utils.AppConstants;
import com.dhananjay.cashkaro_poc.utils.CommonUtils;
import com.dhananjay.cashkaro_poc.utils.listeners.RecyclerViewItemClickListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

/**
 * Activity class for OfferDeals extends {@link BaseActivity}
 *
 * @author Dhananjay Kumar
 */
public class OfferDealActivity extends BaseActivity implements View.OnClickListener, RecyclerViewItemClickListener {
    private TopOffer topOffer;
    private RecyclerView rvDeals;
    private TextView tvCashBackDesc, tvTopDeal;
    private LinearLayout rlCashBack;
    private Button btnShopNow;
    private ImageView ivLogo, ivCashBack;
    private NestedScrollView nsContent;

    /**
     * Start OfferDealActivity.
     *
     * @param context  the context
     * @param topOffer the top offer
     */
    public static void start(Context context, TopOffer topOffer) {
        Intent intent = new Intent(context, OfferDealActivity.class);
        intent.putExtra(AppConstants.EXTRA_OFFER, topOffer);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_deal);
        initViews();
        initFields();
        setUpActionBarWithUpButton();
        initListeners();
    }

    /**
     * Initiate all listeners
     */
    private void initListeners() {
        rlCashBack.setOnClickListener(this);
        btnShopNow.setOnClickListener(this);
    }

    /**
     * initiate all view elements
     */
    private void initViews() {
        rvDeals = (RecyclerView) findViewById(R.id.rv_deals);
        tvCashBackDesc = (TextView) findViewById(R.id.tv_cash_back_desc);
        tvTopDeal = (TextView) findViewById(R.id.tv_top_deal);
        rlCashBack = (LinearLayout) findViewById(R.id.ll_cash_back);
        ivCashBack = (ImageView) findViewById(R.id.iv_cash_back);
        btnShopNow = (Button) findViewById(R.id.btn_shop_now);
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        nsContent = (NestedScrollView) findViewById(R.id.ns_content);
    }

    /**
     * init Fields
     */
    private void initFields() {
        topOffer = (TopOffer) getIntent().getSerializableExtra(AppConstants.EXTRA_OFFER);
        rvDeals.setLayoutManager(new LinearLayoutManager(this));
        rvDeals.setItemAnimator(new DefaultItemAnimator());
        OfferDealAdapter offerDealAdapter = new OfferDealAdapter(new ArrayList<Deal>());
        rvDeals.setAdapter(offerDealAdapter);
        offerDealAdapter.setClickListener(this);
        title = topOffer.getOfferSite();
        ivLogo.setImageResource(topOffer.getSiteLogoDrawable());
        tvTopDeal.setText(String.format(getResources().getString(R.string.top_deal_txt), topOffer.getOfferSite()));
        scrollToTop();
    }

    /**
     * scroll screen to top
     */
    private void scrollToTop() {
        nsContent.post(new Runnable() {
            @Override
            public void run() {
                nsContent.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    /**
     * Records CashBack selection
     */
    private void recordCashBackSeen() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "" + topOffer.getOfferId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, topOffer.getOfferSite());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "cashBack");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    /**
     * Expand Cashback view
     *
     * @param v View
     */
    private void expand(final View v) {
        recordCashBackSeen();
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 1;
        ivCashBack.setImageResource(R.drawable.close_arrow);
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((long) (10 * (targetHeight / v.getContext().getResources().getDisplayMetrics().density)));
        v.startAnimation(a);
    }

    /**
     * Collapse Cashback view
     *
     * @param v View
     */
    private void collapse(final View v) {
        ivCashBack.setImageResource(R.drawable.open_arrow);
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 0.5dp/ms
        a.setDuration((long) (10 * (initialHeight / v.getContext().getResources().getDisplayMetrics().density)));
        v.startAnimation(a);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_cash_back:
                if (tvCashBackDesc.getVisibility() == View.GONE)
                    expand(tvCashBackDesc);
                else
                    collapse(tvCashBackDesc);
                break;
            case R.id.btn_shop_now:
                performShopClick();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        recordScreenView(title + "-deal", OfferDealActivity.class.getSimpleName());
    }

    /**
     * Record Offer click
     */
    private void recordOfferClick() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "" + topOffer.getOfferId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, topOffer.getOfferSite());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "offer");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    public void onClick(View view, int position) {
        performShopClick();
    }

    /**
     * Perform shop now/Get code click
     */
    private void performShopClick() {
        CommonUtils.sendNotification(this, topOffer.getOfferSite());
        recordOfferClick();
        DealLinkActivity.start(this, topOffer);
    }
}

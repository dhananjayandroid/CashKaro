package com.dhananjay.cashkaro_poc.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dhananjay.cashkaro_poc.AutoScrollViewPager;
import com.dhananjay.cashkaro_poc.FullHeightGridView;
import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.adapters.OffersGroupAdapter;
import com.dhananjay.cashkaro_poc.adapters.TopCategoriesGridAdapter;
import com.dhananjay.cashkaro_poc.adapters.TopOffersPagerAdapter;
import com.dhananjay.cashkaro_poc.core.api.commands.Command;
import com.dhananjay.cashkaro_poc.core.api.commands.GetAllDetailsCommand;
import com.dhananjay.cashkaro_poc.core.api.service.ServiceConsts;
import com.dhananjay.cashkaro_poc.models.OfferGroup;
import com.dhananjay.cashkaro_poc.models.TopCategory;
import com.dhananjay.cashkaro_poc.models.TopOffer;
import com.dhananjay.cashkaro_poc.ui.fragments.dialogs.OneButtonDialogFragment;
import com.dhananjay.cashkaro_poc.utils.AppConstants;
import com.dhananjay.cashkaro_poc.utils.listeners.PageClickListener;

import java.util.ArrayList;

/**
 * Activity class for Home screen extends {@link BaseActivity}
 *
 * @author Dhananjay Kumar
 */
public class HomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener, PageClickListener {

    private RecyclerView rvDealGroup;
    private AutoScrollViewPager vpTopOffers;
    private FullHeightGridView gvTopCategories;
    private ArrayList<TopOffer> topOffers = new ArrayList<>();
    private ArrayList<OfferGroup> offerGroups = new ArrayList<>();
    private ArrayList<TopCategory> topCategories = new ArrayList<>();
    private ImageView[] dots;
    private int dotsCount;
    private TopOffersPagerAdapter topOffersPagerAdapter;
    private LinearLayout pager_indicator;
    /**
     * The Local broadcast manager.
     */
    protected LocalBroadcastManager localBroadcastManager;
    private BroadcastReceiver userLoggedInBroadCastReceiver;
    private GetDetailsSuccessAction getDetailsSuccessAction;
    private GetDetailsFailAction getDetailsFailAction;
    private OffersGroupAdapter offersGroupAdapter;
    private TopCategoriesGridAdapter topCategoriesGridAdapter;

    /**
     * Start HomeActivity.
     *
     * @param context the context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        initFields();
        setUpActionBarWithDrawer();
        initListeners();
        registerBroadcastReceivers();
        addActions();
    }

    /**
     * Add actions
     */
    private void addActions() {
        addAction(ServiceConsts.GET_ALL_DETAILS_SUCCESS_ACTION, getDetailsSuccessAction);
        addAction(ServiceConsts.GET_ALL_DETAILS_FAIL_ACTION, getDetailsFailAction);
        updateBroadcastActionList();
    }

    /**
     * Remove actions
     */
    private void removeActions() {
        removeAction(ServiceConsts.GET_ALL_DETAILS_SUCCESS_ACTION);
        removeAction(ServiceConsts.GET_ALL_DETAILS_FAIL_ACTION);
        updateBroadcastActionList();
    }

    /**
     * Initiate all listeners
     */
    private void initListeners() {
        vpTopOffers.addOnPageChangeListener(this);
        topOffersPagerAdapter.setClickListener(this);
    }

    /**
     * init Fields
     */
    private void initFields() {
        getDetailsSuccessAction = new GetDetailsSuccessAction();
        getDetailsFailAction = new GetDetailsFailAction();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        userLoggedInBroadCastReceiver = new UserLoggedInBroadCastReceiver();
//        topOffers = DemoDataCreatorUtils.createDemoTopOffers();
//        offerGroups = DemoDataCreatorUtils.createDemoOffers();
//        topCategories = DemoDataCreatorUtils.createDemoTopCategories();
        topOffersPagerAdapter = new TopOffersPagerAdapter(this, topOffers);
        vpTopOffers.setAdapter(topOffersPagerAdapter);
//        vpTopOffers.startAutoScroll();
//        setUiPageViewController();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvDealGroup.setLayoutManager(mLayoutManager);
        rvDealGroup.setItemAnimator(new DefaultItemAnimator());
        offersGroupAdapter = new OffersGroupAdapter(offerGroups);
        rvDealGroup.setAdapter(offersGroupAdapter);
        topCategoriesGridAdapter = new TopCategoriesGridAdapter(topCategories);
        gvTopCategories.setAdapter(topCategoriesGridAdapter);

        showProgress();
        GetAllDetailsCommand.start(this);
    }

    /**
     * Set up viewpager dots
     */
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

    /**
     * initiate all view elements
     */
    private void initViews() {
        vpTopOffers = (AutoScrollViewPager) findViewById(R.id.vp_top_offers);
        rvDealGroup = (RecyclerView) findViewById(R.id.rv_deal_group);
        gvTopCategories = (FullHeightGridView) findViewById(R.id.gv_top_categories);
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

    @Override
    public void onPageClick(View view, TopOffer topOffer) {
        OfferDealActivity.start(this, topOffer);
    }

    @Override
    public void onResume() {
        super.onResume();
        recordScreenView("home-screen", HomeActivity.class.getSimpleName());
    }


    private void registerBroadcastReceivers() {
        localBroadcastManager.registerReceiver(userLoggedInBroadCastReceiver,
                new IntentFilter(AppConstants.REFRESH_USER_DETAILS));
    }

    private void unregisterBroadcastReceivers() {
        localBroadcastManager.unregisterReceiver(userLoggedInBroadCastReceiver);
    }

    private class UserLoggedInBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppConstants.REFRESH_USER_DETAILS)) {
                refreshUserData();
            }
        }
    }

    /**
     * Refresh user data
     */
    private void refreshUserData() {
        setUpActionBarWithDrawer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcastReceivers();
        removeActions();
    }

    /**
     * Get details success action.
     */
    protected class GetDetailsSuccessAction implements Command {

        @Override
        public void execute(Bundle bundle) {
            topOffers = (ArrayList<TopOffer>) bundle.getSerializable(ServiceConsts.EXTRA_TOP_OFFERS);
            offerGroups = (ArrayList<OfferGroup>) bundle.getSerializable(ServiceConsts.EXTRA_OFFERS);
            topCategories = (ArrayList<TopCategory>) bundle.getSerializable(ServiceConsts.EXTRA_TOP_CATEGORY);
            updateData();
            hideProgress();
        }
    }

    /**
     * Get details fail action.
     */
    protected class GetDetailsFailAction implements Command {
        @Override
        public void execute(Bundle bundle) {
            hideProgress();
            Exception exception = (Exception) bundle.getSerializable(ServiceConsts.EXTRA_ERROR);
            if (exception != null) {
                OneButtonDialogFragment.show(getSupportFragmentManager(), getString(R.string.error), exception.getMessage(), true, null);
            }
        }
    }

    /**
     * Update on screen data
     */
    private void updateData() {
        topCategoriesGridAdapter.updateContent(topCategories);
        topOffersPagerAdapter.updateContent(topOffers);
        offersGroupAdapter.updateContent(offerGroups);
        vpTopOffers.startAutoScroll();
        setUiPageViewController();
    }


}

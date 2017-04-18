package com.dhananjay.cashkaro_poc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dhananjay.cashkaro_poc.adapters.OffersGroupAdapter;
import com.dhananjay.cashkaro_poc.adapters.TopCategoriesGridAdapter;
import com.dhananjay.cashkaro_poc.adapters.TopOffersPagerAdapter;
import com.dhananjay.cashkaro_poc.models.OfferGroup;
import com.dhananjay.cashkaro_poc.models.TopCategory;
import com.dhananjay.cashkaro_poc.models.TopOffer;
import com.dhananjay.cashkaro_poc.utils.AppConstants;
import com.dhananjay.cashkaro_poc.utils.DemoDataCreator;

import java.util.ArrayList;

/**
 * Created by Dhananjay on 17-04-2017.
 */
public class OfferActivity extends AppCompatActivity {
    private TopOffer topOffer;

    public static void start(Context context, TopOffer topOffer) {
        Intent intent = new Intent(context, OfferActivity.class);
        intent.putExtra(AppConstants.EXTRA_OFFER, topOffer);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);
        initFields();
    }

    private void initFields() {
        topOffer = (TopOffer) getIntent().getSerializableExtra(AppConstants.EXTRA_OFFER);
    }

}

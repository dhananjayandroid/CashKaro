package com.dhananjay.cashkaro_poc.ui.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.models.TopOffer;
import com.dhananjay.cashkaro_poc.utils.AppConstants;

/**
 * Created by Dhananjay on 17-04-2017.
 */
public class DealLinkActivity extends BaseActivity {
    private WebView wvDeal;
    private TopOffer topOffer;

    public static void start(Context context, TopOffer topOffer) {
        Intent intent = new Intent(context, DealLinkActivity.class);
        intent.putExtra(AppConstants.EXTRA_OFFER, topOffer);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_link);
        initViews();
        initFields();
        setUpActionBarWithUpButton();

        initFields();
    }

    private void initViews() {
        wvDeal = (WebView) findViewById(R.id.wv_deal);
        topOffer = (TopOffer) getIntent().getSerializableExtra(AppConstants.EXTRA_OFFER);
        title = topOffer.getOfferSite();
    }

    private void initFields() {
        wvDeal.setWebViewClient(new MyWebClient());
        wvDeal.getSettings().setJavaScriptEnabled(true);
        wvDeal.loadUrl(topOffer.getOfferLink());
        wvDeal.measure(100, 100);
        wvDeal.getSettings().setUseWideViewPort(true);
        wvDeal.getSettings().setLoadWithOverviewMode(true);
    }

    private class MyWebClient extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wvDeal.canGoBack()) {
                        wvDeal.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}

package com.wizimatic.appwebber.utility;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.wizimatic.appwebber.R;

/**
 * Created by Ashiq on 4/13/2017.
 */

public class AdUtils {

    private static AdUtils adUtils;

    private InterstitialAd mInterstitialAd;

    private boolean disableBannerAd = false, disableInterstitialAd = false;

    private AdUtils(Context context) {
                context.getResources().getString(R.string.app_ad_id);
    }

    public static AdUtils getInstance(Context context) {
        if (adUtils == null) {
            adUtils = new AdUtils(context);
        }
        return adUtils;
    }

    @SuppressLint("MissingPermission")
    public void showBannerAd(final AdView mAdView) {
        if (disableBannerAd) {
            mAdView.setVisibility(View.GONE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mAdView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    super.onAdFailedToLoad(errorCode);
                    mAdView.setVisibility(View.GONE);
                }
            });
        }
    }

    public void loadFullScreenAd(AppCompatActivity activity) {
        if (!disableInterstitialAd) {
            mInterstitialAd = new InterstitialAd(activity) {
                @NonNull
                @Override
                public String getAdUnitId() {
                    return null;
                }

                @Override
                public void show(@NonNull Activity activity) {

                }

                @Override
                public void setFullScreenContentCallback(@Nullable FullScreenContentCallback fullScreenContentCallback) {

                }

                @Nullable
                @Override
                public FullScreenContentCallback getFullScreenContentCallback() {
                    return null;
                }

                @Override
                public void setImmersiveMode(boolean b) {

                }

                @NonNull
                @Override
                public ResponseInfo getResponseInfo() {
                    return null;
                }

                @Override
                public void setOnPaidEventListener(@Nullable OnPaidEventListener onPaidEventListener) {

                }

                @Nullable
                @Override
                public OnPaidEventListener getOnPaidEventListener() {
                    return null;
                }
            };
            mInterstitialAd.getAdUnitId(activity.getResources().getString(R.string.interstitial_ad_unit_id));

            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }
    }

    public boolean showFullScreenAd() {
        if (!disableInterstitialAd) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                return true;
            }
        }
        return false;
    }

    public InterstitialAd getInterstitialAd() {
        return mInterstitialAd;
    }

    public void disableBannerAd() {
        this.disableBannerAd = true;
    }

    public void disableInterstitialAd() {
        this.disableInterstitialAd = true;
    }


}

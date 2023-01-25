package com.wizimatic.appwebber.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.utility.ActivityUtils;
import com.wizimatic.appwebber.utility.AppUtils;

public class SplashActivity extends AppCompatActivity {

    private Context mContext;
    private AppCompatActivity mActivity;
    private RelativeLayout rootLayout;

    // Constants
    private static final int SPLASH_DURATION = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initVariable();
        initView();

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

    }

    private void initVariable() {
        mContext = getApplicationContext();
        mActivity = SplashActivity.this;
    }

    private void initView() {
        setContentView(R.layout.activity_splash);
        rootLayout = (RelativeLayout) findViewById(R.id.splashBody);
    }

    private void initFunctionality() {
        if (AppUtils.isNetworkAvailable(mContext)) {
            rootLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityUtils.getInstance().invokeActivity(mActivity, MainActivity.class, true);
                }
            }, SPLASH_DURATION);
        } else {
            AppUtils.noInternetWarning(rootLayout, mContext);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initFunctionality();
    }
}


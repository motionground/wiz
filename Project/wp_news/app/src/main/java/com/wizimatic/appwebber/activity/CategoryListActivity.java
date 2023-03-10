package com.wizimatic.appwebber.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;
import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.adapters.CategoryPagerAdapter;
import com.wizimatic.appwebber.api.http.ApiUtils;
import com.wizimatic.appwebber.api.models.category.Category;
import com.wizimatic.appwebber.api.params.HttpParams;
import com.wizimatic.appwebber.utility.AdUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SAIF-MCC on 9/19/2017.
 */

public class CategoryListActivity extends BaseActivity {

    private AppCompatActivity mActivity;
    private Context mContext;

    private ViewPager mViewPager;
    private CategoryPagerAdapter mCategoryPagerAdapter;
    private TabLayout tabLayout;

    private int mPerPage = 5;
    private List<Category> categoryList;
    private List<Category> subCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();
        initFunctionality();
    }

    private void initVar() {
        mActivity = CategoryListActivity.this;
        mContext = mActivity.getApplicationContext();

        categoryList = new ArrayList<>();
        subCategoryList = new ArrayList<>();
    }

    private void initView() {
        setContentView(R.layout.activity_tab);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        initLoader();

        initToolbar();
        setToolbarTitle(getString(R.string.category_list));
        enableBackButton();

    }

    private void initFunctionality() {

        mCategoryPagerAdapter = new CategoryPagerAdapter(getSupportFragmentManager(), (ArrayList) categoryList, (ArrayList) subCategoryList);
        mViewPager.setAdapter(mCategoryPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        showLoader();
        loadCategories();

        // show banner ads
        AdUtils.getInstance(mContext).showBannerAd((AdView) findViewById(R.id.adView));

    }

    public void loadCategories() {
        ApiUtils.getApiInterface().getCategories(mPerPage).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {

                    int totalPages = Integer.parseInt(response.headers().get(HttpParams.HEADER_TOTAL_PAGE));

                    if (totalPages > 1) {
                        mPerPage = mPerPage * totalPages;
                        loadCategories();

                    } else {
                        categoryList.addAll(response.body());
                        for (Category category : categoryList) {
                            if (category.getParent().intValue() == 0) {
                                subCategoryList.add(category);
                            }
                        }

                        mCategoryPagerAdapter.notifyDataSetChanged();

                    }

                    hideLoader();
                } else {
                    showEmptyView();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                t.printStackTrace();
                hideLoader();
                showEmptyView();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}

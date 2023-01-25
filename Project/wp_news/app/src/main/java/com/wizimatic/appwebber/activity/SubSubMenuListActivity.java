package com.wizimatic.appwebber.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;
import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.adapters.SubMenuAdapter;
import com.wizimatic.appwebber.api.models.category.Category;
import com.wizimatic.appwebber.api.models.menus.SubMenuItem;
import com.wizimatic.appwebber.data.constant.AppConstant;
import com.wizimatic.appwebber.listeners.ListItemClickListener;
import com.wizimatic.appwebber.utility.ActivityUtils;
import com.wizimatic.appwebber.utility.AdUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAIF-MCC on 10/2/2017.
 */

public class SubSubMenuListActivity extends BaseActivity {

    private AppCompatActivity mActivity;
    private Context mContext;
    private Toolbar mToolbar;
    private LinearLayout loadingView, noDataView;

    private RecyclerView rvSubMenus;
    private SubMenuAdapter subMenusAdapter = null;
    List<SubMenuItem> subSubMenuItemList;
    private ArrayList<Category> categoryList;
    private SubMenuItem selectedSubMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();
        initFunctionality();
        initListener();
    }

    private void initVar() {
        mActivity = SubSubMenuListActivity.this;
        mContext = mActivity.getApplicationContext();

        subSubMenuItemList = new ArrayList<>();
        categoryList = new ArrayList<>();

        Intent intent = getIntent();
        if (intent != null) {
            categoryList = getIntent().getParcelableArrayListExtra(AppConstant.BUNDLE_KEY_CATEGORY_LIST);
            selectedSubMenuItem = getIntent().getParcelableExtra(AppConstant.BUNDLE_KEY_SUB_MENU);
        }

    }

    private void initView() {
        setContentView(R.layout.activity_menu_or_home_cat_list);

        rvSubMenus = (RecyclerView) findViewById(R.id.rv_menus);
        rvSubMenus.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        initLoader();

        initToolbar();
        setToolbarTitle(getString(R.string.menu_list));
        enableBackButton();

    }


    private void initFunctionality() {

        showLoader();

        subSubMenuItemList.addAll(selectedSubMenuItem.getSubMenuItems());

        subMenusAdapter = new SubMenuAdapter(getApplicationContext(), (ArrayList) subSubMenuItemList);
        rvSubMenus.setAdapter(subMenusAdapter);

        hideLoader();

        // show banner ads
        AdUtils.getInstance(mContext).showBannerAd((AdView) findViewById(R.id.adView));

    }

    public void initListener() {
        subMenusAdapter.setItemClickListener(new ListItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                SubMenuItem clickedSubMenu = subSubMenuItemList.get(position);
                if (clickedSubMenu.getSubMenuItems().size() == 0) {
                    switch (clickedSubMenu.getObject()) {
                        case "category":
                            Category category = new Category(clickedSubMenu.getObjectID(), clickedSubMenu.getTitle(), 0.0, 0.0);
                            List<Category> categories = new ArrayList<>();
                            categories.add(category);
                            ActivityUtils.getInstance().invokeSubCategoryList(SubSubMenuListActivity.this, SubCategoryListActivity.class, (ArrayList) categoryList, (ArrayList) categories, false);
                            break;
                        case "page":
                        case "custom":
                            ActivityUtils.getInstance().invokeCustomPostAndLink(SubSubMenuListActivity.this, CustomLinkAndPageActivity.class, clickedSubMenu.getTitle(), clickedSubMenu.getUrl(), false);
                            break;
                        case "post":
                            ActivityUtils.getInstance().invokePostDetails(SubSubMenuListActivity.this, PostDetailsActivity.class, clickedSubMenu.getObjectID().intValue(), false);
                            break;
                        default:
                            break;
                    }

                } else {
                    ActivityUtils.getInstance().invokeSubSubMenuList(mActivity, SubSubMenuListActivity.class, (ArrayList) categoryList, clickedSubMenu, false);
                }

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
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

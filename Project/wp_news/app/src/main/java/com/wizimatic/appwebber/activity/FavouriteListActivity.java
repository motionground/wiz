package com.wizimatic.appwebber.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;
import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.adapters.FavouriteAdapter;
import com.wizimatic.appwebber.data.sqlite.FavouriteDbController;
import com.wizimatic.appwebber.listeners.ListItemClickListener;
import com.wizimatic.appwebber.models.FavouriteModel;
import com.wizimatic.appwebber.utility.ActivityUtils;
import com.wizimatic.appwebber.utility.AdUtils;
import com.wizimatic.appwebber.utility.DialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAIF-MCC on 10/25/2017.
 */

public class FavouriteListActivity extends BaseActivity {

    private AppCompatActivity mActivity;
    private Context mContext;

    private List<FavouriteModel> favouriteList;
    private FavouriteAdapter favouriteAdapter = null;
    private RecyclerView rvFavourite;
    private FavouriteDbController favouriteDbController;
    private MenuItem menuItemDeleteAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();
        initFunctionality();
        initListener();
    }

    private void initVar() {
        mActivity = FavouriteListActivity.this;
        mContext = mActivity.getApplicationContext();

        favouriteList = new ArrayList<>();
    }

    private void initView() {
        setContentView(R.layout.activity_favourite_list);

        initLoader();

        rvFavourite = (RecyclerView) findViewById(R.id.rvFavourite);
        rvFavourite.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        favouriteAdapter = new FavouriteAdapter(FavouriteListActivity.this, (ArrayList) favouriteList);
        rvFavourite.setAdapter(favouriteAdapter);

        initToolbar();
        setToolbarTitle(getString(R.string.favourite));
        enableBackButton();
    }

    private void initFunctionality() {
        showLoader();

        // show banner ads
        AdUtils.getInstance(mContext).showBannerAd((AdView) findViewById(R.id.adView));
    }

    public void updateUI() {
        if (favouriteDbController == null) {
            favouriteDbController = new FavouriteDbController(mContext);
        }
        favouriteList.clear();
        favouriteList.addAll(favouriteDbController.getAllData());

        hideLoader();

        if (favouriteList.size() == 0) {
            showEmptyView();
            if (menuItemDeleteAll != null) {
                menuItemDeleteAll.setVisible(false);
            }
        } else {
            favouriteAdapter.notifyDataSetChanged();
            if (menuItemDeleteAll != null) {
                menuItemDeleteAll.setVisible(true);
            }
        }
    }

    public void initListener() {
        favouriteAdapter.setItemClickListener(new ListItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                final FavouriteModel model = favouriteList.get(position);
                switch (view.getId()) {
                    case R.id.lyt_favourite:
                        ActivityUtils.getInstance().invokePostDetails(mActivity, PostDetailsActivity.class, model.getPostId(), false);
                        break;
                    case R.id.btn_delete:
                        DialogUtils.showDialogPrompt(mActivity, null, getString(R.string.delete_fav_item), getString(R.string.yes), getString(R.string.no), true, new DialogUtils.DialogActionListener() {
                            @Override
                            public void onPositiveClick() {
                                favouriteDbController.deleteFav(model.getPostId());
                                updateUI();
                            }
                        });
                        break;
                    default:
                        break;
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
            case R.id.menus_delete_all:
                DialogUtils.showDialogPrompt(mActivity, null, getString(R.string.delete_all_fav_item), getString(R.string.yes), getString(R.string.no), true, new DialogUtils.DialogActionListener() {
                    @Override
                    public void onPositiveClick() {
                        favouriteDbController.deleteAllFav();
                        updateUI();
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_delete_all, menu);
        menuItemDeleteAll = menu.findItem(R.id.menus_delete_all);

        updateUI();

        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (favouriteAdapter != null) {
            updateUI();
        }

        // Show banner ad
        AdUtils.getInstance(mContext).showBannerAd((AdView) findViewById(R.id.adView));
    }
}

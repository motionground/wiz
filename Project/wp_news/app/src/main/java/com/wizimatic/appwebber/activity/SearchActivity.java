package com.wizimatic.appwebber.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.wizimatic.appwebber.R;
import com.wizimatic.appwebber.data.constant.AppConstant;
import com.wizimatic.appwebber.fragment.PostListFragment;

/**
 * Created by SAIF-MCC on 10/12/2017.
 */

public class SearchActivity extends BaseActivity {

    private AppCompatActivity mActivity;
    private Context mContext;
    private Fragment postListFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();
    }

    private void initVar() {
        mActivity = SearchActivity.this;
        mContext = mActivity.getApplicationContext();

    }

    private void initView() {
        setContentView(R.layout.activity_search);

        fragmentManager = getSupportFragmentManager();
        postListFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        initToolbar();
        enableBackButton();
        setToolbarTitle(getString(R.string.search));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setQueryHint(getString(R.string.search));
        searchView.postDelayed(new Runnable() {
            @Override
            public void run() {
                searchView.setIconifiedByDefault(true);
                searchView.setFocusable(true);
                searchView.setIconified(false);
                searchView.requestFocusFromTouch();
            }
        }, 200);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //some texts here

                Bundle args = new Bundle();
                postListFragment = new PostListFragment();
                args.putInt(AppConstant.BUNDLE_KEY_CATEGORY_ID, -1);
                args.putString(AppConstant.BUNDLE_KEY_SEARCH_TEXT, newText);
                postListFragment.setArguments(args);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, postListFragment)
                        .commit();

                return false;
            }
        });


        return true;
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

package com.wizimatic.appwebber.webengine;

/**
 * Created by Ashiq on 4/19/2017.
 */

public interface WebListener {

    void onStart();

    void onLoaded();

    void onProgress(int progress);

    void onNetworkError();

    void onPageTitle(String title);
}

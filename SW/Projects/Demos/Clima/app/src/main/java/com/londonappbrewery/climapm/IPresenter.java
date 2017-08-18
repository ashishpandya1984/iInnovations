package com.londonappbrewery.climapm;

/**
 * Created by apandya on 8/18/2017.
 */

interface IPresenter {

    void onActivityCreate();

    void onActivityResume();

    void onActivityPause();

    void onPermissionGranted();
}

package com.londonappbrewery.climapm;

/**
 * Created by apandya on 8/18/2017.
 */

interface IPresenter {

    void onActivityCreate();

    void onActivityResume( final String cityName );

    void onActivityStop();

    void onPermissionGranted();
}

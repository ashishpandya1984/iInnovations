package com.londonappbrewery.climapm;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by apandya on 8/18/2017.
 */

interface IView<T> {

    void updateWeatherOnUI(T data);

    boolean requestPermission();

    AppCompatActivity getLinkedActivity();
}

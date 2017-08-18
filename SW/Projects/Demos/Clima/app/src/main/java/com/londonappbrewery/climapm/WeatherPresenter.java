package com.londonappbrewery.climapm;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by apandya on 8/18/2017.
 */
class WeatherPresenter extends JsonHttpResponseHandler implements IPresenter, LocationListener {
    // Constants:
    private final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";

    // App ID to use OpenWeather data
    private final String APP_ID = "e72ca729af228beabd5d20e3b7749713";

    // Time between location updates (5000 milliseconds or 5 seconds)
    private final long MIN_TIME = 5000;

    // Distance between location updates (1000m or 1km)
    private final float MIN_DISTANCE = 1000;

    // Location manager to access the longitude & latitude
    private LocationManager m_locationManager = null;

    // View to display the current weather
    private IView<WeatherDataModel> m_WeatherDataModelView = null;

    public WeatherPresenter(IView<WeatherDataModel> weatherDataModelIView)
    {
        m_WeatherDataModelView = weatherDataModelIView;
    }

    @Override
    public void onActivityCreate()
    {

    }

    @Override
    public void onActivityResume()
    {
        requestWeatherData();
    }

    @Override
    public void onActivityPause()
    {
        m_locationManager.removeUpdates(this);
    }

    @Override
    public void onPermissionGranted()
    {
        requestWeatherData();
    }

    @Override
    public void onLocationChanged(Location location)
    {
        RequestParams request = new RequestParams();
        request.put("lat", location.getLatitude());
        request.put("lon", location.getLongitude());
        request.put("appid", APP_ID);

        doHttpWork(request);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response)
    {
        super.onSuccess(statusCode, headers, response);

        Log.d("Clima", response.toString());
        WeatherDataModel dataModel = new WeatherDataModel(response);
        m_WeatherDataModelView.updateWeatherOnUI(dataModel);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
    {
        super.onFailure(statusCode, headers, throwable, errorResponse);
    }

    private void requestWeatherData() {
        try
        {
            m_locationManager = (LocationManager) m_WeatherDataModelView.getLinkedActivity().getSystemService(Context.LOCATION_SERVICE);

            if (m_WeatherDataModelView.requestPermission())
                m_locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
        }
        catch (SecurityException ex)
        {
            Log.d("Clima", ex.toString());
        }
    }

    private void doHttpWork(final RequestParams request)
    {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(WEATHER_URL, request, this);
    }
}

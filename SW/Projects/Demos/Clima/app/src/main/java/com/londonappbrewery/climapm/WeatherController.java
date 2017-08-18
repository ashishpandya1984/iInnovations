package com.londonappbrewery.climapm;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class WeatherController extends AppCompatActivity implements IView<WeatherDataModel> {

    private final int REQUEST_CODE = 123;

    private TextView m_CityLabel = null;

    private ImageView m_WeatherImage = null;

    private TextView m_TemperatureLabel = null;

    private IPresenter m_weatherPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_controller_layout);

        // Linking the elements in the layout to Java code
        m_CityLabel         = (TextView) findViewById(R.id.locationTV);
        m_WeatherImage      = (ImageView) findViewById(R.id.weatherSymbolIV);
        m_TemperatureLabel  = (TextView) findViewById(R.id.tempTV);

        ImageButton changeCityButton = (ImageButton) findViewById(R.id.changeCityButton);

        m_weatherPresenter = new WeatherPresenter(this);
        m_weatherPresenter.onActivityCreate();
    }

    @Override
    protected void onPostResume()
    {
        super.onPostResume();
        m_weatherPresenter.onActivityResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        m_weatherPresenter.onActivityPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if( requestCode == REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED )
            m_weatherPresenter.onPermissionGranted();
    }

    @Override
    public void updateWeatherOnUI(WeatherDataModel data)
    {
        m_CityLabel.setText( data.getCityName() );
        m_TemperatureLabel.setText( Integer.toString( data.getTemperature() )  + "°");

        final int resourceId = getResources().getIdentifier(data.getIconName(), "drawable", getPackageName());
        m_WeatherImage.setImageResource(resourceId);
    }

    @Override
    public boolean requestPermission()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions( this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_CODE );
            return false;
        }

        return true;
    }

    @Override
    public AppCompatActivity getLinkedActivity()
    {
        return this;
    }
}

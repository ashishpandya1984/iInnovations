package com.londonappbrewery.climapm;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataModel {

    // TODO: Declare the member variables here
    protected String m_cityName = null;

    protected String m_iconName = null;

    protected int m_temperature = 0;

    // TODO: Create a WeatherDataModel from a JSON:
    WeatherDataModel(JSONObject object)
    {
        try
        {
            m_cityName = (String)object.get("name");
            m_temperature = (int)( object.getJSONObject("main").getDouble("temp") - 273.15);

            int condition = object.getJSONArray("weather").getJSONObject(0).getInt("id");
            m_iconName = getWeatherIconName( condition );
        }
        catch (JSONException ex)
        {
            Log.d("Clima", ex.toString());
        }
    }

    // TODO: Uncomment to this to get the weather image name from the condition:
    private static String getWeatherIconName(int condition) {

        if (condition >= 0 && condition < 300) {
            return "tstorm1";
        } else if (condition >= 300 && condition < 500) {
            return "light_rain";
        } else if (condition >= 500 && condition < 600) {
            return "shower3";
        } else if (condition >= 600 && condition <= 700) {
            return "snow4";
        } else if (condition >= 701 && condition <= 771) {
            return "fog";
        } else if (condition >= 772 && condition < 800) {
            return "tstorm3";
        } else if (condition == 800) {
            return "sunny";
        } else if (condition >= 801 && condition <= 804) {
            return "cloudy2";
        } else if (condition >= 900 && condition <= 902) {
            return "tstorm3";
        } else if (condition == 903) {
            return "snow5";
        } else if (condition == 904) {
            return "sunny";
        } else if (condition >= 905 && condition <= 1000) {
            return "tstorm3";
        }

        return "dunno";
    }

    // TODO: Create getter methods for temperature, city, and icon name:
    public String getCityName() {
        return m_cityName;
    }

    public String getIconName() {
        return m_iconName;
    }

    public int getTemperature() {
        return m_temperature;
    }
}

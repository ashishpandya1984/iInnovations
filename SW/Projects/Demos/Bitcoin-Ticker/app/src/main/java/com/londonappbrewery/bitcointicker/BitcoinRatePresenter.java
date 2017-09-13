package com.londonappbrewery.bitcointicker;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by apandya on 9/13/2017.
 */

class BitcoinRatePresenter extends JsonHttpResponseHandler implements IPresenter
{
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";

    protected IView m_bitCoinRateView = null;

    BitcoinRatePresenter( IView bitCoinRateView )
    {
        m_bitCoinRateView = bitCoinRateView;
    }


    @Override
    public void viewCreated()
    {
        // Do nothing
    }

    @Override
    public void currencyChanged(String newCurrency)
    {
        final String newExchangeRateURL = BASE_URL + newCurrency;
        requestLatestBitcoinExchangeRate( newExchangeRateURL );
    }

    private void requestLatestBitcoinExchangeRate(String url)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, this);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response)
    {
        // called when response HTTP status is "200 OK"
        Log.d("Bit-coin-tracker", "JSON: " + response.toString());
        BitcoinModel model = new BitcoinModel(response);

        if(m_bitCoinRateView != null)
            m_bitCoinRateView.onUpdateExchangeRates(model.getLatestBitcoinPrice());
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response)
    {
        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
        Log.d("Bit-coin-tracker", "Request fail! Status code: " + statusCode);
        Log.d("Bit-coin-tracker", "Fail response: " + response);
        Log.e("ERROR", e.toString());

        if(m_bitCoinRateView != null)
            m_bitCoinRateView.onUpdateExchangeRates("NAN");
    }
}

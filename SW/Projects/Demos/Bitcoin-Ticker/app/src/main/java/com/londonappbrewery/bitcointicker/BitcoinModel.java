package com.londonappbrewery.bitcointicker;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apandya on 9/13/2017.
 */

class BitcoinModel
{
    protected String m_latestBitcoinPrice = null;

    public  BitcoinModel(JSONObject data)
    {
        if(data != null)
        {
            try
            {
                m_latestBitcoinPrice = data.getString("ask");
            }
            catch (JSONException e)
            {
                m_latestBitcoinPrice = "NAN";
            }
        }
    }

    public String getLatestBitcoinPrice()
    {
        return m_latestBitcoinPrice;
    }


}

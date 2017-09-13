package com.londonappbrewery.bitcointicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements OnItemSelectedListener, IView
{
    // Member Variables:
    TextView mPriceTextView;

    IPresenter m_bitCoinRatePresentor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPriceTextView = (TextView) findViewById(R.id.priceLabel);
        Spinner spinner = (Spinner) findViewById(R.id.currency_spinner);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_array, R.layout.spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set an OnItemSelected listener on the spinner
        spinner.setOnItemSelectedListener(this);

        m_bitCoinRatePresentor = new BitcoinRatePresenter( this );
        m_bitCoinRatePresentor.viewCreated();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String item = parent.getItemAtPosition(position).toString();
        Log.d("Bit-coin-tracker", "Item selected = " + item);

        if(m_bitCoinRatePresentor != null)
            m_bitCoinRatePresentor.currencyChanged( item );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    @Override
    public void onUpdateExchangeRates( final String newExchangeRate )
    {
        mPriceTextView.setText( newExchangeRate );
    }
}

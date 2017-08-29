package com.londonappbrewery.climapm;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ChangeCityController extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_city_layout);

        ImageButton backButton = (ImageButton) findViewById( R.id.backButton );
        backButton.setOnClickListener(this);

        EditText textEditor = (EditText) findViewById( R.id.queryET );
        textEditor.setOnEditorActionListener( this );
    }

    @Override
    public void onClick(View v)
    {
        finish();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {
        Intent intent = new Intent( ChangeCityController.this, WeatherController.class );

        final String cityName = v.getText().toString();
        intent.putExtra("City", cityName);

        startActivity( intent );
        return false;
    }
}

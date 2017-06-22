package com.example.jigyasaa.dicee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rollButton;
        rollButton = (Button) findViewById(R.id.roolButton);

        final ImageView leftDice = (ImageView) findViewById(R.id.imageView_LeftDice);
        final ImageView rightDice = (ImageView) findViewById(R.id.imageView_RightDice);

        final int [] diceArray ={ R.drawable.dice1,
                            R.drawable.dice2,
                            R.drawable.dice3,
                            R.drawable.dice4,
                            R.drawable.dice5,
                            R.drawable.dice6};

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Dicee","Button has pressed!");

                Random randomNumberGenerator = new Random();
                int number = randomNumberGenerator.nextInt(6);

                Log.d("Dicee","The Random NUmber is " + number);
                leftDice.setImageResource(diceArray[number]);

                rightDice.setImageResource(diceArray[ randomNumberGenerator.nextInt(6)]);
            }

        });


    }
}

package com.cs193a.kjlaw.hw1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.widget.Toast.makeText;

public class MainActivity extends ActionBarActivity {

    private int mRandomNumber;
    private int mUserNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Random r = new Random();
        mRandomNumber = getRandomNumber(r);

        final EditText input = (EditText) findViewById(R.id.input);
        Button guessButton = (Button) findViewById(R.id.guessButton);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        final TextView result = (TextView) findViewById(R.id.result);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userGuess = input.getText().toString();
                try {
                    mUserNumber = Integer.parseInt(userGuess);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    informUserInvalidNumber();
                }

                // Out of range
                if (mUserNumber < 1 || mUserNumber > 1000) {
                    informUserInvalidNumber();
                }


                if (mUserNumber == mRandomNumber) {
                    // Correct guess
                    result.setText("Congrats! You guessed the number!");
                } else if (mUserNumber < mRandomNumber) {
                    // Guess is too low
                    informUserGuessTooLow();
                } else {
                    // Guess is too high
                    informUserGuessTooHigh();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRandomNumber = getRandomNumber(r);
                result.setText("");
                input.setText("");
            }
        });
    }

    int getRandomNumber(Random r) {
        int num = r.nextInt(1000) + 1;
//        Toast.makeText(getApplicationContext(), "Answer: " + num, Toast.LENGTH_LONG).show();
        return num;
    }

    void informUserInvalidNumber() {
        Toast.makeText(getApplicationContext(), "Please enter a valid number between 1 and 1000.", Toast.LENGTH_LONG).show();
    }

    void informUserGuessTooLow() {
        Toast.makeText(getApplicationContext(), "Your guess is too low! Try again.", Toast.LENGTH_SHORT).show();
    }

    void informUserGuessTooHigh() {
        Toast.makeText(getApplicationContext(), "Your guess is too high! Try again.", Toast.LENGTH_SHORT).show();
    }
}

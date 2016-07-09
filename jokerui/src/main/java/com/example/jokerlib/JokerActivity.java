package com.example.jokerlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokerActivity extends AppCompatActivity {
    public static final String KEY_JOKE = "joke";
    private String mJoke;
    private TextView mJokeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker);
        mJoke = getIntent().getStringExtra(KEY_JOKE);
        mJokeTV = (TextView) findViewById(R.id.joke);
        if(mJoke != null){
            mJokeTV.setText(mJoke);
        }
    }
}

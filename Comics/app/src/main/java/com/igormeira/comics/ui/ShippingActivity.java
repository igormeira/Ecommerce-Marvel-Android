package com.igormeira.comics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.igormeira.comics.R;

public class ShippingActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        configStartButton();
    }

    private void configStartButton() {
        startButton = findViewById(R.id.begin_button);
        startButton.setOnClickListener(v -> goToBegin());
    }

    private void goToBegin() {
        Intent intent = new Intent(ShippingActivity.this, ComicsActivity.class);
        startActivity(intent);
    }
}

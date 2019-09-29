package com.igormeira.comics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.igormeira.comics.R;
import com.igormeira.comics.util.SharedPreference;

/**
 * Activity que informa ao usuário que o produto está sendo enviado
 */
public class ShippingActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        configStartButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_general_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.general_logout:
                logout();
                return true;
            case R.id.general_user:
                showUserInfo();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void configStartButton() {
        startButton = findViewById(R.id.begin_button);
        startButton.setOnClickListener(v -> goToBegin());
    }

    /**
     * Chama ComicsActivity
     */
    private void goToBegin() {
        Intent intent = new Intent(ShippingActivity.this, ComicsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }

    /**
     * Realiza logout do usuário
     */
    private void logout() {
        new SharedPreference(this).sharedReset();
        this.finish();
        Intent intent = new Intent(ShippingActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Chama UserActivity
     */
    private void showUserInfo() {
        Intent intent = new Intent(ShippingActivity.this, UserActivity.class);
        startActivity(intent);
    }
}

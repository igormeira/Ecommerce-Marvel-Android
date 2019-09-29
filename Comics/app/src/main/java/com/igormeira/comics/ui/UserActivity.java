package com.igormeira.comics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.igormeira.comics.R;
import com.igormeira.comics.util.SharePreference;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        configFabShopButton();
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
        }

        return super.onOptionsItemSelected(item);
    }

    private void configFabShopButton() {
        FloatingActionButton fabShopCar = findViewById(R.id.fab_shopcar_button_user);
        fabShopCar.setOnClickListener(v -> openShopCar());
    }

    private void openShopCar() {
        Intent intent = new Intent(UserActivity.this, ShopActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void logout() {
        new SharePreference(this).sharedReset();
        this.finish();
        Intent intent = new Intent(UserActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}

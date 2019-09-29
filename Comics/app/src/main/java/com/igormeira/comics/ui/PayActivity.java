package com.igormeira.comics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.igormeira.comics.R;
import com.igormeira.comics.util.Currency;
import com.igormeira.comics.util.SharedPreference;

import java.math.BigDecimal;

/**
 * Activity responsável pelo pagamento da compra
 */
public class PayActivity extends AppCompatActivity {

    private int numberOfComics;
    private BigDecimal total, discount;
    private TextView nComicsTextView, totalTextView, discountTextView;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Intent intent = getIntent();
        numberOfComics = intent.getIntExtra("Comics", 0);
        total = (BigDecimal) intent.getSerializableExtra("Total");
        discount = (BigDecimal) intent.getSerializableExtra("Discount");

        configPurchaseData();
        configFinishButton();
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

    private void configFinishButton() {
        finishButton = findViewById(R.id.finish_button);
        finishButton.setOnClickListener(v -> finishOrder());
    }

    /**
     * Chama ShippingActivity
     */
    private void finishOrder() {
        Intent intent = new Intent(PayActivity.this, ShippingActivity.class);
        startActivity(intent);
    }

    private void configPurchaseData() {
        nComicsTextView = findViewById(R.id.name);
        nComicsTextView.setText(String.valueOf(numberOfComics));

        totalTextView = findViewById(R.id.birthdate);
        totalTextView.setText(new Currency().currencyFormat(total));

        discountTextView = findViewById(R.id.email);
        discountTextView.setText(new Currency().currencyNegativeFormat(discount));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * Realiza logout do usuário
     */
    private void logout() {
        new SharedPreference(this).sharedReset();
        this.finish();
        Intent intent = new Intent(PayActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Chama UserActivity
     */
    private void showUserInfo() {
        Intent intent = new Intent(PayActivity.this, UserActivity.class);
        startActivity(intent);
    }
}

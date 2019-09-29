package com.igormeira.comics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.igormeira.comics.R;
import com.igormeira.comics.util.Utils;

import java.math.BigDecimal;

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

    private void configFinishButton() {
        finishButton = findViewById(R.id.finish_button);
        finishButton.setOnClickListener(v -> finishOrder());
    }

    private void finishOrder() {
        Intent intent = new Intent(PayActivity.this, ShippingActivity.class);
        startActivity(intent);
    }

    private void configPurchaseData() {
        nComicsTextView = findViewById(R.id.n_comics);
        nComicsTextView.setText(String.valueOf(numberOfComics));

        totalTextView = findViewById(R.id.total_pay);
        totalTextView.setText(new Utils().currencyFormat(total));

        discountTextView = findViewById(R.id.off_pay);
        discountTextView.setText(new Utils().currencyNegativeFormat(discount));
    }
}

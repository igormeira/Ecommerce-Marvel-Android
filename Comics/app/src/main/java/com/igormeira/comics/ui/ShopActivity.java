package com.igormeira.comics.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igormeira.comics.R;
import com.igormeira.comics.model.Comic;
import com.igormeira.comics.model.discount.CommonDiscount;
import com.igormeira.comics.model.discount.RareDiscount;
import com.igormeira.comics.model.discount.WithoutDiscount;
import com.igormeira.comics.ui.recyclerview.adapter.ListShopAdapter;
import com.igormeira.comics.util.SharedPreference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity responsável por listar os itens no carrinho e aplicar os descontos.
 */
public class ShopActivity extends AppCompatActivity {

    Button payButton;
    EditText coupon;
    private ListShopAdapter adapter;
    private List<Comic> comics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        comics = new ArrayList<Comic>();

        configListComics();

        searchComics();

        configCoupon();
        configPayButton();
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

    private void configCoupon() {
        coupon = findViewById(R.id.shop_off_editText);
    }

    private void configPayButton() {
        payButton = findViewById(R.id.pay_button);
        payButton.setOnClickListener(v -> pay());
    }

    /**
     * Chama PayActivity.
     * Aplica os descontos, caso houver.
     * Passa as informações de número de itens, total de desconto e total.
     */
    private void pay() {
        Gson gson = new Gson();
        String response = new SharedPreference(ShopActivity.this).sharedGetComics();
        if (response != null) {
            comics = gson.fromJson(response,
                    new TypeToken<List<Comic>>(){}.getType());
            if (comics.size() > 0) {
                BigDecimal totalWithoutDiscount = new WithoutDiscount().getDiscount(comics);
                BigDecimal total;
                BigDecimal discount;

                String discountType = coupon.getText().toString();
                switch (discountType) {
                    case "Raro":
                        total = new RareDiscount().getDiscount(comics);
                        break;
                    case "Comum":
                        total = new CommonDiscount().getDiscount(comics);
                        break;
                    default:
                        total = totalWithoutDiscount;
                        break;
                }

                discount = totalWithoutDiscount.subtract(total);

                Intent intent = new Intent(ShopActivity.this, PayActivity.class);
                intent.putExtra("Comics", comics.size());
                intent.putExtra("Total", total);
                intent.putExtra("Discount", discount);
                startActivity(intent);
            } else {
                emptyCarError();
            }
        }
        else {
            emptyCarError();
        }
    }

    /**
     * Atualiza lista do carrinho
     */
    public void searchComics() {
        Gson gson = new Gson();
        String response = new SharedPreference(ShopActivity.this).sharedGetComics();
        if (response != null) {
            comics = gson.fromJson(response,
                    new TypeToken<List<Comic>>(){}.getType());
            if (comics.size() > 0) {
                adapter.updateListShop(comics);
            }
        }
    }

    /**
     * Mostra mensagem na tela
     */
    private void emptyCarError() {
        Toast.makeText(ShopActivity.this,
                R.string.empty_car,
                Toast.LENGTH_SHORT).show();
    }

    private void configListComics() {
        RecyclerView listComics = findViewById(R.id.activity_list_shop);
        adapter = new ListShopAdapter(ShopActivity.this);
        listComics.setAdapter(adapter);
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
        Intent intent = new Intent(ShopActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Chama UserActivity
     */
    private void showUserInfo() {
        Intent intent = new Intent(ShopActivity.this, UserActivity.class);
        startActivity(intent);
    }
}

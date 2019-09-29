package com.igormeira.comics.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.igormeira.comics.R;
import com.igormeira.comics.asynctask.BaseAsyncTask;
import com.igormeira.comics.model.Comic;
import com.igormeira.comics.retrofit.MarvelRetrofit;
import com.igormeira.comics.retrofit.service.ComicService;
import com.igormeira.comics.ui.recyclerview.adapter.ListComicsAdapter;
import com.igormeira.comics.util.Dialogs;
import com.igormeira.comics.util.SharedPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Activity que lista as comics.
 */
public class ComicsActivity extends AppCompatActivity {

    private ListComicsAdapter adapter;
    private List<Comic> comics;
    private int rareLimit;
    private int rareNumber;

    private final List<String> TYPES = Arrays.asList("Comum", "Raro");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);

        comics = new ArrayList<Comic>();
        rareNumber = 0;

        configListComics();
        configFabShopButton();

        searchComics();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_comics_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.comics_refresh:
                searchComics();
                return true;
            case R.id.comics_logout:
                logout();
                return true;
            case R.id.comics_user:
                showUserInfo();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Atualiza lista de comics.
     */
    private void searchComics() {
        ComicService service = new MarvelRetrofit().getComicService();
        Call<Object> call = service.searchAll();
        ProgressDialog dialog = new Dialogs().showLoadingDialog(ComicsActivity.this);
        new BaseAsyncTask<>(() -> {
            try {
                getDataFromApi(call);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }, newComics -> {
            dialog.dismiss();
            if (comics.size() > 0) {
                adapter.updateListComics(comics);
            } else {
                Toast.makeText(this,
                        R.string.error_get_comics_api,
                        Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    /**
     * Recupera dados da Api
     *
     * @param call
     * @throws IOException
     * @throws JSONException
     */
    private void getDataFromApi(Call<Object> call) throws IOException, JSONException {
        Response<Object> resposta = call.execute();
        String json = new Gson().toJson(resposta.body());
        JSONObject objMain = new JSONObject(json);
        String code = objMain.getString("code");
        if (code.equals("200.0")) {
            JSONObject objData = objMain.getJSONObject("data");
            JSONArray jsonArray = objData.getJSONArray("results");

            rareLimit = (int) Math.round((double) jsonArray.length() * 0.12);
            new SharedPreference(this).sharedReset();

            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);

                    String title = object.getString("title");

                    String description = object.getString("description");

                    JSONArray prices = object.getJSONArray("prices");
                    JSONObject pricesObject = prices.getJSONObject(0);
                    Double price = Double.valueOf(pricesObject.getString("price"));
                    BigDecimal priceBig = new BigDecimal(price, MathContext.DECIMAL64);

                    JSONObject thumbnailObject = object.getJSONObject("thumbnail");
                    String path = thumbnailObject.getString("path");
                    String extension = thumbnailObject.getString("extension");
                    String thumbnailPath = path + "." + extension;

                    Comic comic = new Comic(title, description,
                            priceBig, thumbnailPath, randomType());
                    comics.add(comic);
                }
            }
        }
    }

    /**
     * Retrona, randomicamente, o tipo da comic (Comum ou Raro).
     *
     * @return String
     */
    private String randomType() {
        String type = "Comum";

        if (rareNumber < rareLimit) {
            int randomIndex = new Random().nextInt(TYPES.size());
            String randomType = TYPES.get(randomIndex);
            if (randomType.equals("Raro")) rareNumber += 1;
            return randomType;
        }
        return type;
    }

    private void configListComics() {
        RecyclerView listComics = findViewById(R.id.activity_lista_comics);
        adapter = new ListComicsAdapter(this::openDetails, ComicsActivity.this);
        listComics.setAdapter(adapter);
    }

    /**
     * Chama ComicDetailsActivity, passando a comic selecionada.
     *
     * @param posicao
     * @param comic
     */
    private void openDetails(int posicao, Comic comic) {
        Intent intent = new Intent(ComicsActivity.this, ComicDetailActivity.class);
        intent.putExtra("Comic", comic);
        startActivity(intent);
    }

    private void configFabShopButton() {
        FloatingActionButton fabShopCar = findViewById(R.id.fab_shopcar_button);
        fabShopCar.setOnClickListener(v -> openShopCar());
    }

    /**
     * Chama ShopActivity.
     */
    private void openShopCar() {
        Intent intent = new Intent(ComicsActivity.this, ShopActivity.class);
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
        Intent intent = new Intent(ComicsActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Chama UserActivity
     */
    private void showUserInfo() {
        Intent intent = new Intent(ComicsActivity.this, UserActivity.class);
        startActivity(intent);
    }
}

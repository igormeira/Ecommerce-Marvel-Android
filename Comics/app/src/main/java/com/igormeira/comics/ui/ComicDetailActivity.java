package com.igormeira.comics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.igormeira.comics.R;
import com.igormeira.comics.model.Comic;
import com.igormeira.comics.util.Currency;
import com.igormeira.comics.util.SharePreference;
import com.igormeira.comics.util.Text;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ComicDetailActivity extends AppCompatActivity {

    private TextView title, price, description;
    private ImageView cover;
    private DisplayImageOptions options;
    private static ImageLoader imageLoader;
    private Button buyButton;
    private Comic comic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        Intent intent = getIntent();
        comic = (Comic) intent.getSerializableExtra("Comic");

        configImageLoader();
        configFabShopButton();
        configDetails();
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

    private void showUserInfo() {
        Intent intent = new Intent(ComicDetailActivity.this, UserActivity.class);
        startActivity(intent);
    }

    private void configFabShopButton() {
        FloatingActionButton fabShopCar = findViewById(R.id.fab_shopcar_button_details);
        fabShopCar.setOnClickListener(v -> openShopCar());
    }

    private void openShopCar() {
        Intent intent = new Intent(ComicDetailActivity.this, ShopActivity.class);
        startActivity(intent);
    }

    private void configImageLoader() {
        imageLoader = ImageLoader.getInstance();
        ComicDetailActivity.imageLoader.init(ImageLoaderConfiguration.createDefault(this));
    }

    private void configDetails() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();

        title = findViewById(R.id.detalhes_titulo_textView);
        title.setText(comic.getTitle());

        price = findViewById(R.id.detalhes_preco_textView);
        price.setText(new Currency().currencyFormat(comic.getPrice()));

        description = findViewById(R.id.detalhes_descricao_textView);
        description.setText(new Text().adaptDescriptionText(comic.getDescription()));

        buyButton = findViewById(R.id.buy_button);

        buyButton.setOnClickListener(this::onButtonClick);

        cover = findViewById(R.id.details_imageView);
        ImageLoader.getInstance().displayImage(comic.getThumbnail(), cover, options);
    }

    public void onButtonClick(View v){
        new SharePreference(this).sharedAddComic(comic);
        Toast.makeText(this,
                R.string.added_to_car,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void logout() {
        new SharePreference(this).sharedReset();
        this.finish();
        Intent intent = new Intent(ComicDetailActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}

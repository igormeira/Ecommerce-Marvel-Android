package com.igormeira.comics.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.igormeira.comics.R;
import com.igormeira.comics.model.Comic;
import com.igormeira.comics.util.Utils;
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

        imageLoader = ImageLoader.getInstance();
        ComicDetailActivity.imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        configDetails();
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
        price.setText(new Utils().currencyFormat(comic.getPrice()));

        description = findViewById(R.id.detalhes_descricao_textView);
        description.setText(comic.getDescription());

        buyButton = findViewById(R.id.buy_button);

        buyButton.setOnClickListener(this::onButtonClick);

        cover = findViewById(R.id.details_imageView);
        ImageLoader.getInstance().displayImage(comic.getThumbnail(), cover, options);
    }

    public void onButtonClick(View v){
        new Utils(this).sharedAddComic(comic);
        Toast.makeText(this,
                "Adiconado ao carrinho!",
                Toast.LENGTH_SHORT).show();
    }
}

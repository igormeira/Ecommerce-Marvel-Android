package com.igormeira.comics.retrofit;

import com.igormeira.comics.retrofit.service.ComicService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe que estabelece conexão com a Api
 */
public class MarvelRetrofit {

    private static final String URL_BASE = "http://gateway.marvel.com/v1/public/";
    private final ComicService comicService;

    /**
     * Construtor da calsse
     */
    public MarvelRetrofit() {
        OkHttpClient client = configClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        comicService = retrofit.create(ComicService.class);
    }

    private OkHttpClient configClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    public ComicService getComicService() {
        return comicService;
    }
}

package com.igormeira.comics.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igormeira.comics.model.ShopCar;
import com.igormeira.comics.model.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por guardar, recuperar e remover os itens do carrionho do usuário.
 */
public class SharedPreference {

    SharedPreferences shref;

    /**
     * Construtor da classe.
     *
     * @param context
     */
    public SharedPreference(Context context) {
        shref = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
    }

    /**
     * Construtor da classe
     */
    public SharedPreference() {}

    /**
     * Adicionar item ao carrinho
     *
     * @param comic
     */
    public void sharedAddComic(Comic comic) {
        SharedPreferences.Editor editor;

        Gson gson = new Gson();
        String response = shref.getString("Comics" , null);
        List<Comic> comics;
        if (response != null) {
            List<Comic> lstArrayList = gson.fromJson(response,
                    new TypeToken<List<Comic>>(){}.getType());
            comics = lstArrayList;
        }
        else {
            comics = new ArrayList<Comic>();
        }
        comics.add(comic);
        ShopCar shopCar = new ShopCar(comics);
        String json = gson.toJson(shopCar.getComics());
        editor = shref.edit();
        editor.remove("Comics").commit();
        editor.putString("Comics", json);
        editor.commit();
    }

    /**
     * Atualiza itens do carrinho
     *
     * @param comics
     */
    public void sharedUpdateComics(List<Comic> comics) {
        SharedPreferences.Editor editor;
        Gson gson = new Gson();
        ShopCar shopCar = new ShopCar(comics);
        String json = gson.toJson(shopCar.getComics());
        editor = shref.edit();
        editor.remove("Comics").commit();
        editor.putString("Comics", json);
        editor.commit();
    }

    /**
     * Remove todos itens do carrinho
     */
    public void sharedReset() {
        SharedPreferences.Editor editor;
        editor = shref.edit();
        editor.remove("Comics").commit();
    }

    /**
     * Recupera itens do carrinho
     *
     * @return String
     */
    public String sharedGetComics() {
        String response = shref.getString("Comics" , null);
        return response;
    }

}

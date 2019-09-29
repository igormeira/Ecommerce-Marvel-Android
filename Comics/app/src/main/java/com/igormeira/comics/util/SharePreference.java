package com.igormeira.comics.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igormeira.comics.model.ShopCar;
import com.igormeira.comics.model.Comic;

import java.util.ArrayList;
import java.util.List;

public class SharePreference {

    SharedPreferences shref;

    public SharePreference(Context context) {
        shref = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
    }

    public SharePreference() {}

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

    public void sharedReset() {
        SharedPreferences.Editor editor;
        editor = shref.edit();
        editor.remove("Comics").commit();
    }

    public String sharedGetComics() {
        String response = shref.getString("Comics" , null);
        return response;
    }

}

package com.igormeira.comics.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igormeira.comics.model.ShopCar;
import com.igormeira.comics.model.Comic;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Utils {

    SharedPreferences shref;

    public Utils(Context context) {
        shref = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
    }

    public Utils() {}

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

    public String currencyFormat(BigDecimal value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String formatted = formatter.format(value);
        return formatted.replace("$", "$ ");
    }

    public String currencyNegativeFormat(BigDecimal value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String formatted = formatter.format(value);
        return formatted.replace("$", "$ -");
    }

}

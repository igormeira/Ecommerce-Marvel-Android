package com.igormeira.comics.model;

import java.util.List;

public class ShopCar {

    private List<Comic> comics;

    public ShopCar(List<Comic> comics) {
        this.comics = comics;
    }

    public List<Comic> getComics() {
        return comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }


}

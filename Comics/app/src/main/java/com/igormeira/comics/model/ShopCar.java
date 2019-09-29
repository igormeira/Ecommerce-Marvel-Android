package com.igormeira.comics.model;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopCar shopCar = (ShopCar) o;
        return comics.equals(shopCar.comics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comics);
    }
}

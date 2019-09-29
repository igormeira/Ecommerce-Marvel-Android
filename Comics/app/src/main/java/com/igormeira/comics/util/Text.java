package com.igormeira.comics.util;

public class Text {

    public String adaptDescriptionText(String description) {
        return description.replace("<br>", " ") + "\n";
    }
}

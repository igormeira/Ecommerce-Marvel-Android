package com.igormeira.comics.util;

/**
 * Classe responsável por ajustar os textos exibidos.
 */
public class Text {

    /**
     * Adaptar texto da descrição da comic.
     * Retira "br" presentes na string passada.
     *
     * @param description
     * @return String
     */
    public String adaptDescriptionText(String description) {
        return description.replace("<br>", " ") + "\n";
    }
}

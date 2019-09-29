package com.igormeira.comics.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe responsável por colocar a moeda no formato adequado.
 */
public class Currency {

    /**
     * Formato americano e valor positivo
     *
     * @param value
     * @return String
     */
    public String currencyFormat(BigDecimal value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String formatted = formatter.format(value);
        return formatted.replace("$", "$ ");
    }

    /**
     * Formato americano e valor negativo
     *
     * @param value
     * @return String
     */
    public String currencyNegativeFormat(BigDecimal value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String formatted = formatter.format(value);
        return formatted.replace("$", "$ -");
    }
}

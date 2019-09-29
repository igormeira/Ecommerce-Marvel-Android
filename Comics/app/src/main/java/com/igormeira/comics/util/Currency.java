package com.igormeira.comics.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Currency {

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

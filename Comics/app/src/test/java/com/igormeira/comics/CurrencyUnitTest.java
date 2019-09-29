package com.igormeira.comics;

import com.igormeira.comics.util.Currency;
import com.igormeira.comics.util.SharePreference;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CurrencyUnitTest {

    @Test
    public void formatBigDecimalToCurrency() {
        String currencyZero = new Currency().currencyFormat(BigDecimal.ZERO);
        assertEquals("$ 0.00", currencyZero);

        String currencyOne = new Currency().currencyFormat(BigDecimal.ONE);
        assertEquals("$ 1.00", currencyOne);

        String currencyTen = new Currency().currencyFormat(BigDecimal.TEN);
        assertEquals("$ 10.00", currencyTen);
    }

    @Test
    public void formatBigDecimalToNegativeCurrency() {
        String currencyZero = new Currency().currencyNegativeFormat(BigDecimal.ZERO);
        assertEquals("$ -0.00", currencyZero);

        String currencyOne = new Currency().currencyNegativeFormat(BigDecimal.ONE);
        assertEquals("$ -1.00", currencyOne);

        String currencyTen = new Currency().currencyNegativeFormat(BigDecimal.TEN);
        assertEquals("$ -10.00", currencyTen);
    }
}
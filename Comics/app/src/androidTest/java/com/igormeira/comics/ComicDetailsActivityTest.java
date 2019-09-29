package com.igormeira.comics;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.igormeira.comics.model.Comic;
import com.igormeira.comics.ui.ComicDetailActivity;
import com.igormeira.comics.util.Utils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class ComicDetailsActivityTest {

    private final String THUMBNAIL_PATH = "http://i.annihil.us/u/prod/marvel/i/mg/d/70/4bc69c7e9b9d7.jpg";

    @Rule
    public ActivityTestRule<ComicDetailActivity> activityRule
            = new ActivityTestRule<>(ComicDetailActivity.class, true, false);

    @Before
    public void initIntentValues() {
        Comic comic = new Comic("X-Men", "Descrição", BigDecimal.TEN,
                                THUMBNAIL_PATH, "Comum");

        Intent intent = new Intent();
        intent.putExtra("Comic", comic);
        activityRule.launchActivity(intent);

        new Utils(activityRule.getActivity().getApplicationContext()).sharedReset();
    }

    @Test
    public void addComicToShopCar() {
        onView(withId(R.id.buy_button))
                .perform(click());

        String shared = new Utils(activityRule.getActivity().getApplicationContext()).sharedGetComics();
        assertFalse(shared == null);
    }
}

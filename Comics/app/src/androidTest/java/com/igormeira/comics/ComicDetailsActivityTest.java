package com.igormeira.comics;

import android.app.Activity;
import android.content.Intent;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.igormeira.comics.model.Comic;
import com.igormeira.comics.ui.ComicDetailActivity;
import com.igormeira.comics.ui.LoginActivity;
import com.igormeira.comics.ui.ShopActivity;
import com.igormeira.comics.ui.UserActivity;
import com.igormeira.comics.util.SharedPreference;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.runner.lifecycle.Stage.RESUMED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

        new SharedPreference(activityRule.getActivity().getApplicationContext()).sharedReset();
    }

    @Test
    public void addComicToShopCar() {
        onView(withId(R.id.buy_button))
                .perform(click());

        String shared = new SharedPreference(activityRule.getActivity().getApplicationContext()).sharedGetComics();
        assertFalse(shared == null);
    }

    @Test
    public void comicGoesToShop() {
        onView(withId(R.id.fab_shopcar_button_details))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof ShopActivity);
        assertTrue(expected);

        String shared = new SharedPreference(activity.getApplicationContext()).sharedGetComics();
        assertEquals(null, shared);
    }

    @Test
    public void goToLoginActivityWhenLogout() {
        onView(withId(R.id.general_logout))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof LoginActivity);
        assertTrue(expected);
    }

    @Test
    public void comicGoesToUser() {
        onView(withId(R.id.general_user))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof UserActivity);
        assertTrue(expected);
    }

    public Activity getActivityInstance() {
        final Activity[] activity = new Activity[1];
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            Activity currentActivity;
            Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
            if (resumedActivities.iterator().hasNext()){
                currentActivity = (Activity) resumedActivities.iterator().next();
                activity[0] = currentActivity;
            }
        });

        return activity[0];
    }
}

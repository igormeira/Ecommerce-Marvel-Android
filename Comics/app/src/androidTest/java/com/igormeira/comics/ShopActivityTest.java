package com.igormeira.comics;

import android.app.Activity;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.igormeira.comics.model.Comic;
import com.igormeira.comics.ui.LoginActivity;
import com.igormeira.comics.ui.PayActivity;
import com.igormeira.comics.ui.ShopActivity;
import com.igormeira.comics.ui.UserActivity;
import com.igormeira.comics.util.SharedPreference;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.runner.lifecycle.Stage.RESUMED;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ShopActivityTest {

    private final String THUMBNAIL_PATH = "http://i.annihil.us/u/prod/marvel/i/mg/d/70/4bc69c7e9b9d7.jpg";
    private Comic comic;

    @Rule
    public ActivityTestRule<ShopActivity> activityRule
            = new ActivityTestRule<>(ShopActivity.class);

    @Before
    public void setUp() {
        comic = new Comic("X-Men", "Descrição", BigDecimal.TEN,
                THUMBNAIL_PATH, "Comum");

        new SharedPreference(activityRule.getActivity().getApplicationContext()).sharedAddComic(comic);
    }

    @Test
    public void shopNotGoesToPayWithEmptyShared() {
        new SharedPreference(activityRule.getActivity().getApplicationContext()).sharedReset();

        onView(withId(R.id.pay_button))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof PayActivity);
        assertFalse(expected);
    }

    @Test
    public void removeComicFromShopCar() {
        onView(withText(startsWith("Remover")))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof PayActivity);
        assertFalse(expected);
    }

    @Test
    public void shopGoesToPay() {
        onView(withId(R.id.pay_button))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof PayActivity);
        assertTrue(expected);
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
    public void shopGoesToUser() {
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

    @After
    public void tearDown() {
        new SharedPreference(activityRule.getActivity().getApplicationContext()).sharedReset();
    }
}

package com.igormeira.comics;

import android.app.Activity;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.igormeira.comics.ui.ComicDetailActivity;
import com.igormeira.comics.ui.ComicsActivity;
import com.igormeira.comics.ui.LoginActivity;
import com.igormeira.comics.ui.ShopActivity;
import com.igormeira.comics.ui.UserActivity;
import com.igormeira.comics.util.SharePreference;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.runner.lifecycle.Stage.RESUMED;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ComicsActivityTest {

    @Rule
    public ActivityTestRule<ComicsActivity> activityRule
            = new ActivityTestRule<>(ComicsActivity.class);

    @Test
    public void comicsGoesToShop() {
        onView(withId(R.id.fab_shopcar_button))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof ShopActivity);
        assertTrue(expected);

        String shared = new SharePreference(activity.getApplicationContext()).sharedGetComics();
        assertEquals(null, shared);
    }

    @Test
    public void comicsGoesToDetails() {
        onView(withText(startsWith("X-Men")))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof ComicDetailActivity);
        assertTrue(expected);

        String shared = new SharePreference(activity.getApplicationContext()).sharedGetComics();
        assertEquals(null, shared);
    }

    @Test
    public void blockBackButton() {
        onView(isRoot()).perform(ViewActions.pressBack());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof LoginActivity);
        assertFalse(expected);
    }

    @Test
    public void comicsGoesToUser() {
        onView(withId(R.id.comics_user))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof UserActivity);
        assertTrue(expected);
    }

    @Test
    public void comicsGoesToLoginActivityWhenLogout() {
        onView(withId(R.id.comics_logout))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof LoginActivity);
        assertTrue(expected);
    }

    @Test
    public void comicsRefresh() {
        onView(withId(R.id.comics_refresh))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof ComicsActivity);
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

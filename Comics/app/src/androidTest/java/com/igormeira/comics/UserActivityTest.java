package com.igormeira.comics;

import android.app.Activity;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.igormeira.comics.ui.LoginActivity;
import com.igormeira.comics.ui.ShopActivity;
import com.igormeira.comics.ui.UserActivity;
import com.igormeira.comics.util.SharePreference;

import org.junit.Rule;
import org.junit.Test;

import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.runner.lifecycle.Stage.RESUMED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserActivityTest {

    @Rule
    public ActivityTestRule<UserActivity> activityRule
            = new ActivityTestRule<>(UserActivity.class);

    @Test
    public void userGoesToShop() {
        onView(withId(R.id.fab_shopcar_button_user))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof ShopActivity);
        assertTrue(expected);

        String shared = new SharePreference(activity.getApplicationContext()).sharedGetComics();
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
    public void userStay() {
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

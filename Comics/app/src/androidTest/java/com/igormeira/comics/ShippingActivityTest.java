package com.igormeira.comics;

import android.app.Activity;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.igormeira.comics.model.Comic;
import com.igormeira.comics.ui.ComicsActivity;
import com.igormeira.comics.ui.LoginActivity;
import com.igormeira.comics.ui.PayActivity;
import com.igormeira.comics.ui.ShippingActivity;
import com.igormeira.comics.ui.UserActivity;
import com.igormeira.comics.util.SharePreference;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.runner.lifecycle.Stage.RESUMED;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ShippingActivityTest {

    @Rule
    public ActivityTestRule<ShippingActivity> activityRule
            = new ActivityTestRule<>(ShippingActivity.class);

    @Test
    public void shippingGoesToComics() {
        onView(withId(R.id.begin_button))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof ComicsActivity);
        assertTrue(expected);
    }

    @Test
    public void resetSharedPreferencesWhenBackToComics() {
        Comic comic = new Comic("X-Men", "Descrição", BigDecimal.TEN,
                "", "Comum");
        new SharePreference(activityRule.getActivity().getApplicationContext()).sharedAddComic(comic);

        onView(withId(R.id.begin_button))
                .perform(click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof ComicsActivity);
        assertTrue(expected);

        String shared = new SharePreference(activityRule.getActivity().getApplicationContext()).sharedGetComics();
        assertTrue(shared == null);
    }

    @Test
    public void blockBackButton() {
        onView(isRoot()).perform(ViewActions.pressBack());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof PayActivity);
        assertFalse(expected);
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
    public void shippingGoesToUser() {
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

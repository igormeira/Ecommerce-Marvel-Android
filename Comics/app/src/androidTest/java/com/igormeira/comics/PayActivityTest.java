package com.igormeira.comics;

import android.app.Activity;
import android.content.Intent;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.igormeira.comics.ui.PayActivity;
import com.igormeira.comics.ui.ShippingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.runner.lifecycle.Stage.RESUMED;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class PayActivityTest {

    @Rule
    public ActivityTestRule<PayActivity> activityRule
            = new ActivityTestRule<>(PayActivity.class, true, false);

    @Before
    public void initIntentValues() {
        Intent intent = new Intent();
        intent.putExtra("Comics", 1);
        intent.putExtra("Total", BigDecimal.TEN);
        intent.putExtra("Discount", BigDecimal.ZERO);
        activityRule.launchActivity(intent);
    }

    @Test
    public void settingIntentValues() {
        onView(withId(R.id.n_comics)).check(matches(withText(String.valueOf(1))));
        onView(withId(R.id.off_pay)).check(matches(withText("$ -0.00")));
        onView(withId(R.id.total_pay)).check(matches(withText("$ 10.00")));
    }

    @Test
    public void payGoesToShipping() {
        onView(withId(R.id.finish_button))
                .perform(scrollTo(), click());

        Activity activity = getActivityInstance();
        boolean expected = (activity instanceof ShippingActivity);
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

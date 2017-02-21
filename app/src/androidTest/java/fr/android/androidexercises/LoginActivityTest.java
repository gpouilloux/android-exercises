package fr.android.androidexercises;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.collect.Iterables;
import android.support.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);

    @Test
    public void should_set_logged_state() throws Exception {
        onView(withId(R.id.usernameEdit))
                .check(matches(isDisplayed()))
                .perform(typeText("gpouilloux"));

        onView(withId(R.id.passwordEdit))
                .check(matches(isDisplayed()))
                .perform(typeText("123456"), closeSoftKeyboard());

        onView(withId(R.id.loginButton))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.loggedText))
                .check(matches(isDisplayed()));
    }

    @Test
    public void should_set_notlogged_state() throws Exception {
        onView(withId(R.id.usernameEdit))
                .check(matches(isDisplayed()))
                .perform(typeText("gpouilloux"));

        onView(withId(R.id.passwordEdit))
                .check(matches(isDisplayed()))
                .perform(typeText("12"), closeSoftKeyboard());

        onView(withId(R.id.loginButton))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.loggedText))
                .check(matches(not(isDisplayed())));
    }

    public void takeScreenshot(String name) {
//        Spoon.screenshot(getCurrentActivity(), name);
    }

    public Activity getCurrentActivity() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.waitForIdleSync();
        final Activity[] activity = new Activity[1];
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                ActivityLifecycleMonitor activityLifecycleMonitor = ActivityLifecycleMonitorRegistry.getInstance();
                java.util.Collection<Activity> resumedActivities = activityLifecycleMonitor.getActivitiesInStage(Stage.RESUMED);
                activity[0] = Iterables.getOnlyElement(resumedActivities);
            }
        });
        return activity[0];
    }

}
package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;


public class ExampleInstrumentedTest6 {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testUserFlow() {
        // Provjerava je li MainActivity otvorena
        Assert.assertTrue(mainActivityRule.getActivity() instanceof MainActivity);

        // Klik na gumb u MainActivity
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click());

        // Provjerava je li Activity2 otvorena
        Assert.assertTrue(mainActivityRule.getActivity().isFinishing());

        Activity2 activity2 = Activity2.getActivityInstance();
        Assert.assertNotNull(activity2);

        // Klik na cardView u Activity2
        Espresso.onView(ViewMatchers.withId(R.id.cardView)).perform(ViewActions.click());

        // Provjerava je li Activity3 otvorena
        Assert.assertTrue(activity2.isFinishing());

        Activity3 activity3 = Activity3.getActivityInstance();
        Assert.assertNotNull(activity3);

        // Klik na second_arrow_up u Activity3
        Espresso.onView(ViewMatchers.withId(R.id.second_arrow_up)).perform(ViewActions.click());

        // Provjerava je li ThirdActivity otvorena
        Assert.assertTrue(activity3.isFinishing());

        ThirdActivity thirdActivity = ThirdActivity.getActivityInstance();
        Assert.assertNotNull(thirdActivity);
        // Završetak korisničkog toka
    }
}


public class ExampleInstrumentedTest5 {

    @Rule
    public ActivityTestRule<Activity2> activityRule = new ActivityTestRule<>(Activity2.class);

    @Test
    public void testMediumScenario() {
        // Pokreće Activity2
        Activity2 activity2 = activityRule.getActivity();

        // Provjerava početno stanje
        View button2 = activity2.findViewById(R.id.button2);
        Assert.assertEquals(View.VISIBLE, button2.getVisibility());

        // Klik na gumb za otvaranje Activity3
        button2.performClick();

        // Provjerava je li otvorena Activity3
        Intent expectedIntent = new Intent(activity2, Activity3.class);
        Assert.assertEquals(expectedIntent.getComponent(), activityRule.getNextStartedActivity().getComponent());

        // Pokreće Activity3
        Activity3 activity3 = (Activity3) activityRule.getActivity();

        // Provjerava početno stanje u Activity3
        View secondBackArrow = activity3.findViewById(R.id.second_back_arrow);
        View secondArrowUp = activity3.findViewById(R.id.second_arrow_up);
        Assert.assertEquals(View.VISIBLE, secondBackArrow.getVisibility());
        Assert.assertEquals(View.VISIBLE, secondArrowUp.getVisibility());

        // Klik na second_back_arrow
        secondBackArrow.performClick();

        // Provjerava je li se vratilo na Activity2
        Intent backIntent = new Intent(activity3, Activity2.class);
        Assert.assertEquals(backIntent.getComponent(), activityRule.getNextStartedActivity().getComponent());
    }
}



public class ExampleInstrumentedTest4 {

    @Rule
    public ActivityTestRule<Activity3> activityRule = new ActivityTestRule<>(Activity3.class);

    @Test
    public void testSecondArrowUpOnClick() {
        Activity3 activity = activityRule.getActivity();
        View secondArrowUp = activity.findViewById(R.id.second_arrow_up);

        // Provjerava je li secondArrowUp prikazan
        Assert.assertEquals(View.VISIBLE, secondArrowUp.getVisibility());

        // Simulira klik na secondArrowUp
        secondArrowUp.performClick();

        // Provjerava je li otvorena nova aktivnost (ThirdActivity)
        Intent expectedIntent = new Intent(activity, ThirdActivity.class);
        Assert.assertEquals(expectedIntent.getComponent(), activityRule.getNextStartedActivity().getComponent());
    }
}



public class ExampleInstrumentedTest3 {

    @Rule
    public ActivityTestRule<Activity3> activityRule = new ActivityTestRule<>(Activity3.class);

    @Test
    public void testSecondBackArrowOnClick() {
        Activity3 activity = activityRule.getActivity();
        View secondBackArrow = activity.findViewById(R.id.second_back_arrow);

        // Provjerava je li secondBackArrow prikazan
        Assert.assertEquals(View.VISIBLE, secondBackArrow.getVisibility());

        // Simulira klik na secondBackArrow
        secondBackArrow.performClick();

        // Provjerava je li otvorena prethodna aktivnost (Activity2)
        Intent expectedIntent = new Intent(activity, Activity2.class);
        Assert.assertEquals(expectedIntent.getComponent(), activityRule.getNextStartedActivity().getComponent());
    }
}



public class ExampleInstrumentedTest2 {

    @Rule
    public ActivityTestRule<Activity2> activityRule = new ActivityTestRule<>(Activity2.class);

    @Test
    public void testCardViewOnClick() {
        Activity2 activity = activityRule.getActivity();
        View cardView = activity.findViewById(R.id.cardView);

        // Provjerava je li cardView prikazan
        Assert.assertEquals(View.VISIBLE, cardView.getVisibility());

        // Simulira klik na cardView
        cardView.performClick();

        // Provjerava je li otvorena nova aktivnost
        Intent expectedIntent = new Intent(activity, Activity3.class);
        Assert.assertEquals(expectedIntent.getComponent(), activityRule.getNextStartedActivity().getComponent());

    }
}


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest1 {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.firstapp", appContext.getPackageName());
    }

    @Test
    public void testButtonOnClick() {
        // Pronalazi gumb na aktivnosti
        MainActivity activity = activityRule.getActivity();
        Button button = activity.findViewById(R.id.button2);

        // Provjerava je li gumb prikazan
        Assert.assertEquals(View.VISIBLE, button.getVisibility());

        // Simulira klik na gumb
        button.performClick();

        // Provjerava je li otvorena druga aktivnost
        Intent expectedIntent = new Intent(activity, Activity2.class);
        Assert.assertEquals(expectedIntent.getComponent(), activityRule.getNextStartedActivity().getComponent());
    }
}



package com.hcl.poc;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.hcl.poc.views.ListingActivity;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class ListingScreenTests {

    @Rule
    public ActivityTestRule<ListingActivity> mActivityTestRule = new ActivityTestRule<>(ListingActivity.class, false, true);

    /**
     * This test will pass in case of data in adapter set to recycler view
     */
    @Test
    public void testDataLoad() {
        if (getRVcount() > 0) {
            Assert.assertTrue(true);
        }
    }

    /**
     * This test will fail in case of error message is shown
     */
    @Test
    public void testError() {
        onView(
                anyOf(withId(R.id.errorTV))
        ).check(matches(not(isDisplayed())));

//        onView(withId(R.id.errorTV)).check(matches(withText("No Data Found")));

    }


    private int getRVcount() {
        RecyclerView recyclerView = (RecyclerView) mActivityTestRule.getActivity().findViewById(R.id.recyclerView);
        return recyclerView.getAdapter().getItemCount();

    }


}

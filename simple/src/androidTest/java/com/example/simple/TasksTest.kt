package com.example.simple


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tests_common.TestUtils.clickOnRecyclerItem
import com.example.tests_common.TestUtils.waitMore
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class TasksTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun mainTest() {
        waitMore()
        clickOnRecyclerItem(R.id.rvTasks)
        waitMore()
        onView(withId(R.id.tvName)).check(matches(not(withText(""))))
    }
}
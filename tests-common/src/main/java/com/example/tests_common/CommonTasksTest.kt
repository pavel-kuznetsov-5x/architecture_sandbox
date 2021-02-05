package com.example.tests_common


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.tests_common.TestUtils.clickOnRecyclerItem
import com.example.tests_common.TestUtils.waitMore
import org.hamcrest.CoreMatchers.not

object CommonTasksTest {

    fun mainTest(recylcerId: Int, taskNameId: Int) {
        waitMore()
        clickOnRecyclerItem(recylcerId)
        waitMore()
        onView(withId(taskNameId)).check(matches(not(withText(""))))
    }
}
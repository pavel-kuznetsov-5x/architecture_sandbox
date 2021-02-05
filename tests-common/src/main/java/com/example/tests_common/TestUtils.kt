package com.example.tests_common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import java.lang.Thread.sleep


object TestUtils {

    fun viewClick(id: Int) {
        onView(withId(id)).perform(click())
    }

    fun clickAndWait(id: Int) {
        viewClick(id)
        waitSomeTime()
    }

    fun scrollToView(id: Int) {
        onView(withId(id)).perform(scrollTo())
    }

    fun clickOnRecyclerItem(id: Int, position: Int = 0) {
        onView(withId(id)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
    }

    fun clickOnRecyclerItemView(id: Int, itemId: Int) {
        onView(withId(id)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                    MyViewAction.clickChildViewWithId(itemId)
            )
        )
    }

    fun waitSomeTime() {
        sleep(500)
    }

    fun waitMore() {
        sleep(1000)
    }

    object MyViewAction {
        fun clickChildViewWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View>? {
                    return null
                }

                override fun getDescription(): String {
                    return "Click on a child view with specified id."
                }

                override fun perform(uiController: UiController?, view: View) {
                    val v: View = view.findViewById(id)
                    v.performClick()
                }
            }
        }
    }

}
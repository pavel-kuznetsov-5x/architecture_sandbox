package com.spqrta.common.delegates

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.spqrta.common.R

abstract class ToolbarDelegate(val activity: AppCompatActivity, val toolbar: Toolbar) {

    fun setTitle(text: String) {
        toolbar.title = text
    }
}

class AppNameToolbarDelegate(activity: AppCompatActivity, toolbar: Toolbar): ToolbarDelegate(activity, toolbar) {

    init {
        setTitle(activity.resources.getString(R.string.app_name))
        activity.setSupportActionBar(toolbar)
    }

}

class TextToolbarDelegate(activity: AppCompatActivity, toolbar: Toolbar, val text: String): ToolbarDelegate(activity, toolbar) {

    init {
        setTitle(text)
        activity.setSupportActionBar(toolbar)
    }



}
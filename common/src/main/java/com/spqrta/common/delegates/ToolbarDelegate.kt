package com.spqrta.common.delegates

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.spqrta.common.R

abstract class ToolbarDelegate(val activity: AppCompatActivity, val toolbar: Toolbar) {

}

class AppNameToolbarDelegate(activity: AppCompatActivity, toolbar: Toolbar): ToolbarDelegate(activity, toolbar) {

    init {
        toolbar.title = activity.resources.getString(R.string.app_name)
        activity.setSupportActionBar(toolbar)
    }

}
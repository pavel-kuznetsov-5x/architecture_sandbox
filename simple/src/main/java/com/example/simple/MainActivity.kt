package com.example.simple

import android.app.Activity
import android.os.Bundle
import com.spqrta.reusables.base.display.NavActivity

class MainActivity : NavActivity() {
    override val navHostId: Int = R.id.navHost
    override val layoutRes: Int = R.layout.activity_main
}
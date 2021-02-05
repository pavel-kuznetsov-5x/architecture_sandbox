package com.example.full

import com.spqrta.common.base.display.NavActivity

class MainActivity : NavActivity() {
    override val navHostId: Int = R.id.navHost
    override val layoutRes: Int = R.layout.activity_main
}
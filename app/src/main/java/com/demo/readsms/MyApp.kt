package com.demo.readsms

import android.app.Application
import com.demo.screenusage.ScreenUsage

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        ScreenUsage.setPref(applicationContext)
    }
}
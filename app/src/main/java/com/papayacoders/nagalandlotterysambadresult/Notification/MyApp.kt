package com.papayacoders.nagalandlotterysambadresult.Notification

import android.app.Application
import com.onesignal.OneSignal

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val ONESIGNAL_APP_ID = "2282fdc9-126b-4db5-9b0b-d8a3f7077a16"
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}
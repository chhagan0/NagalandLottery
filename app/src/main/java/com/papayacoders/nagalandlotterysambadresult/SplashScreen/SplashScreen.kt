package com.papayacoders.nagalandlotterysambadresult.SplashScreen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import com.papayacoders.nagalandlotterysambadresult.MainActivity
import com.papayacoders.nagalandlotterysambadresult.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val handler=Handler()
        handler.postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
        },2000)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.splashend)
        }
    }
}
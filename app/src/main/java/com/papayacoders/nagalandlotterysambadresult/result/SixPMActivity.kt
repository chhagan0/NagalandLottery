package com.papayacoders.nagalandlotterysambadresult.result

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.papayacoders.nagalandlotterysambadresult.MainActivity
import com.papayacoders.nagalandlotterysambadresult.R
import com.papayacoders.nagalandlotterysambadresult.ResultActivity
import com.papayacoders.nagalandlotterysambadresult.ads.backintersitial
import com.papayacoders.nagalandlotterysambadresult.ads.intertitial.Companion.showAds
import com.papayacoders.nagalandlotterysambadresult.ads.mainapp
import com.papayacoders.nagalandlotterysambadresult.databinding.ActivitySixPmactivityBinding

class SixPMActivity : mainapp() {
    lateinit var binding: ActivitySixPmactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixPmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.red)
        }
        binding.btnback.setOnClickListener {
            if (!isInternetAvailable()) {
                val builder = AlertDialog.Builder(this)
                val view = layoutInflater.inflate(R.layout.alert_dialog, null)
                builder.setView(view)
                val alertDialog = builder.create()
                alertDialog.show()
                builder.setCancelable(false)
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                view.findViewById<CardView>(R.id.btnback).setOnClickListener {
                    startActivity(Intent(this, MainActivity::class.java))
                    alertDialog.dismiss()
                }
            }else {
                val inte = Intent(this, ResultActivity::class.java)
                showAds(this, inte)
            }
        }
        nativeads()
        // Load the Firebase image URL using Glide
        Glide.with(this)
            .load(com.papayacoders.nagalandlotterysambadresult.config.Result.result2)
            .placeholder(R.drawable.noresult) // set a default image whi le loading
            .error(R.drawable.noresult) // set a default image if there is an error
            .into(binding.loadimage)
// Enable zooming and panning on the PhotoView
        val photoView = binding.loadimage
        photoView.maximumScale = 10f // set the maximum scale value
        photoView.mediumScale = 5f // set the medium scale value
        photoView.minimumScale = 1f // set the minimum scale value
        photoView.isZoomable = true // enable zooming

    }

    private fun nativeads() {
        MobileAds.initialize(this)


        val adLoader =
            AdLoader.Builder(this, getString(R.string.native_id2))
                .forNativeAd { nativeAd ->
//                    binding?.loader?.visibility = View.GONE
                    binding?.myTemplate?.visibility = View.VISIBLE

                    val styles =
                        NativeTemplateStyle.Builder().build()
                    val template = findViewById<TemplateView>(R.id.my_template)
                    template.setStyles(styles)
                    template.setNativeAd(nativeAd)
                }
                .withAdListener(object : AdListener() {
                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        binding.myTemplate.visibility = View.VISIBLE
                    }
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        binding.myTemplate.visibility=View.GONE
                        Log.d("CHAGAN", adError.toString())
                    }
                })
                .build()

        adLoader.loadAd(AdRequest.Builder().build())


    }

    override fun onBackPressed() {
        if (!isInternetAvailable()) {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.alert_dialog, null)
            builder.setView(view)
            val alertDialog = builder.create()
            alertDialog.show()
            builder.setCancelable(false)
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            view.findViewById<CardView>(R.id.btnback).setOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
                alertDialog.dismiss()
            }
        }else {
            val inte = Intent(this, ResultActivity::class.java)
            showAds(this, inte)
        }
    }

    override fun onResume() {
        super.onResume()
        MobileAds.initialize(this)

    }
}
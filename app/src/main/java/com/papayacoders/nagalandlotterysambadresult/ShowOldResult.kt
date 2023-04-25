package com.papayacoders.nagalandlotterysambadresult

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.papayacoders.nagalandlotterysambadresult.ads.backintersitial
import com.papayacoders.nagalandlotterysambadresult.ads.intertitial.Companion.showAds
import com.papayacoders.nagalandlotterysambadresult.ads.mainapp
import com.papayacoders.nagalandlotterysambadresult.databinding.ActivityShowOldResultBinding
import com.papayacoders.nagalandlotterysambadresult.result.OldResultActivity

class ShowOldResult : mainapp() {
    lateinit var binding: ActivityShowOldResultBinding
    lateinit var res2: String
    lateinit var res3: String
    lateinit var res1: String
    private lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowOldResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        res1 = intent.getStringExtra("result1").toString()
        res2 = intent.getStringExtra("result2").toString()
        res3 = intent.getStringExtra("result3").toString()
        MobileAds.initialize(this)
        adView = binding.adView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        showbannerad()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
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
                val inte = Intent(this, OldResultActivity::class.java)
                showAds(this, inte)
            }
        }
         show2res()
        show3res()
        show1res()


    }

    private fun show1res() {
        // Load the Firebase image URL using Glide
        Glide.with(this)
            .load(res1.toString())
            .placeholder(R.drawable.noresult) // set a default image whi le loading
            .error(R.drawable.noresult) // set a default image if there is an error
            .into(binding.result1)
// Enable zooming and panning on the PhotoView
        val photoView = binding.result1
        photoView.maximumScale = 10f // set the maximum scale value
        photoView.mediumScale = 5f // set the medium scale value
        photoView.minimumScale = 1f // set the minimum scale value
        photoView.isZoomable = true // enable zooming

    }

    private fun show2res() {
        Glide.with(this)
            .load(res2.toString())
            .placeholder(R.drawable.noresult) // set a default image whi le loading
            .error(R.drawable.noresult) // set a default image if there is an error
            .into(binding.result2)
// Enable zooming and panning on the PhotoView
        val photoView = binding.result2
        photoView.maximumScale = 10f // set the maximum scale value
        photoView.mediumScale = 5f // set the medium scale value
        photoView.minimumScale = 1f // set the minimum scale value
        photoView.isZoomable = true // enable zooming


    }

    private fun show3res() {
        Glide.with(this)
            .load(res3.toString())
            .placeholder(R.drawable.noresult) // set a default image whi le loading
            .error(R.drawable.noresult) // set a default image if there is an error
            .into(binding.result3)
// Enable zooming and panning on the PhotoView
        val photoView = binding.result3
        photoView.maximumScale = 10f // set the maximum scale value
        photoView.mediumScale = 5f // set the medium scale value
        photoView.minimumScale = 1f // set the minimum scale value
        photoView.isZoomable = true // enable zooming


    }

    private fun showbannerad() {
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                adView.visibility = View.VISIBLE
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                d("BANNER", "BANNER FAILED $p0")
                adView.visibility = View.VISIBLE


            }
        }

    }
    override fun onBackPressed() = if (!isInternetAvailable()) {
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
        val inte = Intent(this, OldResultActivity::class.java)
        showAds(this, inte)
    }
    override fun onResume() {
        super.onResume()
        MobileAds.initialize(this)

    }
}
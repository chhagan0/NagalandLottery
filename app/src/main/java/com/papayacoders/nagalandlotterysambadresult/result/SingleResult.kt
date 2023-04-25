package com.papayacoders.nagalandlotterysambadresult.result

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.papayacoders.nagalandlotterysambadresult.MainActivity
import com.papayacoders.nagalandlotterysambadresult.R
import com.papayacoders.nagalandlotterysambadresult.ads.intertitial
import com.papayacoders.nagalandlotterysambadresult.ads.mainapp
import com.papayacoders.nagalandlotterysambadresult.databinding.ActivitySingleResultBinding

class SingleResult : mainapp() {
    lateinit var binding: ActivitySingleResultBinding
    var url: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        url = intent.getStringExtra("result").toString()
        Glide.with(this)
            .load(url.toString())
            .listener(object :RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressbar.visibility=View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
binding.progressbar.visibility=View.GONE
                    return false
                }

            })
            .placeholder(R.drawable.noresult) // set a default image whi le loading
            .error(R.drawable.noresult) // set a default image if there is an error
            .into(binding.result)
// Enable zooming and panning on the PhotoView
        val photoView = binding.result
        photoView.maximumScale = 10f // set the maximum scale value
        photoView.mediumScale = 5f // set the medium scale value
        photoView.minimumScale = 1f // set the minimum scale value
        photoView.isZoomable = true // enable zooming
        MobileAds.initialize(this)
        nativeads()
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
            } else {
                val inte = Intent(this, OldResultActivity::class.java)
                intertitial.showAds(this, inte)
            }
        }
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
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        Log.d("CHAGAN", adError.toString())
                    }
                })
                .build()

        adLoader.loadAd(AdRequest.Builder().build())


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
    } else {
        val inte = Intent(this, OldResultActivity::class.java)
        intertitial.showAds(this, inte)
    }

    override fun onResume() {
        super.onResume()
        MobileAds.initialize(this)

    }
}
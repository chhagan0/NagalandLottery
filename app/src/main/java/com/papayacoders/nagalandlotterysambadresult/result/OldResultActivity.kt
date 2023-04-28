package com.papayacoders.nagalandlotterysambadresult.result

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.papayacoders.nagalandlotterysambadresult.Adapter.MyAdapter
import com.papayacoders.nagalandlotterysambadresult.MainActivity
import com.papayacoders.nagalandlotterysambadresult.R
import com.papayacoders.nagalandlotterysambadresult.ResultActivity
import com.papayacoders.nagalandlotterysambadresult.ads.backintersitial
import com.papayacoders.nagalandlotterysambadresult.ads.intertitial.Companion.showAds
import com.papayacoders.nagalandlotterysambadresult.ads.mainapp
import com.papayacoders.nagalandlotterysambadresult.config.Oldresult
import com.papayacoders.nagalandlotterysambadresult.databinding.ActivityOldResultBinding

class OldResultActivity : mainapp() {
    lateinit var binding: ActivityOldResultBinding
    lateinit var recyclerView: RecyclerView
    lateinit var arrayList: ArrayList<Oldresult>
    var db = Firebase.firestore
    private lateinit var adView: AdView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOldResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        arrayList = arrayListOf()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.purple)
        }
        MobileAds.initialize(this)
        db = FirebaseFirestore.getInstance()
        adView = binding.adView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        showbannerad()
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
                val inte = Intent(this, ResultActivity::class.java)
                showAds(this, inte)
            }

        }
        db.collection("images").get().addOnSuccessListener {
            d("CHAGANN", "db Success: ${it.documents}")

            for (data in it.documents) {
                var result: Oldresult? = data.toObject(Oldresult::class.java)
                if (result != null) {
                    arrayList.add(result)

                }
                recyclerView.adapter = MyAdapter(arrayList)
            }
            binding.progressbar.visibility = View.GONE
        }
            .addOnFailureListener {
                d("CHAGANN", "db Failed: $it")
            }

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
        } else {
            val inte = Intent(this, ResultActivity::class.java)
            showAds(this, inte)
        }
    }

    override fun onResume() {
        super.onResume()
        MobileAds.initialize(this)

    }
}
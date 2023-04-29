package com.papayacoders.nagalandlotterysambadresult

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.papayacoders.nagalandlotterysambadresult.ads.backintersitial
import com.papayacoders.nagalandlotterysambadresult.ads.intertitial
import com.papayacoders.nagalandlotterysambadresult.ads.intertitial.Companion.load
import com.papayacoders.nagalandlotterysambadresult.ads.intertitial.Companion.showAds
import com.papayacoders.nagalandlotterysambadresult.ads.mainapp
import com.papayacoders.nagalandlotterysambadresult.config.Speciallottery
import com.papayacoders.nagalandlotterysambadresult.databinding.ActivityResultBinding
import com.papayacoders.nagalandlotterysambadresult.result.EightPMActivity
import com.papayacoders.nagalandlotterysambadresult.result.OldResultActivity
import com.papayacoders.nagalandlotterysambadresult.result.OnePMActivity
import com.papayacoders.nagalandlotterysambadresult.result.SixPMActivity


class ResultActivity : mainapp() {
    lateinit var binding: ActivityResultBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var adView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this)
        getspeciallottery()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.mustredyellow)
        }
        adView = binding.adView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        showbannerad()
        binding.speciallottery.setOnClickListener {
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
                val inte = Intent(
                    this,
                    com.papayacoders.nagalandlotterysambadresult.result.Speciallottery::class.java
                )
                showAds(this, inte)
            }

            mediaPlayer.pause()

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
                val inte = Intent(this, MainActivity::class.java)
                showAds(this, inte)
            }
            mediaPlayer.pause()

        }
        binding.oldresult.setOnClickListener {
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
                showAds(this, inte)
            }

            mediaPlayer.pause()

        }
        binding.onepm.setOnClickListener {
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
                val inte = Intent(this, OnePMActivity::class.java)
                showAds(this, inte)
            }

            mediaPlayer.pause()

        }
        binding.sixpm.setOnClickListener {
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
                val inte = Intent(this, SixPMActivity::class.java)
                showAds(this, inte)
            }
            mediaPlayer.pause()

        }
        binding.eightpm.setOnClickListener {
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
                val inte = Intent(this, EightPMActivity::class.java)
                showAds(this, inte)
            }

            mediaPlayer.pause()

        }
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        binding.play.setOnClickListener {

            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.playicon.setImageResource(R.drawable.baseline_play_arrow_24)
            } else {
                mediaPlayer.start()
                binding.playicon.setImageResource(R.drawable.baseline_pause_24)
            }
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


        mediaPlayer.pause()
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
            val inte = Intent(this, MainActivity::class.java)
            showAds(this, inte)
        }
    }


    fun getspeciallottery() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("speciallottery")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val data = dataSnapshot.getValue(Speciallottery::class.java)
                var name = dataSnapshot.child("lotteryname").value as String
                var url = dataSnapshot.child("result").value as String
                val value = dataSnapshot.child("visible").getValue(Boolean::class.java)
                name = data?.lotteryname.toString()
                url = data?.result.toString()
                if (Speciallottery.lotteryname.isNullOrBlank()){
                    binding.lotteryname.setText("Bumper")
                }else{

                    binding.lotteryname.setText(Speciallottery.lotteryname)
                }
                if (value == true) {
                    binding.speciallottery.visibility = View.VISIBLE
                } else {
                    binding.speciallottery.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                d("CHAGAN", "Special lottery Firebase $error")
            }
        })

    }

    override fun onResume() {
        super.onResume()
        load(this)
    }

}
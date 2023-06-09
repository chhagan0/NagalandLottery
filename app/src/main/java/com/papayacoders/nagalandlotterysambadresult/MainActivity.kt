package com.papayacoders.nagalandlotterysambadresult

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log.d
import android.view.MenuItem
import android.view.View
import android.view.animation.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.onesignal.OneSignal
import com.papayacoders.nagalandlotterysambadresult.ads.backintersitial
import com.papayacoders.nagalandlotterysambadresult.ads.intertitial.Companion.load
import com.papayacoders.nagalandlotterysambadresult.ads.intertitial.Companion.showAds
import com.papayacoders.nagalandlotterysambadresult.ads.mainapp
import com.papayacoders.nagalandlotterysambadresult.databinding.ActivityMainBinding


class MainActivity : mainapp() {
    lateinit var binding: ActivityMainBinding
    var count = 10
    private var backPressedTime: Long = 0
    private lateinit var toast: Toast
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.mainappbar)
        }
        drawerLayout = findViewById(R.id.maindrawer)
        navigationView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(toggle)

        setSupportActionBar(binding.appbar)

        toggle.syncState()
        navigationView.setCheckedItem(R.id.website)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menuicon)

//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
//
//        // OneSignal Initialization
//        OneSignal.initWithContext(this)
//        OneSignal.setAppId(Companion.ONESIGNAL_APP_ID)

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();

        loadresult()
        nativeads()
        load(this)



        repeatFunctionCall(count)


        navigationView.setNavigationItemSelectedListener {


            when (it.itemId) {

                R.id.website -> {


                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()

                    val url = "http://bongappstore9.com/"
                    customTabsIntent.launchUrl(this, Uri.parse(url))


                }
                R.id.moreapp -> {
                    val uri =
                        Uri.parse("https://play.google.com/store/search?q=pub:bongappstore9&c=apps")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                R.id.privacy -> {
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()

                    val url = "http://bongappstore9.com/lottery-result-today/"
                    customTabsIntent.launchUrl(this, Uri.parse(url))

                }
                R.id.whatsaap -> {
                    val phoneNumber = getString(R.string.phone_number)
                    val message = "hi..."
                    val url =
                        "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"

                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                }
                R.id.share -> {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.send))
                    startActivity(Intent.createChooser(shareIntent, "Complete action using"))

                }

            }
            true

        }



        binding.sendwhatsaap.setOnClickListener {
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
                val phoneNumber = getString(R.string.phone_number)
                val message = "hi..."
                val url =
                    "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}"

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }

        }

        binding.showresult.setOnClickListener {
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
        binding.btncrd.setOnClickListener {
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
        binding.btnlattery.setOnClickListener {
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
    }

    private fun nativeads() {
        MobileAds.initialize(this)


        val adLoader =
            AdLoader.Builder(this, getString(R.string.native_id))
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
                        binding.myTemplate.visibility = View.GONE
                        d("CHAGAN", adError.toString())
                    }
                })
                .build()

        adLoader.loadAd(AdRequest.Builder().build())


    }

    override fun onResume() {
        super.onResume()
        load(this)
        backintersitial.backintersitialload(this)

    }


    fun loadresult() {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("lotterytime")
        val childReference = ref.child("result")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {

                    val result_1 =
                        snapshot.child("result1").child("time1result").getValue(String::class.java)
                    val result_2 =
                        snapshot.child("result2").child("time2result").getValue(String::class.java)
                    val result_3 =
                        snapshot.child("result3").child("time3result").getValue(String::class.java)
                    d("DAMM", result_3.toString())
                    com.papayacoders.nagalandlotterysambadresult.config.Result.result1 =
                        result_1.toString()
//                url?.result1 = result_1.toString()
                    com.papayacoders.nagalandlotterysambadresult.config.Result.result2 =
                        result_2.toString()
                    com.papayacoders.nagalandlotterysambadresult.config.Result.result3 =
                        result_3.toString()
                } catch (e: java.lang.NullPointerException) {
                }


            }

            override fun onCancelled(error: DatabaseError) {
                d("CHAGAN", "Firebase load error :  ${error}")
            }

        })
    }

    fun CardView.fadeInAnimation() {
        val fadeIn = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        fadeIn.duration = 1000 // in milliseconds
        fadeIn.interpolator = AccelerateDecelerateInterpolator()
        fadeIn.start()
    }


    fun repeatFunctionCall(times: Int) {
        if (times > 0) {
            val cardVieww = binding.showresult
            val animation = AnimationUtils.loadAnimation(this, R.anim.cardview_anim)
            cardVieww.startAnimation(animation)
            val handlerr = Handler()
            handlerr.postDelayed({
                min()


            }, 600)

        }
    }

    fun min() {
        count++

        val cardVieww = binding.showresult
        val animation = AnimationUtils.loadAnimation(this, R.anim.reverd_anim)
        cardVieww.startAnimation(animation)
        val handler = Handler()
        handler.postDelayed({

            repeatFunctionCall(count)
        }, 300)
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity()
        } else {
            toast = Toast.makeText(this, R.string.exitappmsg, Toast.LENGTH_SHORT)
            toast.show()
        }
        backPressedTime = System.currentTimeMillis()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        if (toggle.onOptionsItemSelected(item)) {

            return true
        }




        return super.onOptionsItemSelected(item)
    }


}
package com.papayacoders.nagalandlotterysambadresult.ads

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.ads.MobileAds
import com.papayacoders.nagalandlotterysambadresult.MainActivity
import com.papayacoders.nagalandlotterysambadresult.R
 import com.papayacoders.nagalandlotterysambadresult.ads.intertitial.Companion.load

open class mainapp :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
   load(this)
        backintersitial.backintersitialload(this)


    }

//  open fun cheakconnection(context: Context){
//    if (!isInternetAvailable()) {
//        val builder = AlertDialog.Builder(this)
//        val view = layoutInflater.inflate(R.layout.alert_dialog, null)
//        builder.setView(view)
//        val alertDialog = builder.create()
//        alertDialog.show()
//        builder.setCancelable(false)
//        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        view.findViewById<CardView>(R.id.btnback).setOnClickListener {
//startActivity(Intent(this,MainActivity::class.java))
//            alertDialog.dismiss()
//        }
//    }
//}

    open fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    override fun onResume() {
        super.onResume()
 load(this)
        backintersitial.backintersitialload(this)

    }
}
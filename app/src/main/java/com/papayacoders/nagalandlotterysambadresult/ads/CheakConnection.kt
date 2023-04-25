//package com.papayacoders.nagalandlotterysambadresult.ads
//
//import android.content.Context
//import android.content.Intent
//import android.graphics.Color
//import android.graphics.drawable.ColorDrawable
//import android.net.ConnectivityManager
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.cardview.widget.CardView
//import androidx.core.app.ActivityCompat.finishAffinity
//import androidx.core.app.ActivityCompat.startActivity
//import androidx.core.content.ContextCompat.getSystemService
//import com.papayacoders.nagalandlotterysambadresult.MainActivity
//import com.papayacoders.nagalandlotterysambadresult.R
//
//open class CheakConnection:AppCompatActivity() {
//    companion object{
//
//    }
//    fun cheakconnect(context: Context){
//        if (!isInternetAvailable()) {
//            val builder = AlertDialog.Builder(context)
//            val view = layoutInflater.inflate(R.layout.alert_dialog, null)
//            builder.setView(view)
//            val alertDialog = builder.create()
//            alertDialog.show()
//            builder.setCancelable(false)
//            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            view.findViewById<CardView>(R.id.btnback).setOnClickListener {
//startActivity(Intent(context,MainActivity::class.java))
//                alertDialog.dismiss()
//            }
//        }
//    }
//
//    private fun isInternetAvailable(): Boolean {
//        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetworkInfo = connectivityManager.activeNetworkInfo
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected
//    }
//}
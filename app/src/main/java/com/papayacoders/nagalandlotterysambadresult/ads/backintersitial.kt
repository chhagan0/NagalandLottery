package com.papayacoders.nagalandlotterysambadresult.ads

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.util.Log
import android.util.Log.d
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.papayacoders.nagalandlotterysambadresult.R

open class backintersitial {

    companion object {

        var mInterstitialAd: InterstitialAd? = null
        var progress: ProgressDialog? = null

        fun showProgress(context: Context) {

            progress = ProgressDialog(context)
            progress?.setCancelable(false)
            progress?.setMessage(" Ads Loading...")
        }

        fun hideProgress() {
            progress?.hide()
        }

        fun backintersitialload(context: Context) {
            showProgress(context)

//val id:String=   "ca-app-pub-3940256099942544/1033173712"
            val adRequest = AdRequest.Builder().build()
            InterstitialAd.load(
                context,
                context.getString(R.string.intersitial_id2),
//"ca-app-pub-3940256099942544/1033173712"        ,
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                        backintersitialload(context)
                        showProgress(context)
                        Log.d("CHAGAN", "intersitial load   ${adError.toString()}")


                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        mInterstitialAd = interstitialAd
                        hideProgress()


                    }
                })

        }


        fun showbackAds(context: Context, inte: Intent) {

            val sharedPreference = context.getSharedPreferences("ads", MODE_PRIVATE)
            val editor = sharedPreference.edit()
            var count = sharedPreference.getInt("count", 1)
            Log.d("papayacoders", "showAds: $count")

//            if (count % 2 == 0) {
//                Log.d("papayacoders", "showAds: ")

            val progress: ProgressDialog
            progress = ProgressDialog(context)
            progress?.setCancelable(false)
            progress?.setMessage(" Ads Loading...")
            showProgress(context)
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(context as Activity)
                hideProgress()
                mInterstitialAd?.fullScreenContentCallback =
                    object : FullScreenContentCallback() {
                        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                            super.onAdFailedToShowFullScreenContent(p0)
                            d("CHAGAN","Failed intersitial $p0")
                            showbackAds(context, inte)
                            hideProgress()
                        }

                        override fun onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent()
                            hideProgress()
                            context.startActivity(inte)
                        }
                    }
            } else {
                context.startActivity(inte)

            }
//            } else {
//                context.startActivity(inte)
//                Log.d("chagan", "no: $count")
//
//            }

//            editor.putInt("count", ++count)
//            editor.apply()
        }
    }


}
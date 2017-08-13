package net.aquadc.advancedkotlinpatterns

import android.app.Activity
import android.app.Application
import android.app.Fragment
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient

class AdvancedKotlinApp : Application() {

    lateinit var picasso: Picasso
        private set

    override fun onCreate() {
        super.onCreate()
        picasso = Picasso.Builder(this)
                .downloader(OkHttp3Downloader(OkHttpClient()))
                .build()
    }

}

inline val Activity.app get() = application as AdvancedKotlinApp
inline val Fragment.app get() = activity.application as AdvancedKotlinApp
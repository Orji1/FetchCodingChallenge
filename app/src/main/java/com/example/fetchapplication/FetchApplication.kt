package com.example.fetchapplication

import android.app.Application
import com.example.fetchapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FetchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FetchApplication)
            modules(appModule)
        }
    }
}
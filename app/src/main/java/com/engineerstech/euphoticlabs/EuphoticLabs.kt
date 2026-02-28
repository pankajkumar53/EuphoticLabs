package com.engineerstech.euphoticlabs

import android.app.Application
import com.engineerstech.euphoticlabs.data.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EuphoticLabs : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@EuphoticLabs)
            modules(appModule)
        }
    }
}
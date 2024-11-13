package com.hieuminh.app_distribution

import android.app.Application
import com.hieuminh.app_distribution.di.allModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(allModules)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}

package com.tech.assignment

import android.app.Application
import com.tech.assignment.di.appDataModule
import com.tech.assignment.di.appDomainModule
import com.tech.assignment.di.appViewModelModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        val appModules = appViewModelModule + appDomainModule + appDataModule
        startKoin(
            this,
            appModules

        )
    }
}
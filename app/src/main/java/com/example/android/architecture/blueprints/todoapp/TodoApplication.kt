package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import com.example.android.architecture.blueprints.todoapp.di.dataModule
import com.example.android.architecture.blueprints.todoapp.di.coroutineModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
    private fun initDependencyInjection() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@TodoApplication)
            modules(
                coroutineModule,
                dataModule,
                screensModule
            )
        }
    }
}

package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestTodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.applicationContext
            )
            modules(
                testDataModule,
                testAddEditTaskDi,
                testTasksDi,
                testTaskDetailDi
            )
        }
    }
}

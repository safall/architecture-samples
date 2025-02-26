package com.example.android.architecture.blueprints.todoapp.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coroutineModule = module {
    single(named("DefaultCoroutineScope")) {
        CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }
    single(named("DefaultDispatcher")) {
        Dispatchers.Default
    }
    single(named("IoDispatcher")) {
        Dispatchers.IO
    }
}

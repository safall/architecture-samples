package com.example.android.architecture.blueprints.todoapp.statistics

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val statisticsDi = module {
    viewModelOf(::StatisticsViewModel)
}

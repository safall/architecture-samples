package com.example.android.architecture.blueprints.todoapp.tasks

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val tasksDi = module {
    viewModelOf(::TasksViewModel)
}

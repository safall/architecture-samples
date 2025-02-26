package com.example.android.architecture.blueprints.todoapp.addedittask

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val addEditTaskDi = module {
    viewModelOf(::AddEditTaskViewModel)
}

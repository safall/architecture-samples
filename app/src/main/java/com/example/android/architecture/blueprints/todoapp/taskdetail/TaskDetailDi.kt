package com.example.android.architecture.blueprints.todoapp.taskdetail

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val taskDetailDi = module {
    viewModelOf(::TaskDetailViewModel)
}

package com.example.android.architecture.blueprints.todoapp

import androidx.lifecycle.SavedStateHandle
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskViewModel
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailViewModel
import com.example.android.architecture.blueprints.todoapp.tasks.TasksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val testAddEditTaskDi = module {
    viewModel { AddEditTaskViewModel(get(), SavedStateHandle()) }
}

val testTasksDi = module {
    viewModel { TasksViewModel(get(), SavedStateHandle()) }
}

val testTaskDetailDi = module {
    viewModel { TaskDetailViewModel(get(), SavedStateHandle()) }
}

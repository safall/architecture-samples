package com.example.android.architecture.blueprints.todoapp

import com.example.android.architecture.blueprints.todoapp.addedittask.addEditTaskDi
import com.example.android.architecture.blueprints.todoapp.statistics.statisticsDi
import com.example.android.architecture.blueprints.todoapp.taskdetail.taskDetailDi
import com.example.android.architecture.blueprints.todoapp.tasks.tasksDi
import org.koin.dsl.module

val screensModule = module {
    includes(
        addEditTaskDi,
        statisticsDi,
        taskDetailDi,
        tasksDi
    )
}

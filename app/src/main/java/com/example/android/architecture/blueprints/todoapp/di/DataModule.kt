package com.example.android.architecture.blueprints.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.android.architecture.blueprints.todoapp.data.DefaultTaskRepository
import com.example.android.architecture.blueprints.todoapp.data.TaskRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TaskDao
import com.example.android.architecture.blueprints.todoapp.data.source.local.ToDoDatabase
import com.example.android.architecture.blueprints.todoapp.data.source.network.NetworkDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.network.TaskNetworkDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single { provideDataBase(get()) }
    single { provideTaskDao(get()) }
    single<NetworkDataSource> { TaskNetworkDataSource() }
    single<TaskRepository> {
        DefaultTaskRepository(
            get(),
            get(),
            get(named("DefaultDispatcher")),
            get(named("DefaultCoroutineScope"))
        )
    }
}

fun provideDataBase(context: Context): ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, "Tasks.db").build()

fun provideTaskDao(database: ToDoDatabase): TaskDao = database.taskDao()

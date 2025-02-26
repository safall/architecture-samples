package com.example.android.architecture.blueprints.todoapp

import android.content.Context
import androidx.room.Room
import com.example.android.architecture.blueprints.todoapp.data.FakeTaskRepository
import com.example.android.architecture.blueprints.todoapp.data.TaskRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TaskDao
import com.example.android.architecture.blueprints.todoapp.data.source.local.ToDoDatabase
import com.example.android.architecture.blueprints.todoapp.data.source.network.FakeNetworkDataSource
import org.koin.dsl.module

val testDataModule = module {
    single { provideDatabase(get()) }
    single { provideTaskDao(get()) }
    single { FakeNetworkDataSource() }
    single<TaskRepository> { FakeTaskRepository() }

}

fun provideDatabase(context: Context): ToDoDatabase {
    return Room.inMemoryDatabaseBuilder(
        context,
        ToDoDatabase::class.java,
    ).build()
}

fun provideTaskDao(database: ToDoDatabase): TaskDao = database.taskDao()

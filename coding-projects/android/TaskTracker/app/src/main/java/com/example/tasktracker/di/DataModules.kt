package com.example.tasktracker.di

import android.content.Context
import androidx.room.Room
import com.example.tasktracker.data.AppDatabase
import com.example.tasktracker.data.ITaskRepository
import com.example.tasktracker.data.TaskDao
import com.example.tasktracker.data.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This module provides dependencies related to
 * data processing and management using Hilt for dependency injection(provides)
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideTaskDao(database: AppDatabase): TaskDao = database.taskDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "tasklist.db"
        ).build()
    }
}

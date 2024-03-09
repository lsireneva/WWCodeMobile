package com.example.tasktracker.di

import com.example.tasktracker.data.ITaskRepository
import com.example.tasktracker.data.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * This module provides dependencies related to
 * data processing and management using Hilt for dependency injection (bind)
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindTaskRepository(taskRepository: TaskRepository): ITaskRepository
}

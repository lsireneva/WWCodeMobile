package com.example.tasktracker.data

import com.example.tasktracker.data.model.Task

/**
 * defines methods in application
 */
interface ITaskRepository {
    suspend fun updateTask(task: Task)
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
}
package com.example.tasktracker.data

import com.example.tasktracker.data.model.Task

/**
 * defines methods in application
 */
interface ITaskRepository {

    //TODO replace with required method
    suspend fun updateTask(task: Task)

}
package com.example.tasktracker.data

import com.example.tasktracker.data.model.Task
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Gauri Gadkari on 1/23/24.
 */
@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) : ITaskRepository {
    //todo replace with required method
    override suspend fun updateTask(task: Task) {
    }

}
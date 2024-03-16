package com.example.tasktracker.data

import com.example.tasktracker.data.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
class TaskRepository @Inject constructor(private val taskDAO: TaskDAO) : ITaskRepository {
    var allTasks: Flow<List<Task>> = taskDAO.getAllTasks()

    override suspend fun insertTask(task: Task) {
        taskDAO.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDAO.deleteTask(task)
    }

    override suspend fun updateTask(task: Task) {
        taskDAO.updateTask(task)
    }
}
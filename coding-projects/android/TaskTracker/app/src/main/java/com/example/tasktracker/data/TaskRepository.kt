package com.example.tasktracker.data

import androidx.lifecycle.LiveData
import com.example.tasktracker.data.model.Task
import javax.inject.Inject

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
class TaskRepository @Inject constructor(private val taskDAO: TaskDAO) : ITaskRepository {
    var allTasks: LiveData<List<Task>>? = taskDAO.getAllTasks()

    override suspend fun insertTask(task: Task) {
        taskDAO.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDAO.deleteTask(task)
    }

    override suspend fun updateTask(task: Task) {
        taskDAO.updateTask(task)
    }


    //Static list for testing
    companion object {
        // Static list for testing
        val tasks = listOf(
            Task(
                id = 1,
                activityName = "Morning Walk",
                date = "2024-02-26",
                startTimeInMillis = 7 * 60 * 60 * 1000,
                endTimeInMillis = 8 * 60 * 60 * 1000,
                duration = "1h 0m"
            ), Task(
                id = 2,
                activityName = "Read Book",
                date = "2024-02-26",
                startTimeInMillis = 9 * 60 * 60 * 1000,
                endTimeInMillis = 10 * 60 * 60 * 1000,
                duration = "1h 0m"
            ), Task(
                id = 3,
                activityName = "Online Course",
                date = "2024-02-26",
                startTimeInMillis = 11 * 60 * 60 * 1000,
                endTimeInMillis = 13 * 60 * 60 * 1000,
                duration = "2h 0m"
            ), Task(
                id = 4,
                activityName = "Lunch Break",
                date = "2024-02-26",
                startTimeInMillis = 13 * 60 * 60 * 1000,
                endTimeInMillis = 14 * 60 * 60 * 1000,
                duration = "1h 0m"
            ), Task(
                id = 5,
                activityName = "Project Work",
                date = "2024-02-26",
                startTimeInMillis = 15 * 60 * 60 * 1000,
                endTimeInMillis = 18 * 60 * 60 * 1000,
                duration = "3h 0m"
            )
        )
    }
}
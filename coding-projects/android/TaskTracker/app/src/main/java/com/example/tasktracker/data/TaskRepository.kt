package com.example.tasktracker.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.tasktracker.data.model.Task

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
class TaskRepository(application: Application) {

    private var db: AppDatabase? = null
    private var taskDAO = AppDatabase.getDB(application)?.taskDAO()
    var allTasks: LiveData<List<Task>>? = taskDAO?.getAllTasks()

    init{
        this.db = AppDatabase.getDB(application)
    }

    fun insertTask(newTask :Task){
        AppDatabase.databaseQueryExecutor.execute{
            this.taskDAO?.insertTask(newTask)
        }
    }

    //Static list for testing
    companion object {
        // Static list for testing
        val tasks = listOf(
            Task(1,"Walking", "01:35:08", "Yesterday"),
            Task(2,"finishing certifications", "03:40:04", "Yesterday"),
            Task(3,"setting up new dryer unit", "01:20:21", "December 19, Tuesday"),
            Task(4,"coding crunch time", "09:30:10", "December 19, Tuesday")
        )
    }


}
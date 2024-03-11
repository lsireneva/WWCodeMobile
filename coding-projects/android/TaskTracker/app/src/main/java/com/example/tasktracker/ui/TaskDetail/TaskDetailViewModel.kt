package com.example.tasktracker.ui.TaskDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tasktracker.data.TaskRepository
import com.example.tasktracker.data.model.Task

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
class TaskDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository = TaskRepository(application)

    fun insertTask(newTask: Task) {
        repository.insertTask(newTask)
    }

    val allTasks: LiveData<List<Task>>? = repository.allTasks
}
package com.example.tasktracker.ui.TaskDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tasktracker.data.TaskRepository
import com.example.tasktracker.data.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
@HiltViewModel
class TaskDetailViewModel @Inject constructor(private var respository: TaskRepository) :
    ViewModel() {
    val allTasks: LiveData<List<Task>>? = respository.allTasks
    suspend fun insertTask(task: Task) {
        respository.insertTask(task)
    }
}

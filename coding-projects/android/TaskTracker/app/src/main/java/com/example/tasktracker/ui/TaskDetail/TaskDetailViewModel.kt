package com.example.tasktracker.ui.TaskDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktracker.data.TaskRepository
import com.example.tasktracker.data.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
@HiltViewModel
class TaskDetailViewModel @Inject constructor(private var repository: TaskRepository) : ViewModel() {
    private val _task: MutableStateFlow<Task> = MutableStateFlow(Task(0, "", "", "0", "0", "0"))

    fun insertTask(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertTask(task)
                Log.d("TaskDetailViewModel", "$task inserted")
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteTask(task)
            }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateTask(task)
            }
        }
    }

    fun updateActivity(activity: String) {
        _task.update { it.copy(activityName = activity) }
    }
}


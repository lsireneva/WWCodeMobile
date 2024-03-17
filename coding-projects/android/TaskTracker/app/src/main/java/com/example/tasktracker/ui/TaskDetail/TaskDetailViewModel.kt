package com.example.tasktracker.ui.TaskDetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.tasktracker.data.TaskRepository
import com.example.tasktracker.data.model.Task
import com.example.tasktracker.navigation.NavScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private var respository: TaskRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val taskId: String? = savedStateHandle[NavScreens.TaskArgs.TASK_ID_ARG]

    private var _showDeleteButton = MutableStateFlow(false)
    var showDeleteButton: StateFlow<Boolean> = _showDeleteButton

    init {
        _showDeleteButton.update { taskId != "null" }
    }

    suspend fun insertTask(task: Task) {
        respository.insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        respository.deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        respository.updateTask(task)
    }
}

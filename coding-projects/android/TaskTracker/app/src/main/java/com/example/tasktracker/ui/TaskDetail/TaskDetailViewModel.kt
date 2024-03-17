package com.example.tasktracker.ui.TaskDetail

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
data class DetailState(
    val showDeleteButton: Boolean
)


@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val repository: TaskRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val taskId: String? = savedStateHandle[NavScreens.TaskArgs.TASK_ID_ARG]
    private val _deleteState = MutableStateFlow(DetailState(false))
    val detailState: StateFlow<DetailState> = _deleteState

    init {
        _deleteState.update { _deleteState.value.copy(showDeleteButton = (taskId != null)) }
    }

    suspend fun insertTask(task: Task) {
        repository.insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        repository.deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        repository.updateTask(task)
    }
}

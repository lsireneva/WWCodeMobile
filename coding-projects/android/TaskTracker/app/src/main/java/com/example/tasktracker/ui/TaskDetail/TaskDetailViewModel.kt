package com.example.tasktracker.ui.TaskDetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktracker.TimeUtil
import com.example.tasktracker.data.TaskRepository
import com.example.tasktracker.data.model.Task
import com.example.tasktracker.navigation.NavScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
data class DetailState(
    val isEditMode: Boolean,
    val taskId: Int = 0,
    val activityName: String = "",
    val date: String = TimeUtil.convertMillisToDate(Calendar.getInstance().timeInMillis),
    val startTime: String = TimeUtil.convertTime(Calendar.getInstance().time),
    val endTime: String = TimeUtil.convertTime(Calendar.getInstance().time)
)

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val repository: TaskRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val taskId: Int =
        Integer.parseInt(savedStateHandle[NavScreens.TaskArgs.TASK_ID_ARG] ?: "0")
    private val _detailState = MutableStateFlow(DetailState(false))
    val detailState: StateFlow<DetailState> = _detailState

    init {
        if (taskId != 0) {
            loadTask(taskId)
        }
    }

    fun updateActivity(activityName: String) {
        _detailState.update { it.copy(activityName = activityName) }
    }

    fun updateDate(date: String) {
        _detailState.update { it.copy(date = date) }
    }

    fun updateStartTime(startTime: String) {
        _detailState.update { it.copy(startTime = startTime) }
    }

    fun updateEndTime(endTime: String) {
        _detailState.update { it.copy(endTime = endTime) }
    }

    //DB Operations
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
                // TODO - when testing- uncomment this line
                //  task.id = 1
                repository.updateTask(task)
            }
        }
    }

    /**
     * loads current task from database
     */
    private fun loadTask(taskId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getTask(taskId).let {
                    it?.let { task ->
                        _detailState.update { detailState ->
                            detailState.copy(
                                isEditMode = true,
                                taskId = taskId,
                                activityName = task.activityName,
                                date = task.date,
                                startTime = task.startTimeInMillis,
                                endTime = task.endTimeInMillis
                            )
                        }
                    }

                }
            }
        }
    }
}

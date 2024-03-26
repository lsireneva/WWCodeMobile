package com.example.tasktracker.ui.TaskList

import androidx.lifecycle.ViewModel
import com.example.tasktracker.TimeUtil
import com.example.tasktracker.data.TaskDAO
import com.example.tasktracker.data.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
@HiltViewModel
class TaskListViewModel @Inject constructor(private val taskDao: TaskDAO) : ViewModel() {
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
            .map { tasks ->
                tasks.sortedByDescending { task ->
                    TimeUtil.convertDateToMillis(task.date)
                }
            }

}



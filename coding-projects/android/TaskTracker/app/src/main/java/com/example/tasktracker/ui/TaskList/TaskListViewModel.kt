package com.example.tasktracker.ui.TaskList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tasktracker.data.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
class TaskListViewModel: ViewModel() {

    /**
     * following hardcoded strings should eventually be replaced by data based on user input once
     * app persistence is implemented and TaskRepository is modified to retrieve data from the DAO
     */
    fun getAllTasks(): Flow<List<Task>> = flowOf(
        listOf(
            Task(
                1,
                "Walking",
                "Yesterday",
                0,
                0,
                "01:35:08"
            ),
            Task(
                2,
                "finishing certifications",
                "Yesterday",
                0,
                0,
                "03:40:04"
            ),
            Task(
                3,
                "setting up new dryer unit",
                "December 19, Tuesday",
                0,
                0,
                "01:20:21"
            ),
            Task(
                4,
                "coding crunch time",
                "December 19, Tuesday",
                0,
                0,
                "09:30:10"
            )
        )
    )
    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                TaskListViewModel()
            }
        }
    }
}
package com.example.tasktracker

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasktracker.navigation.NavScreens
import com.example.tasktracker.navigation.NavScreens.TaskArgs.TASK_ID_ARG
import com.example.tasktracker.ui.TaskDetail.TaskDetailScreen
import com.example.tasktracker.ui.TaskDetail.TaskDetailViewModel
import com.example.tasktracker.ui.TaskList.TaskListScreen
import com.example.tasktracker.ui.TaskList.TaskListViewModel
import com.example.tasktracker.ui.TaskSettings.TaskSettingsScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreens.TaskList.route) {
        composable(NavScreens.TaskList.route) {
            val viewModel = hiltViewModel<TaskListViewModel>()
            TaskListScreen(
                onNavigateToSettings = { navController.navigate(NavScreens.TaskSettings.route) },
                onNavigateToDetail = { taskId ->
                    navController.navigate("${NavScreens.TaskDetail.route}/${getDetailArg(taskId = taskId)}")
                },
                taskListViewModel = viewModel
            )
        }
        composable(NavScreens.TaskSettings.route) {
            TaskSettingsScreen()
        }
        composable("${NavScreens.TaskDetail.route}/?$TASK_ID_ARG={$TASK_ID_ARG}") {
            val viewModel = hiltViewModel<TaskDetailViewModel>()
            TaskDetailScreen(
                onNavigateToList = { navController.navigate(NavScreens.TaskList.route) },
                taskDetailViewModel = viewModel
            )
        }
    }
}

fun getDetailArg(taskId: Int?): String {
    return if (taskId == null) {
        ""
    } else {
        "?$TASK_ID_ARG=$taskId"
    }
}
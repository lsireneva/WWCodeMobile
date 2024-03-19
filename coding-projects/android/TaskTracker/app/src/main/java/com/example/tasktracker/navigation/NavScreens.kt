package com.example.tasktracker.navigation

sealed class NavScreens(val route: String) {
    object TaskArgs {
        const val TASK_ID_ARG = "taskId"
    }

    companion object {
        private const val LIST_SCREEN_ROUTE = "List"
        private const val SETTINGS_SCREEN_ROUTE = "Settings"
        private const val DETAIL_SCREEN_ROUTE = "Detail"
    }

    object TaskList : NavScreens(LIST_SCREEN_ROUTE)
    object TaskSettings : NavScreens(SETTINGS_SCREEN_ROUTE)
    object TaskDetail : NavScreens(DETAIL_SCREEN_ROUTE)
}
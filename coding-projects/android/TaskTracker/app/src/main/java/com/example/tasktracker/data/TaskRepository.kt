package com.example.tasktracker.data

import com.example.tasktracker.data.model.Task

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
object TaskRepository {

    // these hardcoded strings should eventually be replaced by data based on user input
    val tasks = listOf(
        Task("Walking", "01:35:08", "Yesterday"),
        Task("finishing certifications", "03:40:04", "Yesterday"),
        Task("setting up new dryer unit", "01:20:21", "December 19, Tuesday"),
        Task("coding crunch time", "09:30:10", "December 19, Tuesday")
    )
}
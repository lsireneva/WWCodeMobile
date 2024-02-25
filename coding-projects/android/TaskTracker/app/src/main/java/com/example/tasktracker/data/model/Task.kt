package com.example.tasktracker.data.model

import androidx.room.Entity

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
@Entity
data class Task(val taskTitle: String, val taskTime: String, val date: String)
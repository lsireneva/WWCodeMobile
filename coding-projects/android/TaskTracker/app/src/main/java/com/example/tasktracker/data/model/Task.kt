package com.example.tasktracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Gauri Gadkari on 1/23/24.
 */

@Entity(tableName = "table_task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val taskTitle: String,
    val taskTime: String,
    val date: String
)
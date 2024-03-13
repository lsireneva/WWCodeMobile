package com.example.tasktracker.data

import androidx.room.Dao

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
@Dao
interface TaskDao {
    abstract fun taskDao(): TaskDao
}
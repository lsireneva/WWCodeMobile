package com.example.tasktracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tasktracker.data.model.Task

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
@Dao
interface TaskDAO {

    @Insert
    fun insertTask(newTask: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM table_task")
    fun deleteAllTasks()

    @Query("SELECT * FROM table_task ORDER BY id")
    fun getAllTasks(): LiveData<List<Task>>

}
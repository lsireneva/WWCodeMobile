package com.example.tasktracker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tasktracker.data.AppDatabase
import com.example.tasktracker.data.TaskDAO
import com.example.tasktracker.data.TaskRepository
import com.example.tasktracker.data.model.Task
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var repository: TaskRepository
    private lateinit var database: AppDatabase
    private lateinit var taskDAO: TaskDAO

    @Before
    fun setup() {
        // Context of the app under test.
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        taskDAO = database.taskDAO()
        repository = TaskRepository(taskDAO)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        if (this::database.isInitialized) {
            database.close()
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertTaskAndReadInList() = runTest {
        // Given a Task
        val task = Task(activityName = "Test Activity", date = "2024-03-04", startTimeInMillis = 500, endTimeInMillis = 1000, duration = "1h")

        // When inserting a task
        repository.insertTask(task)

        // Then the task can be retrieved
        val allTasks: List<Task> = getOrAwaitValueMainThread(repository.allTasks)
        assertEquals("Test Activity", allTasks[0].activityName)
    }

    // Util function for Flow testing on the main thread
    private fun <T> getOrAwaitValueMainThread(flowData: Flow<List<Task>>): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val mainScope = MainScope()
        mainScope.launch {
            flowData
                .flowOn(Dispatchers.IO)
                .collect{value ->
                    data = value as T
                    latch.countDown()
                }
        }

        try {
            if(!latch.await(2, TimeUnit.SECONDS)){
                throw TimeoutException("Flow value was never collected")
            }
        }finally {
            mainScope.cancel()
        }
        return data ?: throw NullPointerException("Flow value was null")
    }
}
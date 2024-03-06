package com.example.tasktracker

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.tasktracker.data.AppDatabase
import com.example.tasktracker.data.TaskDAO
import com.example.tasktracker.data.model.Task
import junit.framework.TestCase.assertEquals
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

    private lateinit var database: AppDatabase
    private lateinit var taskDAO: TaskDAO

    @Before
    fun setup() {
        // Context of the app under test.
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        taskDAO = database.taskDAO()
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
        taskDAO.insertTask(task)

        // Then the task can be retrieved
        val allTasks = getOrAwaitValueMainThread(taskDAO.getAllTasks())
        assertEquals("Test Activity", allTasks[0].activityName)
    }

    // Util function for LiveData testing on the main thread
    private fun <T> getOrAwaitValueMainThread(liveData: LiveData<T>): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = Observer<T> { o ->
            data = o
            latch.countDown()
        }

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            liveData.observeForever(observer)
        }

        try {
            if (!latch.await(2, TimeUnit.SECONDS)) {
                throw TimeoutException("LiveData value was never set.")
            }
        } finally {
            InstrumentationRegistry.getInstrumentation().runOnMainSync {
                liveData.removeObserver(observer)
            }
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }



}
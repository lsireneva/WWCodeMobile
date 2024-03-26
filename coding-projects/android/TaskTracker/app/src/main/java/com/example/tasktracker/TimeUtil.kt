package com.example.tasktracker

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

class TimeUtil {
    companion object {
        fun getDaysOfWeekShort(): List<String> {
            return DayOfWeek.values().map {
                it.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            }
        }

        fun isToday(dayOfWeek: String): Boolean {
            val c = Calendar.getInstance().time
            val day = SimpleDateFormat("EE", Locale.getDefault()).format(c)
            return day == dayOfWeek
        }

        fun convertMillisToDate(millis: Long): String {
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            return formatter.format(Date(millis))
        }

        fun convertTime(time: Date): String {
            val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
            return formatter.format(time).toString()
        }

        fun convertDateToMillis(date: String): Long {
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            return formatter.parse(date).time
        }
        fun calculateDuration(startTime: String, endTime: String): String {
            val format = SimpleDateFormat("HH:mm", Locale.getDefault())
            return try {
                val start = format.parse(startTime)?.time ?: 0L
                val end = format.parse(endTime)?.time ?: 0L

                val durationInMillis = end - start

                val hours = durationInMillis / (3600 * 1000)
                val minutes = (durationInMillis % (3600 * 1000)) / (60 * 1000)
                val seconds = (durationInMillis % (60 * 1000)) / 1000

                // Format and return as a string
                String.format("%02d:%02d:%02d", hours, minutes, seconds)
            } catch (e: ParseException) {
                "00:00:00"
            }
        }
    }
}

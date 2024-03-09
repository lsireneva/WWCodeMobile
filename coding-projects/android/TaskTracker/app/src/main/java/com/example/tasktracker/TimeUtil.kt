package com.example.tasktracker

import android.os.Build
import androidx.annotation.RequiresApi
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
    }
}

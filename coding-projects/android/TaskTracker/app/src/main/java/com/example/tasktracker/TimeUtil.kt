package com.example.tasktracker

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

class TimeUtil {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getDaysOfWeekFull(): List<String> {
            return DayOfWeek.values().map {
                it.getDisplayName(TextStyle.FULL, Locale.getDefault())
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getDaysOfWeekShort(): List<String> {
            return DayOfWeek.values().map {
                it.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getDaysOfWeekNarrow(): List<String> {
            return DayOfWeek.values().map {
                it.getDisplayName(TextStyle.NARROW, Locale.getDefault())
            }
        }
    }
}

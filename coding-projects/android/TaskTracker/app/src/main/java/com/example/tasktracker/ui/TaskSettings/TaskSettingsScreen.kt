package com.example.tasktracker.ui.TaskSettings

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasktracker.R
import com.example.tasktracker.TimeUtil



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskSettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    /*
                        TODO - #155 - Add a header text, play with `fontSize` or `fontStyle` and remove the
                        "This is the settings screen" text now that we no longer need it.
                     */
                }
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValue.calculateTopPadding(),
                    start = 12.dp,
                    end = 12.dp
                )
                .fillMaxSize(),
        ) {
            Text(text = "This is the settings screen")
            // TODO - #156 - Add a section header for info and feedback, play with `fontSize` or `fontStyle` in your Text composable
            Card(modifier = Modifier.fillMaxWidth()){
                // TODO - #158 - Add an About Us row with an icon and a text
                // TODO - #163 - Add a Privacy Policy row with an icon and a text
                // TODO - #164 - Add a Tutorial row with an icon and text
                // TODO - #165 - Add a Rate app row with an icon and text
                // TODO - #166 - Add a Follow us on Twitter row with an icon and text
            }
            // TODO - #167 - Add a section header for Notifications

                Text(
                    stringResource(R.string.days).uppercase(),
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.small_padding)),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.small_padding)),
                    horizontalArrangement = Arrangement
                        .spacedBy(dimensionResource(R.dimen.small_padding))
                ) {
                    for (day in TimeUtil.getDaysOfWeekShort()) {
                            DayOfWeekItem(day)
                        }
                    }
                }
                // TODO - #174 - Add a "Task Reminder" row
                // TODO - #175 - Add a "Show Badge" row
                // TODO - #176 - Add a "Reminder Time" row
            }
            // TODO - #168 - Add a section header for "What's New?"
            Card(modifier = Modifier.fillMaxWidth()){
                // TODO - #172 - Add a "Vote on Future Requests row"
            }

            // TODO - #157 - Add a section header for appearance, play with `fontSize` or `fontStyle in your Text composable`
            Card(modifier = Modifier.fillMaxWidth()) {
                // TODO - #171 - Add an App Theme row
                // TODO - #170 - Add an App Icon row
            }
            VersionFooter(version = getFullVersionName())
        }


@Composable
fun DayOfWeekItem(day: String) {

    val isToday = TimeUtil.isToday(day)

    val backgroundColor = if (isToday) Color.Green else Color.LightGray

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color = backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(day,
            style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun TaskSettingsPreview() {
    TaskSettingsScreen()
}

@Composable
fun VersionFooter(version: String) {
    Text(
        text = "Version $version",
        color = Color.Gray,
        style = MaterialTheme.typography.labelSmall
    )
}

@Composable
fun getFullVersionName(): String {
    val context = LocalContext.current
    val packageInfo: PackageInfo? = try {
        context.packageManager.getPackageInfo(context.packageName, 0)
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }
    return packageInfo?.versionName ?: "Unknown"
}


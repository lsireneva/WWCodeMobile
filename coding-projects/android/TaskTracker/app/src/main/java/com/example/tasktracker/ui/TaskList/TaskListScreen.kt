package com.example.tasktracker.ui.TaskList

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasktracker.R
import com.example.tasktracker.data.model.Task

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    onNavigateToSettings: () -> Unit,
    viewModel: TaskListViewModel = viewModel(factory = TaskListViewModel.factory)
) {
    val allTasks by viewModel.getAllTasks().collectAsState(emptyList())

    Scaffold(
        topBar = {
            ListScreenTopAppBar(
                { onNavigateToSettings() }
            )
        }
    ) {
        TaskList(
            taskList = allTasks,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
fun TaskList(
    taskList: List<Task>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(R.dimen.medium_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.medium_padding)),
    ) {
        item {
            Text(
                text = stringResource(R.string.activity_heading),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
        }
        val grouped = taskList.groupBy { it.date }

        grouped.forEach { (date, tasks) ->
            item {
                Header(date)
            }

            items(tasks) { task ->
                TaskCard(
                    task = task,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            dimensionResource(R.dimen.border_thickness),
                            Color.Black,
                            RoundedCornerShape(dimensionResource(R.dimen.small_round_corner))
                        )
                )
            }
        }
    }
}

@Composable
fun Header(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.small_padding),
                top = dimensionResource(id = R.dimen.medium_padding)
            ),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreenTopAppBar(
    onNavigateToSettings: () -> Unit
) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .padding
                        (
                        start = dimensionResource(R.dimen.app_bar_padding),
                        top = dimensionResource(R.dimen.app_bar_padding)
                    )
            ) {
                Text(
                    text = stringResource(R.string.timer),
                    fontWeight = FontWeight.Medium,
                    fontSize = 28.sp
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.AddCircle,
                    contentDescription = "Add Button"
                )
            }
            IconButton(onClick = { onNavigateToSettings() }) {
                Icon(
                    painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = stringResource(id = R.string.settings)
                )
            }
        }

    )
}


@Preview(showBackground = true)
@Composable
private fun TopAppBarPreview() {
    ListScreenTopAppBar(
        onNavigateToSettings = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun TaskListPreview() {
    TaskList(
        listOf(
            Task(1, "Walking", "Yesterday", 0, 0, "01:35:08"),
            Task(2, "finishing certifications", "Yesterday", 0, 0, "03:40:04"),
            Task(3, "setting up new dryer unit", "December 19, Tuesday", 0, 0, "01:20:21"),
            Task(4, "coding crunch time", "December 19, Tuesday", 0, 0, "09:30:10")
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreview() {
    TaskListScreen(onNavigateToSettings = {})
}


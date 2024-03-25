package com.example.tasktracker.ui.TaskList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasktracker.R
import com.example.tasktracker.data.model.Task

/**
 * Created by Gauri Gadkari on 1/23/24.
 */
@Composable
fun TaskCard(task: Task, modifier: Modifier = Modifier, onClick: (Task) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                isExpanded = !isExpanded
                onClick(task) },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = task.activityName,
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(R.dimen.medium_padding)),
                style = MaterialTheme.typography.bodySmall,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                overflow = TextOverflow.Ellipsis

            )
            Text(
                text = task.duration,
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(R.dimen.medium_padding)),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun TaskCardPreview() {
    TaskCard(Task(1, "Walking", "Yesterday", "0", "0", "01:35:08"), onClick = {})
}




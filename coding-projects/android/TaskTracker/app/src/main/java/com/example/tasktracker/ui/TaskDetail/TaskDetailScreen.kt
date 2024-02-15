package com.example.tasktracker.ui.TaskDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.tasktracker.R
import com.example.tasktracker.ui.theme.Green


/**
 * Created by Gauri Gadkari on 1/23/24.
 * Screen to display task details and add, edit or delete task
 * Developed compose UI by Liubov Sireneva on 1/29/24
 * Added DetailDialog and Delete flow by Liubov Sireneva 2/14/24
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen() {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(dimensionResource(R.dimen.border_thickness_1), Color.Black),
        modifier = Modifier
            .padding(dimensionResource(R.dimen.medium_padding))
            .wrapContentSize()
            .verticalScroll(rememberScrollState()),
        shape = RoundedCornerShape(dimensionResource(R.dimen.detail_card_shape))

    ) {
        val dateInfo =
            "JAN 29 2024" /*TODO when data persistence will be ready*/
        val startTimeInfo = "08:22:10" /*TODO when data persistence will be ready*/
        val endTimeInfo = "09:12:01" /*TODO when data persistence will be ready*/
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.medium_padding)),
            horizontalArrangement = Arrangement.End
        ) {
            val showDialog = remember { mutableStateOf(false) }

            IconButton(onClick = { showDialog.value = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_delete_24),
                    contentDescription = stringResource(id = R.string.delete),
                )
                if (showDialog.value) {
                    DetailDialog(
                        text = stringResource(id = R.string.detail_delete_flow),
                        onDismissRequest = { showDialog.value = false },
                        onConfirmation = {/*TODO when data persistence will be ready*/ })
                }
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_cancel_24),
                    contentDescription = stringResource(id = R.string.cancel),
                    tint = Color.Red
                )
            }
        }

        LabelButtonRow(
            label = stringResource(id = R.string.date_label).uppercase(),
            buttonInfo = dateInfo
        ) {}
        var textState by remember { mutableStateOf("") }

        TextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier
                .padding(dimensionResource(R.dimen.medium_padding))
                .fillMaxWidth()
                .sizeIn(minHeight = dimensionResource(R.dimen.detail_textfield_min_height))
                .border(
                    dimensionResource(R.dimen.border_thickness_1),
                    Color.Gray,
                    RoundedCornerShape(5)
                ),
            label = { Text(text = stringResource(id = R.string.textfield_label)) },
            maxLines = 20,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color.Gray,
                focusedLabelColor = Color.Gray,
                textColor = Color.Black,
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
        )

        LabelButtonRow(
            label = stringResource(id = R.string.start_time_label).uppercase(),
            buttonInfo = startTimeInfo
        ) {}
        LabelButtonRow(
            label = stringResource(id = R.string.end_time_label).uppercase(),
            buttonInfo = endTimeInfo
        ) {}

        OutlinedButton(
            onClick = { },
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color.White, contentColor = Green
            ),
            border = BorderStroke(dimensionResource(R.dimen.border_thickness_1), Green),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    top = dimensionResource(R.dimen.detail_done_button_padding),
                    bottom = dimensionResource(R.dimen.medium_padding)
                )
                .size(
                    dimensionResource(R.dimen.detail_done_button_width),
                    dimensionResource(R.dimen.detail_done_button_padding)
                ),
        ) {
            Text(text = stringResource(id = R.string.done).uppercase())
        }
    }
}

@Composable
fun DetailDialog(text: String, onDismissRequest: () -> Unit, onConfirmation: () -> Unit) {

    Dialog(onDismissRequest = { onDismissRequest() }) {
        OutlinedCard(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(dimensionResource(R.dimen.border_thickness_1), Color.Black),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.medium_padding))
                .wrapContentSize(),
            shape = RectangleShape
        ) {

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(dimensionResource(R.dimen.medium_padding)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(onClick = onDismissRequest) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close)
                    )
                }
                Text(
                    modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding)),
                    text = text,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensionResource(R.dimen.medium_padding),
                            vertical = dimensionResource(R.dimen.small_padding)
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    GrayButton(buttonText = stringResource(id = R.string.ok), onConfirmation)
                    GrayButton(buttonText = stringResource(id = R.string.cancel), onDismissRequest)
                }
            }

        }
    }
}

@Composable
fun GrayButton(buttonText: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(
            dimensionResource(R.dimen.detail_gray_button_width),
            dimensionResource(R.dimen.detail_gray_button_height)
        ),
        colors = ButtonDefaults.buttonColors(Color.DarkGray),
        shape = RoundedCornerShape(dimensionResource(R.dimen.detail_button_corner_shape))
    ) {
        Text(text = buttonText)
    }
}

@Composable
fun LabelButtonRow(label: String, buttonInfo: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.medium_padding),
                vertical = dimensionResource(R.dimen.small_padding)
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            modifier = Modifier.align(Alignment.CenterVertically),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            modifier = Modifier.align(Alignment.CenterVertically),
            shape = RoundedCornerShape(dimensionResource(R.dimen.detail_button_corner_shape)),
            onClick = onClick,
            colors = ButtonDefaults.textButtonColors(
                containerColor = Green, contentColor = Color.White
            )
        ) {
            Text(text = buttonInfo, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailScreenPreview() {
    TaskDetailScreen()
}

@Preview(showBackground = true)
@Composable
fun TaskDetailDialogPreview() {
    DetailDialog(
        text = "Are you sure you want to delete this entry?",
        onDismissRequest = {}) {
    }
}


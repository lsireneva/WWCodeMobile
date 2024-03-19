package com.example.tasktracker.ui.TaskDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.tasktracker.TimeUtil
import com.example.tasktracker.ui.theme.Green
import java.util.Calendar


/**
 * Created by Gauri Gadkari on 1/23/24.
 * Screen to display task details and add, edit or delete task
 * Developed compose UI by Liubov Sireneva on 1/29/24
 */
@Composable
fun TaskDetailScreen(
    onNavigateToList: () -> Unit, taskDetailViewModel: TaskDetailViewModel
) {
    val (showCancelConfirmationPopup, setShowCancelConfirmationPopup) = remember {
        mutableStateOf(
            false
        )
    }
    val (showDeleteConfirmationPopup, setShowDeleteConfirmationPopup) = remember {
        mutableStateOf(
            false
        )
    }

    val uiState by taskDetailViewModel.detailState.collectAsState()

    // Function to handle cancel confirmation
    val onCancelConfirmed = {
        setShowCancelConfirmationPopup(false)
        onNavigateToList()
    }
    val onDeleteConfirmed = {
        setShowDeleteConfirmationPopup(false)
        // delete from db
        //taskDetailViewModel.deleteTask()
    }
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(dimensionResource(R.dimen.detail_border_thickness), Color.Black),
        modifier = Modifier
            .padding(dimensionResource(R.dimen.medium_padding))
            .wrapContentSize()
            .verticalScroll(rememberScrollState()),
        shape = RoundedCornerShape(dimensionResource(R.dimen.detail_card_shape))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.medium_padding)),
            horizontalArrangement = Arrangement.End
        ) {
            if (uiState.showDeleteButton) {
                DeleteButton (onClick = {setShowDeleteConfirmationPopup(true)})
            }
            CancelButton(onClick = { setShowCancelConfirmationPopup(true) })
        }

        if (showCancelConfirmationPopup) {
            ConfirmationDialog(message = stringResource(id = R.string.cancel_popup_message),
                onConfirm = onCancelConfirmed,
                onCancel = { setShowCancelConfirmationPopup(false) })
        }

        DetailDateButton()
        
        if (showDeleteConfirmationPopup){
            ConfirmationDialog(message = stringResource(id = R.string.delete_popup_message),
                onConfirm = onDeleteConfirmed,
                onCancel = {setShowDeleteConfirmationPopup(false)})
        }

        var textState by remember { mutableStateOf("") }

        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier
                .padding(dimensionResource(R.dimen.medium_padding))
                .fillMaxWidth()
                .sizeIn(minHeight = dimensionResource(R.dimen.detail_textfield_min_height)),
            placeholder = { Text(text = stringResource(id = R.string.textfield_label)) },
            maxLines = 20,
        )

        TimePickerRow(stringResource(id = R.string.start_time_label))
        TimePickerRow(stringResource(id = R.string.end_time_label))

        OutlinedButton(
            onClick = { onNavigateToList() },
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color.White, contentColor = Green
            ),
            border = BorderStroke(dimensionResource(R.dimen.detail_border_thickness), Green),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    top = dimensionResource(R.dimen.detail_done_button_padding),
                    bottom = dimensionResource(R.dimen.small_padding)
                ),
            contentPadding = PaddingValues(
                horizontal = dimensionResource(R.dimen.detail_done_button_inside_padding),
                vertical = dimensionResource(R.dimen.small_padding)
            )
        ) {
            Text(text = stringResource(id = R.string.done).uppercase())
        }
    }
}

@Composable
fun DetailDateButton() {
    var date by remember {
        mutableStateOf(TimeUtil.convertMillisToDate(Calendar.getInstance().timeInMillis))
    }

    var showDatePicker by remember { mutableStateOf(false) }

    LabelButtonRow(
        label = stringResource(id = R.string.date_label).uppercase(), buttonInfo = date
    ) { showDatePicker = true }

    if (showDatePicker) {
        DetailDatePickerDialog(onDateSelected = { date = it },
            onDismiss = { showDatePicker = false })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailDatePickerDialog(
    onDateSelected: (String) -> Unit, onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    val selectedDate = datePickerState.selectedDateMillis?.let {
        TimeUtil.convertMillisToDate(it)
    } ?: ""


    DatePickerDialog(onDismissRequest = { onDismiss() }, confirmButton = {
        Button(onClick = {
            onDateSelected(selectedDate)
            onDismiss()
        }

        ) {
            Text(text = stringResource(id = R.string.ok).uppercase())
        }
    }, dismissButton = {
        Button(onClick = {
            onDismiss()
        }) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }) {
        DatePicker(state = datePickerState)
    }
}


@Composable
fun TimePickerRow(timeRowLabel: String) {
    var time by remember {
        mutableStateOf(TimeUtil.convertTime(Calendar.getInstance().time))
    }
    var showTimePicker by remember { mutableStateOf(false) }
    LabelButtonRow(
        label = timeRowLabel.uppercase(), buttonInfo = time
    ) { showTimePicker = true }

    if (showTimePicker) {
        DetailTimePickerDialog(onTimeSelected = { time = it },
            onDismiss = { showTimePicker = false })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTimePickerDialog(onTimeSelected: (String) -> Unit, onDismiss: () -> Unit) {

    val currentTime = TimeUtil.convertTime(Calendar.getInstance().time)
    val (hour, minute) = currentTime.split(":")


    val timePickerState = rememberTimePickerState(
        initialHour = hour.toInt(), initialMinute = minute.toInt(), is24Hour = false
    )
    val selectedTime = String.format("%02d:%02d", timePickerState.hour, timePickerState.minute)


    TimePickerDialog(onDismissRequest = { onDismiss() }, onConfirm = {
        onTimeSelected(selectedTime)
        onDismiss()
    }) {
        TimePicker(state = timePickerState)
    }
}

@Composable
fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit,
) {
    AlertDialog(onDismissRequest = { onDismissRequest() },
        title = { Text(text = stringResource(id = R.string.select_time)) },
        text = { content() },
        confirmButton = {
            Button(onClick = { onConfirm() }) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            Button(onClick = { onDismissRequest() }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        })
}

@Composable
fun LabelButtonRow(label: String, buttonInfo: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.medium_padding),
                vertical = dimensionResource(R.dimen.small_padding)
            ), horizontalArrangement = Arrangement.SpaceBetween
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
            Text(text = buttonInfo, fontSize = 14.sp)
        }
    }
}

@Composable
fun CancelButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_cancel_24),
            contentDescription = stringResource(id = R.string.cancel),
            tint = Color.Red
        )
    }
}

@Composable
fun DeleteButton(onClick: () -> Unit){
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.outline_delete_24),
            contentDescription = stringResource(id = R.string.delete),
        )
    }
}

@Composable
fun ConfirmationDialog(
    message: String, onConfirm: () -> Unit, onCancel: () -> Unit
) {
    AlertDialog(onDismissRequest = onCancel, text = { Text(text = message) }, confirmButton = {
        Button(onClick = { onConfirm() }) {
            Text(text = stringResource(id = R.string.ok))
        }
    }, dismissButton = {
        Button(onClick = { onCancel() }) {
            Text(text = stringResource(id = R.string.cancel))
        }
    })
}


@Preview(showBackground = true)
@Composable
fun TaskDetailScreenPreview() {
    TaskDetailScreen(onNavigateToList = {}, viewModel())
}

@Preview(showBackground = true)
@Composable
fun ConfirmationDialogPreview() {
    ConfirmationDialog(message = stringResource(id = R.string.cancel_popup_message),
        onConfirm = { },
        onCancel = { })
}


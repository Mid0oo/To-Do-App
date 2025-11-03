package com.example.to_doapp.ui.screens.taskscreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytrainning.R
import com.example.to_doapp.model.TaskUi
import com.example.to_doapp.ui.Labels
import com.example.to_doapp.ui.components.LabelSection
import com.example.to_doapp.ui.components.PinButton
import com.example.to_doapp.ui.components.TodoCheckbox
import com.example.to_doapp.ui.theme.Graphik

@Composable
fun TaskScreen(
    todoId: Long,
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()


    LaunchedEffect(todoId) {
        viewModel.loadTodo(todoId)
    }
    BackHandler {
        uiState.todo?.let { todo ->
            viewModel.deleteIfEmpty(
                todoId = todoId,
                title = todo.title,
                tasksCount = todo.tasks.size
            )
        }
        navController.popBackStack()
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding()
    ) { paddingValues ->
        uiState.todo?.let { todo ->
            TaskScreenContent(
                title = todo.title,
                tasks = todo.tasks,
                selectedLabel = todo.label,
                isPinned = todo.isPinned,
                onTitleChange = viewModel::onTitleChange,
                onTaskCheckedChange = { task -> viewModel.checkTask(task) },
                onTaskTextChange = { updated -> viewModel.updateTask(updated) },
                onAddTask = { viewModel.addTask() },
                onPinClick = { viewModel.pinTodo() },
                onDeleteTask = { viewModel.deleteTask(it) },
                onLabelChange = { viewModel.onLabelChange(it) },
                onBackClick = {
                    viewModel.deleteIfEmpty(
                        todoId,
                        todo.title,
                        todo.tasks.size
                    )
                    navController.popBackStack() },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
fun TaskScreenContent(
    title: String,
    tasks: List<TaskUi>,
    selectedLabel: String,
    isPinned: Boolean,
    onTitleChange: (String) -> Unit,
    onTaskCheckedChange: (TaskUi) -> Unit,
    onTaskTextChange: (TaskUi) -> Unit,
    onAddTask: () -> Unit,
    onPinClick: () -> Unit,
    onLabelChange: (String) -> Unit,
    onDeleteTask: (TaskUi) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.weight(1f))
            PinButton(
                onClick = {onPinClick()},
                isPinned = isPinned
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        BasicTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = Graphik,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(tasks, key = { it.id }) { task ->
                TodoCheckbox(
                    checked = task.isDone,
                    onCheckedChange = { onTaskCheckedChange(task) },
                    onValueChange = { newText ->
                        val updatedTask = task.copy(title = newText)
                        onTaskTextChange(updatedTask)
                    },
                    onDeleted = { onDeleteTask(task) },
                    onEnter = { onAddTask() },
                    text = task.title,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .clickable { onAddTask() }
                ) {
                    Box(
                        modifier = Modifier
                            .size(15.dp)
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .border(
                                width = 2.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(4.dp)
                            )
                        ,
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "Add task",
                            tint = Color.Black,
                            modifier = Modifier.size(8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "To-do",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontFamily = Graphik,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        LabelSection(
            selectedLabel = selectedLabel,
            onSelectedLabel = { onLabelChange(it) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    TaskScreenContent(
        title = "Self-care List",
        tasks = listOf(),
        selectedLabel = Labels.PERSONAL,
        isPinned = true,
        onTitleChange = {},
        onTaskCheckedChange = {},
        onTaskTextChange = {},
        onAddTask = {},
        onPinClick = {},
        onLabelChange = {},
        onBackClick = {},
        onDeleteTask = {}
    )
}

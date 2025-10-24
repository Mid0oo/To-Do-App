package com.example.to_doapp.ui.screens.taskscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mytrainning.R
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
){
    val state = viewModel.state.collectAsState()
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
    ) { paddingValues ->
        TaskScreenContent(
            onBackClick = {navController.popBackStack()},
            modifier = Modifier.padding(paddingValues)
        )
    }


}

@Composable
fun TaskScreenContent(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var tasks by remember { mutableStateOf(listOf("" to false)) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .clickable{onBackClick()}
            )
            Spacer(modifier = Modifier.weight(1f))
            PinButton(
                onClick = {},
                isPinned = false,
                modifier = Modifier
            )

        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            item {
                BasicTextField(
                    value = "Title",
                    onValueChange = {},
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontFamily = Graphik,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .padding(vertical = 26.dp)
                        .weight(1f)
                )
            }
            items(tasks.size){ it ->
                val (text,checked) = tasks[it]

                TodoCheckbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        tasks= tasks.toMutableList().apply {
                            this[it] = text to isChecked
                        }
                    },
                    onValueChange = {newText ->
                        tasks = tasks.toMutableList().apply {
                            this[it] = newText to checked
                        }
                    },
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
        LabelSection(
            selectedLabel = Labels.WORK,
            onSelectedLabel = {},
            )

    }

}
@Preview(showBackground = true)
@Composable
fun TaskScreenPreview(){
    TaskScreenContent(onBackClick = {})
}
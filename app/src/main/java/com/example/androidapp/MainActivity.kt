package com.example.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidapp.ui.theme.AndroidAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidAppTheme {
                TodoView()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TodoView() {
    val todos = remember {mutableStateListOf<Todo>()}
    var showList by remember {mutableStateOf(false)}
    if (showList) {
        TodoListView(
            todos = todos,
            onBack = {showList = false}
        )
    }
    if (!showList){
        AddTodoView(
            todos = todos,
            onGoList = {showList = true}
        )
    }
}
@Composable
fun AddTodoView(
    todos: MutableList<Todo>,
    onGoList: () -> Unit) {
    var newTodo by remember {mutableStateOf("")}

    Column(
        modifier = Modifier
            .padding(10.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add a new TODO!")
        TextField(
            value = newTodo,
            onValueChange = {newTodo = it},
            label = {Text("Input a new TODO:")}
        )
        Button(
            onClick = {
                todos.add(Todo(newTodo))
                newTodo = ""
            }
        )
        {
            Text("Add!")
        }
        Button(
            onClick = onGoList
        ) {
            Text("Go to List of todos!")
        }
    }
}

@Composable
fun TodoListView(
    todos: MutableList<Todo>,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("My Todo List")

        Button(
            onClick = onBack
        ) {
            Text("Go back")
        }
    }

    if (todos.isEmpty()) {
        Text("No todos yet!")
    } else {
        for (todo in todos) {
            Text(todo.todo)
        }
    }
}
package com.example.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
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
import java.util.Date

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
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            . padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add a new Todo!",
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onBackground,)
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newTodo,
                onValueChange = {newTodo = it},
                label = {Text("Input a new TODO:")},
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(
                modifier = Modifier
                    .width(10.dp)
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

        }

        Button(
            onClick = onGoList,
            modifier = Modifier
                .padding(top = 10.dp)
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
            .background(MaterialTheme.colorScheme.background)

            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("My Todo List",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )

        Button(
            onClick = onBack,
            modifier = Modifier
                .padding(bottom = 20.dp)
        ) {
            Text("Go back")
        }

        if (todos.isEmpty()) {
            Text("No todos yet!",
                color = MaterialTheme.colorScheme.onBackground)
        } else {
            for ((index, todo) in todos.withIndex()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = todo.done,
                        onCheckedChange = {isChecked ->
                            todos[index] = todo.copy(done = isChecked)
                        }
                    )

                    Text(
                        text = todo.todo,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp)
                    )

                    Button(
                        onClick = {
                            todos.removeAt(index)
                        }
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }


}
package com.dsm.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dsm.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addTodoButton = findViewById(R.id.addTodoButton) as Button

        //TODO: Declarar navController

        // set on-click listener
        addTodoButton.setOnClickListener {
            // your code to perform when the user clicks on the button
            //navController.navigate(R.id.action_mainActivity_to_addTodoActivity)
        }
    }
}

class AddTodoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_todo)
    }
}

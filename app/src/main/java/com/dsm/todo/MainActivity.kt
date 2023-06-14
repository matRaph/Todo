package com.dsm.todo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.Toast
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
import androidx.core.view.size
import com.dsm.todo.databinding.ActivityMainBinding
import com.dsm.todo.model.Todo
import com.dsm.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedTodo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addBtn.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }

        // Carrega e exibe todos
        loadTodos()
    }

    private fun loadTodos() {
        val todoDao = TodoDao(this)
        val todos = todoDao.getAll()

        val todoTitles = todos.map { it.title }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todoTitles)

        binding.todoListView.adapter = adapter

        for (todo in todos){
            println(todo)
        }
        binding.todoListView.setOnItemClickListener { _, _, position, _ ->
            selectedTodo = todos[position]
            navigateToTodoDetail()
        }
    }

    private fun navigateToTodoDetail() {
        selectedTodo?.let { todo ->
            val intent = Intent(this, TodoDetailActivity::class.java)
            intent.putExtra("todo_id", todo.id) // Pass the selected todo ID to the detail activity
            startActivity(intent)
        }
    }

}

package com.dsm.todo

import TodoDbHelper
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dsm.todo.databinding.ActivityAddTodoBinding
import com.dsm.todo.model.Todo
import com.google.gson.Gson

class AddTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTodoBinding
    private var lastTodoId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.saveTodo.setOnClickListener {
            val nome = binding.nomeEditText.text.toString().trim()
            val descricao = binding.descricaoEditText.text.toString().trim()

            if (nome.isNotEmpty()) {
                val todo = Todo(lastTodoId++, nome, descricao)

                // Salvar o todo no banco de dados
                val dbHelper = TodoDbHelper(this)
                val db = dbHelper.writableDatabase

                val values = ContentValues().apply {
                    put(TodoContract.TodoEntry.COLUMN_NAME_TITLE, todo.title)
                    put(TodoContract.TodoEntry.COLUMN_NAME_DESCRIPTION, todo.description)
                }

                db.insert(TodoContract.TodoEntry.TABLE_NAME, null, values)

                db.close()
                dbHelper.close()

                Toast.makeText(this, "Todo criado!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, digite um nome", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
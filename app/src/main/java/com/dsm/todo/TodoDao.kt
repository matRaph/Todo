package com.dsm.todo

import TodoDbHelper
import android.content.ContentValues
import android.content.Context
import com.dsm.todo.model.Todo

class TodoDao(context: Context) {

    private val dbHelper = TodoDbHelper(context)

    fun save(todo: Todo) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("title", todo.title)
            put("description", todo.description)
        }

        db.insert("todos", null, values)
        db.close()
    }

    fun getAll(): List<Todo> {
        val db = dbHelper.readableDatabase

        val projection = arrayOf("id", "title", "description")

        val cursor = db.query(
            "todos",
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val todos = mutableListOf<Todo>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow("id"))
                val title = getString(getColumnIndexOrThrow("title"))
                val description = getString(getColumnIndexOrThrow("description"))

                val todo = Todo(id, title, description)
                todos.add(todo)
            }
        }
        cursor.close()
        db.close()

        return todos
    }

    fun getById(todoId: Long): Todo? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf("id", "title", "description")

        val selection = "id = ?"
        val selectionArgs = arrayOf(todoId.toString())

        val cursor = db.query(
            "todos",
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var todo: Todo? = null
        with(cursor) {
            if (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow("id"))
                val title = getString(getColumnIndexOrThrow("title"))
                val description = getString(getColumnIndexOrThrow("description"))

                todo = Todo(id, title, description)
            }
        }
        cursor.close()
        db.close()

        return todo
    }

}

import android.provider.BaseColumns

object TodoContract {
    // Definir as constantes para o nome da tabela e colunas
    object TodoEntry : BaseColumns {
        const val TABLE_NAME = "todos"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_DESCRIPTION = "description"
    }
}

data class NoteComment(
    val commentId: Long = 0,
    val noteId: Long = 0,
    val text: String,
    val isDeleted: Boolean = false
)
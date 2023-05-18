data class NoteComment(
    var commentId: Long = 0,
    val noteId: Long = 0,
    var text: String,
    val isDeleted: Boolean = false,
    val commentIdInNote: Long = 0
) {
    override fun toString(): String {
        return text
    }

    operator fun set(commentId: Long, value: NoteComment) {

    }
}
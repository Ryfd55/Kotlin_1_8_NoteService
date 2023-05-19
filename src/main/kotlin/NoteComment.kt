data class NoteComment(
    var commentId: Long = 0,
    val noteId: Long = 0,
    var text: String,
    var isDeleted: Boolean = false,
    val commentIdInNote: Long = 0
) {
    override fun toString(): String {
        return text
    }
}
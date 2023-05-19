data class Note(
    var id: Long = 0,
    val title: String = "",
    val text: String = "",
    var comments:  MutableMap<Long, MutableList<NoteComment>> = mutableMapOf(),
    var commentCounter: Long = 0
) {
    override fun toString(): String {
        return "Заголовок: $title.  Текст: $text"
    }
}
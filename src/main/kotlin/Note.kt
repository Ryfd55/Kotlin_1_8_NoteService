data class Note(
    var id: Long = 0,
    val title: String = "",
    val text: String = "",
    val privacy: Int = 0,
    val commentPrivacy: Int = 0,
//    var comments: GenericNoteService<NoteComment> = GenericNoteService()
) {
    override fun toString(): String {
        return "Заголовок: $title.  Текст: $text\n"
    }
}
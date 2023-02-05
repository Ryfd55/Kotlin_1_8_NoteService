data class Note(
    val id: Long = 0,
    val title: String = "",
    val text: String = "",
    val privacy: Int = 0,
    val commentPrivacy: Int = 0,
//    var comments: GenericService<NoteComment> = GenericService()
)
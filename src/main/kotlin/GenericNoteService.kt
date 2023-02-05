open class GenericNoteService<T : Any> : ServiceInterface<T> {
    private var elements: MutableMap<Long, T> = mutableMapOf()
    var nextIdNote: Long = 0

    override fun add(item: T): Long {
        elements[++nextIdNote] = item
        return nextIdNote
    }

    override fun delete(id: Long): Boolean {
        if (elements.containsKey(id)) {
            return true
        } else {
            return false
        }
    }
}

object NotesService {
    private var notes = GenericNoteService<Note>()

    fun addNote(title: String, text: String, privacy: Int = 0, commentPrivacy: Int = 0): Long {
        println(Note(notes.nextIdNote + 1, title, text, privacy, commentPrivacy))
        return notes.add(Note(notes.nextIdNote + 1, title, text, privacy, commentPrivacy))
    }

}

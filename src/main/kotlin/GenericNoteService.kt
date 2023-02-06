open class GenericNoteService<T : Any> : ServiceInterface<T> {
    private var elements: MutableMap<Long, T> = mutableMapOf()
    var nextIdNote: Long = 0

    override fun add(item: T): Long {
        elements[++nextIdNote] = item
        return nextIdNote
    }

    override fun delete(id: Long): Boolean {
        return if (elements.containsKey(id)) {
            elements.remove(id)
             true
        } else {
             false
        }
    }
}

object NotesService {
    private var notes = GenericNoteService<Note>()

//    private var notes: MutableList<Note> = ArrayList<Note>()
//    private var comments: MutableList<Comment> = ArrayList<Comment>()

    fun addNote(title: String, text: String, privacy: Int = 0, commentPrivacy: Int = 0): Long {
        println(Note(notes.nextIdNote + 1, title, text, privacy, commentPrivacy))
        return notes.add(Note(notes.nextIdNote + 1, title, text, privacy, commentPrivacy))
    }
    fun deleteNote(title: String, text: String, privacy: Int = 0, commentPrivacy: Int = 0): Long {
        println(Note(notes.nextIdNote + 1, title, text, privacy, commentPrivacy))
        return notes.add(Note(notes.nextIdNote + 1, title, text, privacy, commentPrivacy))
    }

}

abstract class AbstractNoteService<T : Any> : ServiceInterface<T> {
    var items: MutableMap<Long, T> = mutableMapOf()
    var deletedElements: MutableMap<Long, T> = mutableMapOf()
    var nextIndex: Long = 0

    override fun read(): MutableMap<Long, T> {
        val result: MutableMap<Long, T> = mutableMapOf()
        for ((index, note) in items) {
            result[index] = note
        }
        return result
    }


    abstract fun add(note: Note): Long
    abstract fun edit(id: Long, newNote: Note)
}

class NoteService : AbstractNoteService<Note>() {
    override fun add(item: Note): Long {
        items[++nextIndex] = item
        return nextIndex
    }

    fun printNotes() {
        val notesMap = read()
        for ((index, note) in notesMap) {
            println("Заметка #$index\n$note")
//            printComments(note.value.id)
        }
    }

    override fun edit(id: Long, item: Note) {
        if (items.containsKey(id)) {
            val editedNote = item.copy(id = id)
            items[id] = editedNote
        } else {
            throw IllegalArgumentException("Note with id=$id not found")
        }
    }

    override fun delete(id: Long) {
        val note = items[id] ?: throw NoSuchElementException("No note with id $id")
        deletedElements[id] = note
        items.remove(id)
    }
}


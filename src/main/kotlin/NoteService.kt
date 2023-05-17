abstract class AbstractNoteService<T : Any> : ServiceInterface<T> {
    var items: MutableMap<Long, T> = mutableMapOf()
    var deletedElements: MutableMap<Long, T> = mutableMapOf()
    var nextIndex: Long = 0
    var commentIndex: Long = 0

    override fun read(): MutableMap<Long, T> {
        val result: MutableMap<Long, T> = mutableMapOf()
        for ((index, note) in items) {
            result[index] = note
        }
        return result
    }


    abstract fun add(note: Note): Long
    abstract override fun createComment(id: Long, comment: NoteComment): Long
    abstract fun edit(id: Long, newNote: Note)
}

class NoteService : AbstractNoteService<Note>() {


    override fun add(item: Note): Long {
        items[++nextIndex] = item
//        println(item)
        return nextIndex
    }

    fun printNotes() {
        val notesMap = read()
        for ((index, note) in notesMap) {
            println("Заметка #$index  $note")
            printComments(index)
        }
    }

    fun get() {
        printNotes()
    }

    fun getById(id: Long) {
        val note = items[id] ?: throw NoSuchElementException("No note with id $id")
        println("  $note")
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

    override fun createComment(id: Long, comment: NoteComment): Long {
        val note = items[id] ?: throw NoSuchElementException("No note with id $id")
        val noteComments = note.comments[id] ?: mutableListOf()
        val nextCommentId = noteComments.size.toLong() + 1
        val newComment = comment.copy(commentId = nextCommentId, noteId = id)
        noteComments.add(newComment)
        note.comments[id] = noteComments
        return note.commentCounter++
    }

    private fun printComments(noteId: Long): Boolean {
        val note = items[noteId]
        val noteComments = note?.comments?.get(noteId)
        if (noteComments != null && noteComments.isNotEmpty()) {
            for (comment in noteComments) println("     Комментарий ${comment.commentId} - $comment")
            return true
        } else {
            return false
        }
    }

    fun getComments(id: Long) {
        if (!printComments(id)) println("Комментариев к заметке #$id нет")
    }
}


import kotlin.collections.flatten

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
    abstract override fun createComment(id: Long, comment: NoteComment)
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
            println("   Заметка #$index  $note")
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

    override fun createComment(id: Long, comment: NoteComment) {
        commentIndex += 1
        val note = items[id] ?: throw NoSuchElementException("No note with id $id")
        val noteComments = note.comments[id] ?: mutableListOf()
        val nextCommentId = noteComments.size.toLong() + 1
        val newComment = comment.copy(commentIdInNote = nextCommentId, noteId = id, commentId = commentIndex)
        noteComments.add(newComment)
        note.comments[id] = noteComments

    }

    private fun printComments(id: Long): Boolean {
        val note = items[id]
        val noteComments = note?.comments?.get(id)
        return if (noteComments != null && noteComments.isNotEmpty()) {
            for (comment in noteComments)
                println("       #${comment.commentIdInNote} - $comment - commentId: ${comment.commentId}")
            true
        } else {
            false
        }
    }

    fun getComments(id: Long) {
        if (!printComments(id)) println("Комментариев к заметке #$id нет")
    }

    fun editComment(commentId: Long, newComment: NoteComment) {   // вызов комментария по commentId
        for ((_, note) in items) {
            for (comments in note.comments.values) {
                val comment = comments.find { it.commentId == commentId }
                if (comment != null) {
                    val editedComment = newComment.copy(text = newComment.text, commentId = commentId, noteId = newComment)
                    comments[commentId.toInt()-1] = editedComment
                    println(11)
                    println( editedComment.noteId)
                }
            }
        }
    }
}

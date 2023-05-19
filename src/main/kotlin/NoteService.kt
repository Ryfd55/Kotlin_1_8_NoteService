import kotlin.collections.flatten

abstract class AbstractNoteService<T : Any> : ServiceInterface<T> {
    var items: MutableMap<Long, T> = mutableMapOf()
    var noteIndex: Long = 0
    var commentIndex: Long = 0

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
        items[++noteIndex] = item
        return noteIndex
    }

    private fun printNotes() {
        val notesMap = read()
        for ((index, note) in notesMap) {
            println("   Заметка #$index  $note")
            printComments(index)
        }
    }

    override fun get() {
        printNotes()
    }

    override fun getById(id: Long) {
        val note = items[id] ?: throw exception.NotFoundException("No note with id $id")
        println("  $note")
    }

    override fun edit(id: Long, item: Note) {
        if (items.containsKey(id)) {
            val editedNote = item.copy(id = id)
            items[id] = editedNote
        } else {
            throw exception.NotFoundException("Note with id=$id not found")
        }
    }

    override fun delete(id: Long) {
        items[id] ?: throw exception.NotFoundException("No note with id $id")
        items.remove(id)
    }

    override fun createComment(id: Long, comment: NoteComment) {
        commentIndex++
        val note = items[id] ?: throw exception.NotFoundException("No note with id $id")
        val noteComments = note.comments[id] ?: mutableListOf()
        val nextCommentId = noteComments.size.toLong() + 1
        val newComment = comment.copy(commentIdInNote = nextCommentId, noteId = id, commentId = commentIndex)
        noteComments.add(newComment)
        note.comments[id] = noteComments
    }

    private fun printComments(noteId: Long): Boolean {
        val note = items[noteId]
        val noteComments = note?.comments?.get(noteId)
        return if (noteComments != null && noteComments.isNotEmpty()) {
            for (comment in noteComments) {
                if (!comment.isDeleted)
                    println("       #${comment.commentIdInNote} - $comment - commentId: ${comment.commentId}")
            }
            true
        } else false
    }

    override fun getComments(id: Long) {
        if (!printComments(id)) throw exception.NotFoundException("Comment to note #$id not found")
    }

    override fun editComment(commentId: Long, comment: NoteComment) {
        if (commentId in 1L..commentIndex) {
            val editedComment =
                items.values.find { it -> it.comments.values.flatten().any { it.commentId == commentId } }
            editedComment?.comments?.values?.forEach { comments ->
                comments.find { it.commentId == commentId }?.text = comment.text
            }
        } else {
            throw exception.NotFoundException("Comment with commentId=$commentId not found")
        }
    }

    override fun deleteComment(commentId: Long) {
        if (commentId in 1L..commentIndex) {
            val editedComment =
                items.values.find { it -> it.comments.values.flatten().any { it.commentId == commentId } }
            editedComment?.comments?.values?.forEach { comments ->
                comments.find { it.commentId == commentId }?.apply {
                    if (isDeleted) {
                        throw exception.CommentDeleteException("Comment with commentId=$commentId has already been deleted!")
                    }
                    isDeleted = true
                }
            }
        } else {
            throw exception.NotFoundException("Comment with commentId=$commentId not found")
        }
    }

    override fun restoreComment(commentId: Long) {
        if (commentId in 1L..commentIndex) {
            val editedComment =
                items.values.find { it -> it.comments.values.flatten().any { it.commentId == commentId } }
            editedComment?.comments?.values?.forEach { comments ->
                comments.find { it.commentId == commentId }?.apply {
                    if (!isDeleted) {
                        throw exception.CommentRestoreException("Comment with commentId=$commentId has not been deleted!")
                    } else {
                        isDeleted = false
                    }
                }
            }
        } else {
            throw exception.NotFoundException("Comment with commentId=$commentId not found")
        }
    }
}
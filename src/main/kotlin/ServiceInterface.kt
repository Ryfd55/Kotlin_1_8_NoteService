interface ServiceInterface<T> {
    fun add(item: T): Long
    fun read(): MutableMap<Long, T>
    fun edit(id: Long, item: T)
    fun delete(id: Long)
    fun get()
    fun getById(id: Long)
    fun createComment(id: Long, comment: NoteComment)
    fun getComments(id: Long)
    fun editComment(commentId: Long, comment: NoteComment)
    fun deleteComment(commentId: Long)
    fun restoreComment(commentId: Long)
}
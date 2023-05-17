import javax.xml.stream.events.Comment

interface ServiceInterface<T> {
    fun add(item: T): Long
    fun read(): MutableMap<Long, T>
    fun edit(id: Long, item: T)
    fun delete(id: Long)
    fun createComment(id: Long, comment: NoteComment):Long

    //    fun deleteComment

    //    fun editComment
//    fun get
//    fun getById
//    fun getComments
//    fun getFriendsNotes
//    fun restoreComment
}
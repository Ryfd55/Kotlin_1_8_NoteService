interface ServiceInterface<T> {
    fun add(item: T): Long
    fun read(): MutableMap<Long, T>
    fun edit(id: Long, item: T)
    fun delete(id: Long)
    //    fun createComment

    //    fun deleteComment

    //    fun editComment
//    fun get
//    fun getById
//    fun getComments
//    fun getFriendsNotes
//    fun restoreComment
}
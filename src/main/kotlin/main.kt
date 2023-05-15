fun main() {
    val noteService = NoteService()
    val noteFirst = Note(title = "Заметка 1", text = "Текст заметки 1")
//    NotesService.createComment(noteId, 1, 2, "Комментарий 1 к заметке 1")
//    NotesService.createComment(noteId, 1, 3, "Комментарий 2 к заметке 1")

    val noteSecond = Note(title = "Заметка 2", text = "Текст заметки 2")
//    NotesService.createComment(noteId, 2, 1, "Комментарий 1 к заметке 2")
//    NotesService.createComment(noteId, 2, 1, "Комментарий 2 к заметке 2")

    noteService.add(noteFirst)
    noteService.add(noteSecond)


    noteService.printNotes()

//    NotesService.deleteNote(1)
//    NotesService.printNotes()
//    println("\nУдалили комментарий с id == 1 у заметки с id == 2")
//
//    NotesService.printNotes()
//
//    NotesService.restoreComment(2, 1)
//    println("\nВосстановили комментарий с id == 1 у заметки с id == 2")
//
    noteService.edit(2, Note(title = "Отредактированный заголовок", text = "Отредактированный текст"))
    noteService.printNotes()
    noteService.delete(1)
    noteService.printNotes()
//    NotesService.printNotes()
}
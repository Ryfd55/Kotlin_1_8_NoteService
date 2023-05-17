fun main() {
    val noteService = NoteService()
    val noteFirst = Note(title = "Заметка_1", text = "Текст_заметки_1")
    val noteSecond = Note(title = "Заметка_2", text = "Текст_заметки_2")
    val noteThird = Note(title = "Заметка_3", text = "Текст_заметки_3")

    println("Добавление заметок")
    noteService.add(noteFirst)
    noteService.add(noteSecond)
    noteService.add(noteThird)
    noteService.get()

    println()
    println("Возвращение заметки по id")
    noteService.getById(2)

    println()
    println("Удаление заметки")
    noteService.delete(1)
    noteService.get()

    println()
    println("Редактирование заметки")
    noteService.edit(2, Note(title = "Отредактированный заголовок", text = "Отредактированный текст"))
    noteService.get()

    println()
    println("Создание комментариев")

    val comment1 = NoteComment(noteId = 2, text = "Пример_комментария_1")
    val comment2 = NoteComment(noteId = 2, text = "Пример_комментария_2")
    val comment3 = NoteComment(noteId = 2, text = "Пример_комментария_3")

    noteService.createComment(2, comment1)
    noteService.createComment(2, comment2)
    noteService.createComment(2, comment3)
    noteService.get()

    println()
    println("Вызов комментариев по номеру заметки")
    noteService.getComments(2)

//    println(comment)
//    println(comment.commentId)
//    noteService.createComment(2, comment)
    println("темп")
//    noteService.createComment(2, comment)
//    println(noteService.createComment(2, comment))


}
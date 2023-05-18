fun main() {
    val noteService = NoteService()
    val noteFirst = Note(title = "Заметка_1", text = "Текст_заметки_1")
    val noteSecond = Note(title = "Заметка_2", text = "Текст_заметки_2")
    val noteThird = Note(title = "Заметка_3", text = "Текст_заметки_3")
    val noteFourth = Note(title = "Заметка_4", text = "Текст_заметки_4")
    val comment1 = NoteComment(text = "Пример_комментария_1")
    val comment2 = NoteComment(text = "Пример_комментария_2")
    val comment3 = NoteComment(text = "Пример_комментария_3")
    val comment4 = NoteComment(text = "Пример_комментария_4")

    println("Добавление заметок")
    noteService.add(noteFirst)
    noteService.add(noteSecond)
    noteService.add(noteThird)
    noteService.add(noteFourth)
    noteService.get()

//
//    println("________________________________________")
//    println("Редактирование комментария")
//    noteService.editComment(2, NoteComment(text = "Отредактированный комментарий"))
//    noteService.get()

    println()
    println("________________________________________")
    println("Возвращение заметки по id")
    noteService.getById(2)

    println()
    println("________________________________________")
    println("Удаление заметки")
    noteService.delete(1)
    noteService.get()

    println()
    println("________________________________________")
    println("Редактирование заметки")
    noteService.edit(2, Note(title = "Отредактированный заголовок", text = "Отредактированный текст"))
    noteService.get()

    println()
    println("________________________________________")
    println("Создание комментариев")
    noteService.createComment(2, comment1)
    noteService.createComment(2, comment2)
    noteService.createComment(3, comment3)
    noteService.createComment(2, comment4)
    noteService.get()

    println()
    println("________________________________________")
    println("Вызов комментариев по номеру заметки")
    noteService.getComments(3)

    println()
    println("________________________________________")
    println("Редактирование комментария")
    noteService.editComment(1, NoteComment(text = "Отредактированный комментарий"))
//    noteService.get()

//    noteService.findComment(3)
    noteService.get()


//    println(comments)
}
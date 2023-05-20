import exception.CommentDeleteException
import exception.CommentRestoreException
import exception.NotFoundException
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class NoteServiceTest {
    private val noteService = NoteService()
    private val noteFirst = Note(title = "Заметка_1", text = "Текст_заметки_1")
    private val noteSecond = Note(title = "Заметка_2", text = "Текст_заметки_2")
    private val noteThird = Note(title = "Заметка_3", text = "Текст_заметки_3")
    private val comment1 = NoteComment(text = "Пример_комментария_1")
    private val comment2 = NoteComment(text = "Пример_комментария_2")
    private val comment3 = NoteComment(text = "Пример_комментария_3")
    private val comment4 = NoteComment(text = "Пример_комментария_4")
    private lateinit var outputStream: ByteArrayOutputStream

    @Test
    fun add() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
    }

    @Test
    fun get() {
        noteService.get()
    }

    @Test
    fun getById() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.getById(2)
    }

    @Test
    fun edit() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.edit(2, Note(title = "Отредактированный заголовок", text = "Отредактированный текст"))
    }

    @Test
    fun delete() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.delete(2)
    }

    @Test
    fun createComment() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.add(noteThird)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.createComment(3, comment3)
        noteService.createComment(2, comment4)
    }

    @Test
    fun getComments() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.add(noteThird)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.createComment(3, comment3)
        noteService.createComment(2, comment4)
        noteService.getComments(2)
    }

    @Test
    fun editComment() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.add(noteThird)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.createComment(3, comment3)
        noteService.createComment(2, comment4)
        noteService.editComment(2, NoteComment(text = "Отредактированный комментарий"))
    }

    @Test
    fun deleteComment() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.add(noteThird)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.createComment(3, comment3)
        noteService.createComment(2, comment4)
        noteService.deleteComment(2)
    }

    @Test
    fun restoreComment() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.add(noteThird)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.createComment(3, comment3)
        noteService.createComment(2, comment4)
        noteService.deleteComment(2)
        noteService.restoreComment(2)
    }

    @Before
    fun setUp() {
        outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
    }

    @Test
    fun shouldPrintNote() {
        noteService.add(noteFirst)
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        noteService.printNotes()
        val expectedOutput = "Заметка #1  Заголовок: Заметка_1.  Текст: Текст_заметки_1".trimMargin()
        assertEquals(expectedOutput, outputStream.toString().trim())
    }

    @Test(expected = NotFoundException::class)
    fun notFoundExInGetById() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        val nonExistentId = 10L
        noteService.getById(nonExistentId)
    }

    @Test(expected = NotFoundException::class)
    fun notFoundExInEdit() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.edit(10, Note(title = "Отредактированный заголовок", text = "Отредактированный текст"))
    }

    @Test(expected = NotFoundException::class)
    fun notFoundExInDelete() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.delete(10)
    }

    @Test(expected = NotFoundException::class)
    fun notFoundExInCreateComment() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.createComment(10, comment1)
    }

    @Test(expected = NotFoundException::class)
    fun notFoundExInGetComment() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.getComments(10)
    }

    @Test(expected = NotFoundException::class)
    fun notFoundExInEditComment() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.editComment(10, NoteComment(text = "Отредактированный комментарий"))
    }

    @Test(expected = NotFoundException::class)
    fun notFoundExInDeleteComment() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.deleteComment(10)
    }

    @Test(expected = CommentDeleteException::class)
    fun deleteCommentEx() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.deleteComment(2)
        noteService.deleteComment(2)
    }

    @Test(expected = NotFoundException::class)
    fun notFoundExInRestoreComment() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.deleteComment(2)
        noteService.restoreComment(10)
    }

    @Test(expected = CommentRestoreException::class)
    fun restoreCommentEx() {
        noteService.add(noteFirst)
        noteService.add(noteSecond)
        noteService.createComment(2, comment1)
        noteService.createComment(2, comment2)
        noteService.deleteComment(2)
        noteService.restoreComment(1)
    }
}
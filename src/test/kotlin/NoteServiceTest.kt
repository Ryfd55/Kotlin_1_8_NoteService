import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NoteServiceTest {

//    @Before
//    fun clearBeforeTest() {
//        NotesService.clear()
//    }

    @Test
    fun add() {
        var noteLastId = NotesService.addNote("Заметка 1", "Текст заметки 1")
        noteLastId = NotesService.addNote("Заметка 2", "Текст заметки 2")
        Assert.assertEquals(2, noteLastId)
    }

    @Test
    fun delete() {
        var noteLastId = NotesService.addNote("Заметка 1", "Текст заметки 1")
        noteLastId = NotesService.addNote("Заметка 2", "Текст заметки 2")
        Assert.assertEquals(2, noteLastId)
    }
}
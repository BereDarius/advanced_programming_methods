package Tests;

import Domain.Teacher;
import Exceptions.TeacherException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void exceptionIsThrown() {
        assertThrows(TeacherException.class, () -> new Teacher(-1, "", "", "", ""));
    }

    @Test
    void getId() throws TeacherException {
        Teacher teacher = new Teacher(10, "fn", "ln", "dr", "a@a.a");
        assertEquals(teacher.getId(), 10);
    }

    @Test
    void setFirstName() throws TeacherException {
        Teacher teacher = new Teacher(10, "fn", "ln", "dr", "a@a.a");
        teacher.setFirstName("fnn");
        assertEquals(teacher.getFirstName(), "fnn");
    }

    @Test
    void setId() throws TeacherException {
        Teacher teacher = new Teacher(10, "fn", "ln", "dr", "a@a.a");
        teacher.setId(1);
        assertEquals(teacher.getId(), 1);
    }

    @Test
    void setLastName() throws TeacherException {
        Teacher teacher = new Teacher(10, "fn", "ln", "dr", "a@a.a");
        teacher.setLastName("lnn");
        assertEquals(teacher.getLastName(), "lnn");
    }

    @Test
    void setTitle() throws TeacherException {
        Teacher teacher = new Teacher(10, "fn", "ln", "dr", "a@a.a");
        teacher.setTitle("title");
        assertEquals(teacher.getTitle(), "title");
    }

    @Test
    void setEmail() throws TeacherException {
        Teacher teacher = new Teacher(10, "fn", "ln", "dr", "a@a.a");
        teacher.setEmail("b@b.b");
        assertEquals(teacher.getEmail(), "b@b.b");
    }

    @Test
    void getEmail() throws TeacherException {
        Teacher teacher = new Teacher(10, "fn", "ln", "dr", "a@a.a");
        assertEquals(teacher.getEmail(), "a@a.a");
    }

    @Test
    void testEquals() throws TeacherException {
        Teacher teacher1 = new Teacher(10, "fn", "ln", "dr", "a@a.a");
        Teacher teacher2 = new Teacher(15, "fnn", "lnn", "drr", "ab@ab.ab");
        Teacher teacher3 = new Teacher(10, "fn", "ln", "dr", "a@a.a");
        assert teacher1.equals(teacher3);
        assertNotEquals(teacher1, teacher2);
    }
}
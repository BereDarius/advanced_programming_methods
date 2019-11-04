package Tests;

import Domain.Discipline;
import Exceptions.DisciplineException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisciplineTest {

    @Test
    void exceptionIsThrown() {
        assertThrows(DisciplineException.class, () -> new Discipline("name", ""));
    }

    @Test
    void getName() throws DisciplineException {
        Discipline discipline1 = new Discipline("name1", "field1");
        Discipline discipline2 = new Discipline("name2", "field2");
        Discipline discipline3 = new Discipline("name2", "field3");
        assertNotEquals(discipline1.getName(), discipline2.getName());
        assertEquals(discipline2.getName(), discipline3.getName());
    }

    @Test
    void setName() throws DisciplineException {
        Discipline discipline1 = new Discipline("name1", "field1");
        discipline1.setName("name2");
        assertEquals(discipline1.getName(), "name2");
    }

    @Test
    void setField() throws DisciplineException {
        Discipline discipline1 = new Discipline("name1", "field1");
        discipline1.setField("field2");
        assertEquals(discipline1.getField(), "field2");
    }

    @Test
    void testEquals() throws DisciplineException {
        Discipline discipline1 = new Discipline("name1", "field1");
        Discipline discipline2 = new Discipline("name2", "field2");
        assert !discipline1.equals(discipline2);
        Discipline discipline3 = new Discipline("name2", "field2");
        assert discipline2.equals(discipline3);
    }
}
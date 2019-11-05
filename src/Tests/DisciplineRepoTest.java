package Tests;

import Domain.Discipline;
import Exceptions.DisciplineException;
import Exceptions.DisciplineRepoException;
import Repos.DisciplineRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DisciplineRepoTest {

    @Test
    void add() throws DisciplineException, DisciplineRepoException {
        Discipline discipline1 = new Discipline("name1", "field1");
        Discipline discipline2 = new Discipline("name2", "field2");
        Discipline discipline3 = new Discipline("name3", "field3");
        DisciplineRepo repo = new DisciplineRepo();
        repo.add(discipline1);
        assertEquals(repo.getSize(), 1);
        repo.add(discipline2);
        assertEquals(repo.getSize(), 2);
        repo.add(discipline3);
        assertEquals(repo.getSize(), 3);
        assertEquals(repo.getAll().get(0), discipline1);
        assertEquals(repo.getAll().get(1), discipline2);
        assertEquals(repo.getAll().get(2), discipline3);
        assertThrows(DisciplineRepoException.class, () -> repo.add(discipline1));
    }

    @Test
    void remove() throws DisciplineException, DisciplineRepoException {
        Discipline discipline1 = new Discipline("name1", "field1");
        Discipline discipline2 = new Discipline("name2", "field2");
        Discipline discipline3 = new Discipline("name3", "field3");
        DisciplineRepo repo = new DisciplineRepo();
        repo.add(discipline1);
        repo.add(discipline2);
        repo.add(discipline3);
        assertEquals(repo.getAll().get(0), discipline1);
        assertEquals(repo.getAll().get(1), discipline2);
        assertEquals(repo.getAll().get(2), discipline3);
        repo.remove(discipline1);
        assertEquals(repo.getAll().get(0), discipline2);
        assertEquals(repo.getSize(), 2);
        repo.remove(discipline3);
        assertEquals(repo.getAll().get(0), discipline2);
        assertEquals(repo.getSize(), 1);
        assertThrows(DisciplineRepoException.class, () -> repo.remove(discipline1));
    }

    @Test
    void update() throws DisciplineException, DisciplineRepoException {
        Discipline discipline1 = new Discipline("name1", "field1");
        Discipline discipline2 = new Discipline("name2", "field2");
        Discipline discipline3 = new Discipline("name3", "field3");
        DisciplineRepo repo = new DisciplineRepo();
        repo.add(discipline1);
        repo.add(discipline2);
        repo.add(discipline3);
        repo.update(1, "newName", "newField");
        repo.update("name1", "newField1");
        assertEquals(repo.getAll().get(1).getName(), "newName");
        assertEquals(repo.getAll().get(1).getField(), "newField");
        assertEquals(repo.getAll().get(0).getField(), "newField1");
        assertThrows(DisciplineRepoException.class, () -> repo.update(4, "foo", "bar"));
        assertThrows(DisciplineRepoException.class, () -> repo.update("foo", "bar"));
    }
}
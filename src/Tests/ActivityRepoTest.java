package Tests;

import Domain.Activity;
import Domain.Discipline;
import Domain.Room;
import Domain.Teacher;
import Exceptions.*;
import Repos.ActivityRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ActivityRepoTest {

    @Test
    void add() throws DisciplineException, TeacherException, RoomException, ActivityException, ActivityRepoException {
        ActivityRepo repo = new ActivityRepo();
        Discipline discipline1 = new Discipline("name1", "field1");
        Teacher teacher1 = new Teacher(1, "aa", "ba", "ca", "@.a");
        ArrayList<String> activities1 = new ArrayList<>();
        activities1.add("lab");
        activities1.add("seminar");
        Room room1 = new Room("C1", 1000000, "B!F", activities1);
        Activity activity1 = new Activity(discipline1, "lab", teacher1, room1);
        repo.add(activity1);
        Discipline discipline2 = new Discipline("name2", "field2");
        Teacher teacher2 = new Teacher(1, "ab", "bb", "cb", "@.b");
        ArrayList<String> activities2 = new ArrayList<>();
        activities2.add("lecture");
        activities2.add("seminar");
        Room room2 = new Room("C2", 1000, "BF", activities2);
        Activity activity2 = new Activity(discipline2, "seminar", teacher2, room2);
        assertEquals(repo.getSize(), 1);
        repo.add(activity2);
        assertEquals(repo.getSize(), 2);
        assertEquals(repo.getAll().get(0), activity1);
        assertEquals(repo.getAll().get(1), activity2);
        assertThrows(ActivityRepoException.class, () -> repo.add(activity1));
    }

    @Test
    void insert() throws DisciplineException, TeacherException, RoomException, ActivityRepoException, ActivityException {
        ActivityRepo repo = new ActivityRepo();
        Discipline discipline1 = new Discipline("name1", "field1");
        Teacher teacher1 = new Teacher(1, "aa", "ba", "ca", "@.a");
        ArrayList<String> activities1 = new ArrayList<>();
        activities1.add("lab");
        activities1.add("seminar");
        Room room1 = new Room("C1", 1000000, "B!F", activities1);
        Activity activity1 = new Activity(discipline1, "lab", teacher1, room1);
        repo.add(activity1);
        Discipline discipline2 = new Discipline("name2", "field2");
        Teacher teacher2 = new Teacher(1, "ab", "bb", "cb", "@.b");
        ArrayList<String> activities2 = new ArrayList<>();
        activities2.add("lecture");
        activities2.add("seminar");
        Room room2 = new Room("C2", 1000, "BF", activities2);
        Activity activity2 = new Activity(discipline2, "seminar", teacher2, room2);
        assertEquals(repo.getSize(), 1);
        repo.insert(0, activity2);
        assertEquals(repo.getSize(), 2);
        assertEquals(repo.getAll().get(0), activity2);
        assertEquals(repo.getAll().get(1), activity1);
        assertThrows(ActivityRepoException.class, () -> repo.insert(0, activity1));
    }

    @Test
    void remove() throws DisciplineException, TeacherException, RoomException, ActivityException, ActivityRepoException {
        ActivityRepo repo = new ActivityRepo();
        Discipline discipline1 = new Discipline("name1", "field1");
        Teacher teacher1 = new Teacher(1, "aa", "ba", "ca", "@.a");
        ArrayList<String> activities1 = new ArrayList<>();
        activities1.add("lab");
        activities1.add("seminar");
        Room room1 = new Room("C1", 1000000, "B!F", activities1);
        Activity activity1 = new Activity(discipline1, "lab", teacher1, room1);
        repo.add(activity1);
        Discipline discipline2 = new Discipline("name2", "field2");
        Teacher teacher2 = new Teacher(1, "ab", "bb", "cb", "@.b");
        ArrayList<String> activities2 = new ArrayList<>();
        activities2.add("lecture");
        activities2.add("seminar");
        Room room2 = new Room("C2", 1000, "BF", activities2);
        Activity activity2 = new Activity(discipline2, "seminar", teacher2, room2);
        assertEquals(repo.getSize(), 1);
        repo.add(activity2);
        assertEquals(repo.getSize(), 2);
        assertEquals(repo.getAll().get(0), activity1);
        assertEquals(repo.getAll().get(1), activity2);
        repo.remove(activity2);
        assertEquals(repo.getSize(), 1);
        assertEquals(repo.getAll().get(0), activity1);
        assertThrows(ActivityRepoException.class, () -> repo.remove(activity2));
    }
}
package Tests;

import Domain.Activity;
import Domain.Discipline;
import Domain.Room;
import Domain.Teacher;
import Exceptions.ActivityException;
import Exceptions.DisciplineException;
import Exceptions.RoomException;
import Exceptions.TeacherException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @Test
    void exceptionIsThrown() {
        assertThrows(ActivityException.class, () -> {
            String activity_type = "fish";
            new Activity(null, activity_type, null, null);
        });
    }

    @Test
    void getDiscipline() throws DisciplineException, ActivityException {
        Discipline discipline = new Discipline("name", "field");
        Activity activity = new Activity(discipline, "lab", null, null);
        assertEquals(activity.getDiscipline(), discipline);
    }

    @Test
    void setDiscipline() throws DisciplineException, ActivityException  {
        Discipline discipline1 = new Discipline("name1", "field1");
        Discipline discipline2 = new Discipline("name2", "field2");
        Activity activity = new Activity(discipline1, "lab", null, null);
        activity.setDiscipline(discipline2);
        assertEquals(activity.getDiscipline(), discipline2);
    }

    @Test
    void getTeacher() throws TeacherException, ActivityException {
        Teacher teacher = new Teacher(1, "a", "b", "c", "@.");
        Activity activity = new Activity(null, "lab", teacher, null);
        assertEquals(activity.getTeacher(), teacher);
    }

    @Test
    void setTeacher() throws TeacherException, ActivityException {
        Teacher teacher1 = new Teacher(1, "aa", "ba", "ca", "@.a");
        Teacher teacher2 = new Teacher(2, "ab", "bb", "cb", "@.b");
        Activity activity = new Activity(null, "lab", teacher1, null);
        activity.setTeacher(teacher2);
        assertEquals(activity.getTeacher(), teacher2);
    }

    @Test
    void getRoom() throws RoomException, ActivityException {
        ArrayList<String> activities = new ArrayList<>();
        activities.add("lab");
        activities.add("seminar");
        Room room = new Room("C101", 100, "B1001", activities);
        Activity activity = new Activity(null, "lab", null, room);
        assertEquals(activity.getRoom(), room);
    }

    @Test
    void setRoom() throws RoomException, ActivityException {
        ArrayList<String> activities1 = new ArrayList<>();
        activities1.add("lab");
        activities1.add("seminar");
        ArrayList<String> activities2 = new ArrayList<>();
        activities2.add("lecture");
        activities2.add("seminar");
        Room room1 = new Room("C1", 1000000, "B!F", activities1);
        Room room2 = new Room("!WDE", 1, "BASE", activities2);
        Activity activity = new Activity(null, "lab", null, room1);
        activity.setRoom(room2);
        assertEquals(activity.getRoom(), room2);
    }

    @Test
    void testEquals() throws TeacherException, RoomException, DisciplineException, ActivityException {
        Discipline discipline1 = new Discipline("name1", "field1");
        Discipline discipline2 = new Discipline("name2", "field2");
        Teacher teacher1 = new Teacher(1, "aa", "ba", "ca", "@.a");
        Teacher teacher2 = new Teacher(2, "ab", "bb", "cb", "@.b");
        ArrayList<String> activities1 = new ArrayList<>();
        activities1.add("lab");
        activities1.add("seminar");
        ArrayList<String> activities2 = new ArrayList<>();
        activities2.add("lecture");
        activities2.add("seminar");
        Room room1 = new Room("C1", 1000000, "B!F", activities1);
        Room room2 = new Room("!WDE", 1, "BASE", activities2);
        Activity activity1 = new Activity(discipline1, "lab", teacher1, room1);
        Activity activity2 = new Activity(discipline2, "lecture", teacher2, room2);
        assertNotEquals(activity1, activity2);
        Activity activity3 = new Activity(discipline1, "lab", teacher1, room1);
        assertEquals(activity1, activity3);
    }
}
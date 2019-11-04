package Tests;

import Domain.*;
import Exceptions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FormationTest {

    @Test
    void exceptionIsThrown() {
        assertThrows(FormationException.class, () -> new Formation("", new ArrayList<>()));
    }

    @Test
    void getName() throws FormationException, DisciplineException, TeacherException, RoomException, ActivityException {
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
        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);
        Formation formation = new Formation("name", activities);
        assertEquals(formation.getName(), "name");
    }

    @Test
    void setName() throws DisciplineException, TeacherException, RoomException, ActivityException, FormationException {
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
        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);
        Formation formation = new Formation("name", activities);
        formation.setName("new name");
        assertEquals(formation.getName(), "new name");
    }

    @Test
    void getSubgroup() throws DisciplineException, TeacherException, RoomException, ActivityException, FormationException {
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
        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);
        Formation formation = new Formation("name", activities);
        Discipline discipline3 = new Discipline("name1", "field1");
        Discipline discipline4 = new Discipline("name2", "field2");
        Teacher teacher3 = new Teacher(1, "aa", "ba", "ca", "@.a");
        Teacher teacher4 = new Teacher(2, "ab", "bb", "cb", "@.b");
        ArrayList<String> activities3 = new ArrayList<>();
        activities3.add("lab");
        activities3.add("seminar");
        ArrayList<String> activities4 = new ArrayList<>();
        activities4.add("lecture");
        activities4.add("seminar");
        Room room3 = new Room("C1", 1000000, "B!F", activities3);
        Room room4 = new Room("!WDE", 1, "BASE", activities4);
        Activity activity3 = new Activity(discipline3, "lab", teacher3, room3);
        Activity activity4 = new Activity(discipline4, "lecture", teacher4, room4);
        ArrayList<Activity> activities_sub = new ArrayList<>();
        activities_sub.add(activity3);
        activities_sub.add(activity4);
        Formation subgroup = new Formation("sub", activities_sub);
        formation.setSubgroup(subgroup);
        assertEquals(formation.getSubgroup(), subgroup);
    }

    @Test
    void setSubgroup() throws ActivityException, RoomException, TeacherException, DisciplineException, FormationException {
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
        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);
        Formation formation = new Formation("name", activities);
        Discipline discipline3 = new Discipline("name1", "field1");
        Discipline discipline4 = new Discipline("name2", "field2");
        Teacher teacher3 = new Teacher(1, "aa", "ba", "ca", "@.a");
        Teacher teacher4 = new Teacher(2, "ab", "bb", "cb", "@.b");
        ArrayList<String> activities3 = new ArrayList<>();
        activities3.add("lab");
        activities3.add("seminar");
        ArrayList<String> activities4 = new ArrayList<>();
        activities4.add("lecture");
        activities4.add("seminar");
        Room room3 = new Room("C1", 1000000, "B!F", activities3);
        Room room4 = new Room("!WDE", 1, "BASE", activities4);
        Activity activity3 = new Activity(discipline3, "lab", teacher3, room3);
        Activity activity4 = new Activity(discipline4, "lecture", teacher4, room4);
        ArrayList<Activity> activities_sub = new ArrayList<>();
        activities_sub.add(activity3);
        activities_sub.add(activity4);
        Formation subgroup = new Formation("sub", activities_sub);
        formation.setSubgroup(subgroup);
        assertEquals(formation.getSubgroup(), subgroup);
    }

    @Test
    void addActivity() throws FormationException, ActivityException, RoomException, TeacherException, DisciplineException {
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
        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);
        Formation formation = new Formation("name", activities);
        Discipline discipline3 = new Discipline("name2", "field2");
        Teacher teacher3 = new Teacher(1, "aa", "ba", "ca", "@.a");
        ArrayList<String> activities3 = new ArrayList<>();
        activities3.add("lab");
        activities3.add("seminar");
        Room room3 = new Room("C1", 1000000, "B!F", activities3);
        Activity activity3 = new Activity(discipline3, "lab", teacher3, room3);
        formation.addActivity(activity3);
        assert formation.getActivities().get(2).equals(activity3);
        assertEquals(formation.getActivities().size(), 3);
    }

    @Test
    void removeActivity() throws DisciplineException, TeacherException, RoomException, ActivityException, FormationException {
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
        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);
        Formation formation = new Formation("name", activities);
        formation.removeActivity(activity1);
        assert formation.getActivities().get(0).equals(activity2);
        assertEquals(formation.getActivities().size(), 1);
    }

    @Test
    void testEquals() throws DisciplineException, TeacherException, RoomException, ActivityException, FormationException {
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
        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(activity1);
        activities.add(activity2);
        Formation formation = new Formation("name", activities);
        Discipline discipline3 = new Discipline("name1", "field1");
        Discipline discipline4 = new Discipline("name2", "field2");
        Teacher teacher3 = new Teacher(1, "aa", "ba", "ca", "@.a");
        Teacher teacher4 = new Teacher(2, "ab", "bb", "cb", "@.b");
        ArrayList<String> activities3 = new ArrayList<>();
        activities3.add("lab");
        activities3.add("seminar");
        ArrayList<String> activities4 = new ArrayList<>();
        activities4.add("lecture");
        activities4.add("seminar");
        Room room3 = new Room("C1", 1000000, "B!F", activities3);
        Room room4 = new Room("!WDE", 1, "BASE", activities4);
        Activity activity3 = new Activity(discipline3, "lab", teacher3, room3);
        Activity activity4 = new Activity(discipline4, "lecture", teacher4, room4);
        ArrayList<Activity> activities_sub = new ArrayList<>();
        activities_sub.add(activity3);
        activities_sub.add(activity4);
        Formation formation1 = new Formation("name1", activities_sub);
        Formation formation2 = new Formation("name", activities);
        assertNotEquals(formation, formation1);
        assert formation.equals(formation2);
    }
}
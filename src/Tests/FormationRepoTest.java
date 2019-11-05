package Tests;

import Domain.*;
import Exceptions.*;
import Repos.FormationRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FormationRepoTest {

    @Test
    void add() throws FormationException, DisciplineException, TeacherException, RoomException, ActivityException, FormationRepoException {
        FormationRepo repo = new FormationRepo();
        Discipline discipline1 = new Discipline("name1", "field1");
        Teacher teacher1 = new Teacher(1, "aa", "ba", "ca", "@.a");
        ArrayList<String> activities1 = new ArrayList<>();
        activities1.add("lab");
        activities1.add("seminar");
        Room room1 = new Room("C1", 1000000, "B!F", activities1);
        Activity activity1 = new Activity(discipline1, "lab", teacher1, room1);
        Discipline discipline2 = new Discipline("name2", "field2");
        Teacher teacher2 = new Teacher(1, "ab", "bb", "cb", "@.b");
        ArrayList<String> activities2 = new ArrayList<>();
        activities2.add("lecture");
        activities2.add("seminar");
        Room room2 = new Room("C2", 1000, "BF", activities2);
        Activity activity2 = new Activity(discipline2, "seminar", teacher2, room2);
        ArrayList<Activity> formation1_activities = new ArrayList<>();
        formation1_activities.add(activity1);
        ArrayList<Activity> formation2_activities = new ArrayList<>();
        formation2_activities.add(activity2);
        Formation formation1 = new Formation("name1", formation1_activities);
        Formation formation2 = new Formation("name2", formation2_activities);
        repo.add(formation1);
        assertEquals(repo.getSize(), 1);
        repo.add(formation2);
        assertEquals(repo.getSize(), 2);
        assertEquals(repo.getAll().get(0), formation1);
        assertEquals(repo.getAll().get(1), formation2);
        assertThrows(FormationRepoException.class, () -> repo.add(formation1));
    }

    @Test
    void remove() {
    }

    @Test
    void update() {
    }

    @Test
    void updateByName() {
    }
}
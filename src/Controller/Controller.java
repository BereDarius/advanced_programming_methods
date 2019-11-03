package Controller;

import Domain.*;
import Exceptions.*;
import Repos.*;

import java.util.ArrayList;


public class Controller {

    private ActivityRepo activities;
    private DisciplineRepo disciplines;
    private FormationRepo formations;
    private RoomRepo rooms;
    private TeacherRepo teachers;

    public Controller(ActivityRepo activities, DisciplineRepo disciplines, FormationRepo formations, RoomRepo rooms, TeacherRepo teachers) {
        this.activities = activities;
        this.disciplines = disciplines;
        this.formations = formations;
        this.rooms = rooms;
        this.teachers = teachers;
    }

    /*
    Activity Repo Methods
     */

    public boolean addActivity(Activity a) throws ActivityRepoException {
        return this.activities.add(a);
    }

    public void insertActivity(int index, Activity a) {
        this.activities.insert(index, a);
    }

    public boolean removeActivity(Activity a) throws ActivityRepoException {
        return this.activities.remove(a);
    }

    public ActivityRepo getAllActivities() {
        return this.activities;
    }

    /*
    Discipline Repo Methods
     */

    public boolean addDiscipline(Discipline d) throws DisciplineRepoException {
        return this.disciplines.add(d);
    }

    public boolean removeDiscipline(Discipline d) throws DisciplineRepoException, ActivityRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getDiscipline().equals(d)) {
                this.removeActivity(activity);
            }
        }
        return this.disciplines.remove(d);
    }

    public boolean removeDisciplineByName(String name) throws DisciplineRepoException, ActivityRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getDiscipline().getName().toLowerCase().equals(name.toLowerCase())) {
                this.removeActivity(activity);
            }
        }
        return this.disciplines.remove(name);
    }

    public void updateDiscipline(int index, String name, String field) throws DisciplineRepoException, DisciplineException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getDiscipline().equals(this.disciplines.getAll().get(index))) {
                activity.setDiscipline(new Discipline(name, field));
            }
        }
        this.disciplines.update(index, name, field);
    }

    public boolean updateDisciplineByName(String name, String field) throws DisciplineRepoException, DisciplineException {
        for (Activity activity : this.activities.getAll()){
            if (activity.getDiscipline().getName().toLowerCase().equals(name.toLowerCase())) {
                activity.setDiscipline(new Discipline(name, field));
            }
        }
        return this.disciplines.update(name, field);
    }

    public DisciplineRepo getAllDisciplines() {
        return this.disciplines;
    }

    /*
    Formation Repo Methods
     */

    public boolean addFormation(Formation f) throws FormationRepoException {
        return this.formations.add(f);
    }

    public boolean removeFormation(Formation f) throws FormationRepoException {
        return this.formations.remove(f);
    }

    public boolean removeFormationByName(String name) throws FormationRepoException {
        return this.formations.remove(name);
    }

    public void updateFormation(Formation formation, String name, ArrayList<Activity> newActivities, ArrayList<Activity> removeActivities, Formation newSubgroup) throws ActivityRepoException {
        for (Activity activity : newActivities) {
            if (!this.activities.getAll().contains(activity)) {
                this.activities.add(activity);
            }
        }
        this.formations.update(formation, name, newActivities, removeActivities, newSubgroup);
    }

    public void updateFormationByName(String name, ArrayList<Activity> newActivities, ArrayList<Activity> removeActivities, Formation newSubgroup) {
        this.formations.updateByName(name, newActivities, removeActivities, newSubgroup);
    }

    public FormationRepo getAllFormations() {
        return this.formations;
    }

    /*
    Room Repo Methods
     */

    public boolean addRoom(Room r) throws RoomRepoException {
        return this.rooms.add(r);
    }

    public boolean removeRoom(Room r) throws RoomRepoException, ActivityRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getRoom().equals(r)) {
                this.activities.remove(activity);
            }
        }
        return this.rooms.remove(r);
    }

    public boolean removeRoomByNum(String num) throws RoomRepoException, ActivityRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getRoom().getRoomNumber().toLowerCase().equals(num.toLowerCase())) {
                this.activities.remove(activity);
            }
        }
        return this.rooms.remove(num);
    }

     public void updateRoom(int index, String num, int numSeats, String building) throws RoomRepoException, RoomException {
         for (Activity activity : this.activities.getAll()) {
            if (activity.getRoom().equals(this.rooms.getAll().get(index))) {
                activity.setRoom(new Room(num, numSeats, building));
            }
         }
         this.rooms.update(index, num, numSeats, building);
    }

    public boolean updateRoomByNum(String num, int numSeats, String building) throws RoomRepoException, RoomException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getRoom().getRoomNumber().toLowerCase().equals(num.toLowerCase())) {
                activity.setRoom(new Room(num, numSeats, building));
            }
        }
        return this.rooms.update(num, numSeats, building);
    }

    public RoomRepo getAllRooms() {
        return this.rooms;
    }

    /*
    Teacher Repo Methods
     */

    public boolean addTeacher(Teacher t) throws TeacherRepoException {
        return this.teachers.add(t);
    }

    public boolean removeTeacher(Teacher t) throws TeacherRepoException, ActivityRepoException {
        for (Activity a : this.activities.getAll()) {
            if (a.getTeacher().equals(t)) {
                this.activities.remove(a);
            }
        }
        return this.teachers.remove(t);
    }

    public boolean removeTeacherByID(int id) throws TeacherRepoException, ActivityRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getTeacher().getId() == id) {
                this.activities.remove(activity);
            }
        }
        return this.teachers.remove(id);
    }

    public void updateTeacher(int index, int id, String fn, String ln, String title, String email) throws TeacherRepoException, TeacherException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getTeacher().equals(this.teachers.getAll().get(index))) {
                activity.setTeacher(new Teacher(id, fn, ln, title, email));
            }
        }
        this.teachers.update(index, id, fn, ln, title, email);
    }

    public boolean updateTeacherByID(int id, String fn, String ln, String title, String email) throws TeacherRepoException, TeacherException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getTeacher().getId() == id) {
                activity.setTeacher(new Teacher(id, fn, ln, title, email));
            }
        }
        return this.teachers.update(id, fn, ln, title, email);
    }

    public TeacherRepo getAllTeachers() {
        return this.teachers;
    }

}

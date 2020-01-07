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
    private BinaryRepo<Teacher> binaryTeachers;
    private DBrepo database;

    public Controller(ActivityRepo activities, DisciplineRepo disciplines, FormationRepo formations, RoomRepo rooms, TeacherRepo teachers, BinaryRepo<Teacher> binaryTeachers, DBrepo database) {
        this.activities = activities;
        this.disciplines = disciplines;
        this.formations = formations;
        this.rooms = rooms;
        this.teachers = teachers;
        this.binaryTeachers = binaryTeachers;
        this.database = database;
    }

    public void openDBConnection() {
        this.database.openConnection();
    }

    public void closeDBConnection() {
        this.database.closeConnection();
    }

    public void addTeacherDB(int id, String fname, String lname, String title, String email) throws TeacherException {
        Teacher t = new Teacher( id, fname, lname, title, email);
        this.database.addTeacher(t);
    }

    public ArrayList<Teacher> readDatabaseTeachers(){
        return this.database.getTeachers();
    }

    public void addActivityDB(int id, int discipline, String type, int teacher, String room) throws ActivityException {
        Activity a = new Activity(id, discipline, type, teacher, room);
        this.database.addActivity(a);
    }

    public ArrayList<Activity> readDatabaseActivities() {
        return this.database.getActivities();
    }

    /*
    Activity Repo Methods
     */

    public boolean addActivity(Activity a) throws ActivityRepoException, ActivityException {
        addActivityDB(a.getId(), a.getDiscipline(), a.getActivityType(), a.getTeacher(), a.getRoom());
        return this.activities.add(a);
    }

    public void insertActivity(int index, Activity a) throws ActivityRepoException {
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

    public boolean removeDiscipline(int index) throws DisciplineRepoException, ActivityRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getDiscipline() == this.disciplines.getAll().get(index).getId()) {
                this.removeActivity(activity);
            }
        }
        return this.disciplines.remove(this.disciplines.getAll().get(index));
    }

    public boolean removeDisciplineById(int id) throws DisciplineRepoException, ActivityRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getDiscipline() == id) {
                this.removeActivity(activity);
            }
        }
        return this.disciplines.remove(id);
    }

    public void updateDiscipline(int index, int id, String name, String field) throws DisciplineRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (disciplines.getDisciplineById(activity.getDiscipline()).equals(disciplines.getAll().get(index))) {
                activity.setDiscipline(id);
            }
        }
        this.disciplines.update(index, id, name, field);
    }

    public boolean updateDisciplineById(int id, String name, String field) throws DisciplineRepoException {
        return this.disciplines.update(id, name, field);
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

    public void updateFormation(int id, Formation formation, String name, ArrayList<Integer> newActivities, ArrayList<Integer> removeActivities, Formation newSubgroup) throws ActivityRepoException {
        for (int activity : newActivities) {
            if (!this.activities.getAll().contains(activities.getActivityById(activity))) {
                this.activities.add(activities.getActivityById(activity));
            }
        }
        this.formations.update(id, formation, name, newActivities, removeActivities, newSubgroup);
    }

    public void updateFormationByName(int id, String name, ArrayList<Integer> newActivities, ArrayList<Integer> removeActivities, Formation newSubgroup) {
        this.formations.updateById(id, name, newActivities, removeActivities, newSubgroup);
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
            if (rooms.getRoomByRoomNumber(activity.getRoom()).equals(r)) {
                this.activities.remove(activity);
            }
        }
        return this.rooms.remove(r);
    }

    public boolean removeRoomByNum(String num) throws RoomRepoException, ActivityRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getRoom().toLowerCase().equals(num.toLowerCase())) {
                this.activities.remove(activity);
            }
        }
        return this.rooms.remove(num);
    }

     public void updateRoom(int index, String num, int numSeats, String building, ArrayList<String> activities) throws RoomRepoException {
         for (Activity activity : this.activities.getAll()) {
            if (activity.getRoom().toLowerCase().equals(rooms.getAll().get(index).getRoomNumber().toLowerCase())) {
                activity.setRoom(num);
            }
         }
         this.rooms.update(index, num, numSeats, building, activities);
    }

    public boolean updateRoomByNum(String num, int numSeats, String building, ArrayList<String> activities) throws RoomRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getRoom().toLowerCase().equals(num.toLowerCase())) {
                activity.setRoom(num);
            }
        }
        return this.rooms.update(num, numSeats, building, activities);
    }

    public RoomRepo getAllRooms() {
        return this.rooms;
    }

    /*
    Teacher Repo Methods
     */

    public boolean addTeacher(Teacher t) throws TeacherRepoException, TeacherException {
        addTeacherDB(t.getId(), t.getFirstName(), t.getLastName(), t.getTitle(), t.getEmail());
        return this.teachers.add(t);
    }

    public boolean removeTeacher(Teacher t) throws TeacherRepoException, ActivityRepoException {
        for (Activity a : this.activities.getAll()) {
            if (teachers.getTeacherById(a.getTeacher()).equals(t)) {
                this.activities.remove(a);
            }
        }
        return this.teachers.remove(t);
    }

    public boolean removeTeacherByID(int id) throws TeacherRepoException, ActivityRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getTeacher() == id) {
                this.activities.remove(activity);
            }
        }
        return this.teachers.remove(id);
    }

    public void updateTeacher(int index, int id, String fn, String ln, String title, String email) throws TeacherRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (teachers.getTeacherById(activity.getTeacher()).equals(this.teachers.getAll().get(index))) {
                activity.setTeacher(id);
            }
        }
        this.teachers.update(index, id, fn, ln, title, email);
    }

    public boolean updateTeacherByID(int id, String fn, String ln, String title, String email) throws TeacherRepoException {
        for (Activity activity : this.activities.getAll()) {
            if (activity.getTeacher() == id) {
                activity.setTeacher(id);
            }
        }
        return this.teachers.update(id, fn, ln, title, email);
    }

    public TeacherRepo getAllTeachers() {
        return this.teachers;
    }

    public void serializeTeachers(ArrayList<Teacher> teachers ){
        this.binaryTeachers.Serialization(teachers, this.binaryTeachers.getFilepath());
    }
    public ArrayList<Teacher> deserializeTeacher(){
        return this.binaryTeachers.deserialization(this.binaryTeachers.getFilepath());
    }

}

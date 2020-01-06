package Repos;

import Domain.Teacher;
import Exceptions.TeacherRepoException;

import java.util.ArrayList;

public class TeacherRepo {

    private ArrayList<Teacher> teachers;


    /*
    CONSTRUCTOR
     */

    public TeacherRepo() {
        teachers = new ArrayList<>();
    }

    /*
    METHOD THAT ADDS A TEACHER TO THE REPO;
    RETURNS TRUE IF THE ENTITY WAS ADDED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean add(Teacher t) throws TeacherRepoException {
        for (Teacher teacher : this.getAll()) {
            if (teacher.getId() == t.getId()) {
                throw new TeacherRepoException("Teacher already exists within the teachers repository...");
            }
        }
        return teachers.add(t);
    }

    /*
    METHOD THAT REMOVES A TEACHER FROM THE REPO;
    RETURNS TRUE IF THE ENTITY WAS REMOVED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean remove(Teacher t) throws TeacherRepoException {
        if (teachers.contains(t)) {
            return teachers.remove(t);
        } else {
            throw new TeacherRepoException("Teacher doesn't exist within the teachers repository...");
        }
    }

    /*
    METHOD THAT REMOVES A TEACHER FROM THE REPO HAVING THE GIVEN ID;
    RETURNS TRUE IF THE ENTITY WAS REMOVED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean remove(int id) throws TeacherRepoException {
        for (int i = 0; i < this.teachers.size(); ++i) {
            if (this.teachers.get(i).getId() == id) {
                return this.teachers.remove(this.teachers.get(i));
            }
        }
        throw new TeacherRepoException("Teacher with given id does not exist within the teachers repository...");
    }

    /*
    METHOD THAT UPDATES A TEACHER FOUND AT THE GIVEN INDEX FROM THE REPO AND CHANGES IT'S ID, FIRST NAME, LAST NAME AND
    TITLE WITH THE NEW GIVEN ONES;
    THROWS AN EXCEPTION IF THE INDEX IS OUT OF BOUNDS
     */

    public void update(int index, int newId, String newFirstName, String newLastName, String newTitle, String newEmail) throws TeacherRepoException {
        if (index >= 0 && index < teachers.size()) {
            this.teachers.get(index).setId(newId);
            this.teachers.get(index).setFirstName(newFirstName);
            this.teachers.get(index).setLastName(newLastName);
            this.teachers.get(index).setTitle(newTitle);
            this.teachers.get(index).setEmail(newEmail);
        } else {
            throw new TeacherRepoException("Teachers repository index out of bounds...");
        }
    }

    /*
    METHOD THAT UPDATES A TEACHER FROM THE REPO HAVING THE GIVEN ID AND CHANGES IT'S FIRST NAME, LAST NAME AND TITLE
    WITH THE NEW GIVEN ONES;
    THROWS AN EXCEPTION IF THERE IS NO TEACHER HAVING THE GIVEN ID IN THE REPO
     */

    public boolean update(int id, String newFirstName, String newLastName, String newTitle, String newEmail) throws TeacherRepoException {
        for (Teacher teacher : this.teachers) {
            if (teacher.getId() == id) {
                teacher.setFirstName(newFirstName);
                teacher.setLastName(newLastName);
                teacher.setTitle(newTitle);
                teacher.setEmail(newEmail);
                return true;
            }
        }
        throw new TeacherRepoException("Teacher with given id does not exist within the teachers repository...");
    }

    /*
        METHOD THAT RETURNS THE NUMBER OF ENTITIES FROM THE REPO
     */

    public int getSize() {
        return this.teachers.size();
    }

    /*
    RETURNS THE TEACHER HAVING THE GIVEN ID
     */

    public Teacher getTeacherById(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    /*
    METHOD THAT RETURNS A LIST OF ALL TEACHERS FROM THE REPO
     */

    public ArrayList<Teacher> getAll() {
        return this.teachers;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Teacher teacher : this.teachers) {
            result.append(teacher.toString());
        }
        return result.toString();
    }
}

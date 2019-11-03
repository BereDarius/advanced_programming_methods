package Domain;

import Exceptions.TeacherException;

public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private String email;


    /*
    CONSTRUCTOR
     */

    public Teacher(int id, String firstName, String lastName, String title, String email) throws TeacherException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new TeacherException("ID must be greater than 0...\n");
        }
        for (char c : firstName.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new TeacherException("The teacher's first name must contain only letters...\n");
            }
        }
        this.firstName = firstName;
        for (char c : lastName.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new TeacherException("The teacher's last name must contain only letters...\n");
            }
        }
        this.lastName = lastName;
        for (char c : title.toCharArray()) {
            if (!Character.isLetter(c) && !(c == '.')) {
                throw new TeacherException("The teacher's title must contain only letters...\n");
            }
        }
        this.title = title;
        if (!email.contains("@") || !email.contains(".")) {
            throw new TeacherException("The teacher's email must be a valid one...\n");
        }

    }

    /*
    GETTERS AND SETTERS
     */

    public int getId() {
        return id;
    }

    private String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmail(String newEmail) { this.email = newEmail; }

    public String getEmail() { return this.email; }

    /*
    TO_STRING() AND EQUALS() OVERRIDE METHODS
     */

    @Override
    public String toString() {
        return "Teacher: " + this.id + ". " + this.title + " " + this.firstName + " " + this.lastName + ";\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return getId() == teacher.getId() &&
                getFirstName().toLowerCase().equals(teacher.getFirstName()) &&
                getLastName().toLowerCase().equals(teacher.getLastName()) &&
                getTitle().toLowerCase().equals(teacher.getTitle()) &&
                getEmail().toLowerCase().equals((teacher.getEmail()));
    }

}

package Domain;

import Exceptions.ActivityException;

import java.util.ArrayList;


public class Activity {

    private Discipline discipline;
    private String activityType;
    private Teacher teacher;
    private Room room;


    /*
    CONSTRUCTOR
     */

    public Activity(Discipline discipline, String activityType, Teacher teacher, Room room) throws ActivityException {
        ArrayList<String> activityTypes = new ArrayList<>();
        activityTypes.add("laboratory");
        activityTypes.add("seminar");
        activityTypes.add("lecture");
        activityTypes.add("lab");
        this.discipline = discipline;
        if (activityTypes.contains(activityType.toLowerCase())) {
            this.activityType = activityType;
        } else {
            throw new ActivityException("Activity type does not exist...");
        }
        this.teacher = teacher;
        this.room = room;
    }

    /*
    GETTERS AND SETTERS
     */

    private String getActivityType() {
        return activityType;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline newDiscipline) { this.discipline = newDiscipline; }

    public Teacher getTeacher() { return teacher; }

    public Room getRoom() { return this.room; }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /*
    TO_STRING() AND EQUALS() OVERRIDE METHODS
     */

    @Override
    public String toString() {
        return "Activity:\n\t" +
                discipline +
                "\tActivity type: " + activityType + ";" +
                "\n\t " + teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return getDiscipline().equals(activity.getDiscipline()) &&
                getActivityType().equals(activity.getActivityType()) &&
                teacher.equals(activity.teacher) &&
                room.equals(activity.room);
    }

}

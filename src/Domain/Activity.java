package Domain;

import Exceptions.ActivityException;

import java.util.ArrayList;


public class Activity {
    private int id;
    private int discipline_id;
    private String activityType;
    private int teacher_id;
    private String room_number;

    /*
    CONSTRUCTOR
     */

    public Activity(int id, int discipline_id, String activityType, int teacher_id, String room_number) throws ActivityException {
        ArrayList<String> activityTypes = new ArrayList<>();
        activityTypes.add("laboratory");
        activityTypes.add("seminar");
        activityTypes.add("lecture");
        activityTypes.add("lab");
        if (id > 0) {
            this.id = id;
        } else {
            throw new ActivityException("The ID must be a positive number...");
        }
        this.discipline_id = discipline_id;
        this.teacher_id = teacher_id;
        if (activityType == null) {
            throw new ActivityException("The activity type cannot be null...");
        }
        if (activityTypes.contains(activityType.toLowerCase())) {
            this.activityType = activityType;
        } else {
            throw new ActivityException("Activity type does not exist...");
        }
        this.room_number = room_number;
    }

    /*
    GETTERS AND SETTERS
     */

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getActivityType() {
        return activityType;
    }

    public int getDiscipline() {
        return discipline_id;
    }

    public void setDiscipline(int newDiscipline) { this.discipline_id = newDiscipline; }

    public int getTeacher() { return teacher_id; }

    public String getRoom() { return this.room_number; }

    public void setRoom(String room) {
        this.room_number = room;
    }

    public void setTeacher(int teacher) {
        this.teacher_id = teacher;
    }

    /*
    TO_STRING() AND EQUALS() OVERRIDE METHODS
     */

    @Override
    public String toString() {
        return "Activity:\n\t" +
                discipline_id +
                "\tActivity type: " + activityType + ";" +
                "\n\t " + teacher_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return getDiscipline() == activity.getDiscipline() &&
                getActivityType().equals(activity.getActivityType()) &&
                getTeacher() == activity.getTeacher() &&
                getRoom().equals(activity.getRoom());
    }
}

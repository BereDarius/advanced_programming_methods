package Repos;

import Domain.Activity;
import Exceptions.ActivityRepoException;

import java.util.ArrayList;

public class ActivityRepo {

    private ArrayList<Activity> activities;


    /*
    CONSTRUCTOR
     */

    public ActivityRepo() {
        activities = new ArrayList<>();
    }

    /*
    METHOD THAT ADDS AN ACTIVITY TO THE REPO AT THE END;
    RETURNS TRUE IF THE ENTITY WAS ADDED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean add(Activity a) throws ActivityRepoException {
        if (!activities.contains(a)) {
            return activities.add(a);
        } else {
            throw new ActivityRepoException("This activity already exists in the repository...");
        }
    }

    /*
    METHOD THAT INSERTS AN ACTIVITY IN THE REPO AT A GIVEN INDEX
     */

    public void insert(int index, Activity a) throws ActivityRepoException {
        if (!activities.contains(a)) {
            this.activities.add(index, a);
        } else {
            throw new ActivityRepoException("This activity already exists in the repository...");
        }
    }

    /*
    METHOD THAT REMOVES AN ACTIVITY FROM THE REPO;
    RETURNS TRUE IF THE ENTITY WAS REMOVED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean remove(Activity a) throws ActivityRepoException {
        if (activities.contains(a)) {
            return activities.remove(a);
        } else {
            throw new ActivityRepoException("The repository does not contain this activity...");
        }
    }

    /*
    RETURNS THE NUMBER OF ACTIVITIES STORED WITHIN THE REPO
     */

    public int getSize() {
        return this.activities.size();
    }

    /*
    RETURNS THE ACTIVITY HAVING THE GIVEN ID
     */

    public Activity getActivityById(int id) {
        for (Activity activity : activities) {
            if (activity.getId() == id) {
                return activity;
            }
        }
        return null;
    }

    /*
    RETURNS THE LIST OF THE REPO'S ACTIVITIES
     */

    public ArrayList<Activity> getAll() {
        return this.activities;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Activity activity : this.activities) {
            result.append(activity.toString());
        }
        return result.toString();
    }
}

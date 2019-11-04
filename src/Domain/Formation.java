package Domain;

import Exceptions.FormationException;

import java.util.ArrayList;

public class Formation {
    private String name;
    private Formation subgroup;
    private ArrayList<Activity> activities;


    /*
    CONSTRUCTOR
     */

    public Formation(String name, ArrayList<Activity> activities) throws FormationException {
        if (name != null && !name.equals("")) {
            this.name = name;
        } else {
            throw new FormationException("The name cannot be null or empty...");
        }
        if (!activities.isEmpty()) {
            this.activities = activities;
        } else {
            throw new FormationException("The formation must have at least one activity...");
        }
    }

    /*
    GETTERS AND SETTERS
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Formation getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(Formation f) { this.subgroup = f; }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        this.activities.remove(activity);
    }

    /*
    TO_STRING() AND EQUALS() OVERRIDE METHODS
     */

    @Override
    public String toString() {
        if (this.subgroup != null) {
            return "Group: " + name + ", subgroup: " + subgroup + ';';
        } else {
            return "Group: " + name + ";\n";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Formation)) return false;
        Formation formation = (Formation) o;
        if (getSubgroup() == null && formation.getSubgroup() == null) {
            return getName().equals(formation.getName()) &&
                    getActivities().equals(formation.getActivities());
        }
        return getName().equals(formation.getName()) &&
                getSubgroup().equals(formation.getSubgroup()) &&
                getActivities().equals(formation.getActivities());
    }

}
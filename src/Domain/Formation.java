package Domain;

import Exceptions.FormationException;

import java.util.ArrayList;

public class Formation {
    private int id;
    private String name;
    private Formation subgroup;
    private ArrayList<Integer> activities;

    /*
    CONSTRUCTOR
     */

    public Formation(int id, String name, ArrayList<Integer> activities) throws FormationException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new FormationException("The id must be a positive number...");
        }
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

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public ArrayList<Integer> getActivities() {
        return activities;
    }

    public void addActivity(int activity) {
        this.activities.add(activity);
    }

    public void removeActivity(int activity) {
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
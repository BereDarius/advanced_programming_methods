package Repos;

import Domain.Formation;
import Exceptions.FormationRepoException;

import java.util.ArrayList;

public class FormationRepo {

    private ArrayList<Formation> formations;


    /*
    CONSTRUCTOR
     */

    public FormationRepo() {
        formations = new ArrayList<>();
    }

    /*
    METHOD THAT ADDS A FORMATION TO THE REPOSITORY;
    RETURNS TRUE IF THE ENTITY WAS ADDED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean add(Formation f) throws FormationRepoException {
        for (Formation formation : this.getAll()) {
            if (formation.getName().toLowerCase().equals(f.getName().toLowerCase())) {
                throw new FormationRepoException("The formations repository already contains this formation...");
            }
        }
        return formations.add(f);
    }

    /*
    METHOD THAT REMOVES A FORMATION FROM THE REPO;
    RETURNS TRUE IF THE ENTITY WAS REMOVED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean remove(Formation f) throws FormationRepoException {
        if (formations.contains(f)) {
            return formations.remove(f);
        } else {
            throw new FormationRepoException("The formations repository does not contain this formation...");
        }
    }

    /*
    METHOD THAT REMOVES THE FORMATION HAVING THE GIVEN NAME FROM THE REPO;
    RETURNS TRUE IF THE ENTITY WAS REMOVED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean remove(String formationName) throws FormationRepoException {
        for (int i = 0; i < this.formations.size(); ++i) {
            if (this.formations.get(i).getName().equals(formationName)) {
                if (this.formations.get(i).getSubgroup() != null) {
                    this.remove(this.formations.get(i).getSubgroup().getName());
                }
                return this.formations.remove(this.formations.get(i));
            }
        }
        throw new FormationRepoException("There is no formation in the repository having the given name...");
    }

    /*
    METHOD THAT UPDATES A FORMATION FOUND AT THE GIVEN INDEX IN THE REPO AND CHANGES THE NAME WITH A NEW GIVEN ONE
     */

    public void update(int id, Formation formation, String newName, ArrayList<Integer> newActivities, ArrayList<Integer> removeActivities, Formation newSubgroup) {
        formation.setName(newName);
        for (int activity : newActivities) {
            formation.addActivity(activity);
        }
        for (int activity : removeActivities) {
            formation.removeActivity(activity);
        }
        formation.setSubgroup(newSubgroup);
        formation.setId(id);
    }

    public void updateById(int id, String name, ArrayList<Integer> newActivities, ArrayList<Integer> removeActivities, Formation newSubgroup) {
        for (Formation f : this.formations) {
            if (f.getId() == id) {
                f.setName(name);
                for (int activity : newActivities) {
                    f.addActivity(activity);
                }
                for (int activity : removeActivities) {
                    f.removeActivity(activity);
                }
                f.setSubgroup(newSubgroup);
            }
        }
    }

    /*
    METHOD THAT RETURNS THE NUMBER OF FORMATIONS IN THE REPO
     */

    public int getSize() {
        return this.formations.size();
    }

    /*
    RETURNS THE FORMATION HAVING THE GIVEN ID
     */

    public Formation getFormationById(int id) {
        for (Formation formation : formations) {
            if (formation.getId() == id) {
                return formation;
            }
        }
        return null;
    }

    /*
    METHOD THAT RETURNS A LIST OF ALL THE ENTITIES FOUND WITHIN THE REPO
     */

    public ArrayList<Formation> getAll() {
        return this.formations;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Formation formation : this.formations) {
            result.append(formation.toString());
        }
        return result.toString();
    }
}

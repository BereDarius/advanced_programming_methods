package Repos;

import Domain.Discipline;
import Exceptions.DisciplineRepoException;

import java.util.ArrayList;

public class DisciplineRepo {

    private ArrayList<Discipline> disciplines;


    /*
    CONSTRUCTOR
     */

    public DisciplineRepo() {
        disciplines = new ArrayList<>();
    }

    /*
    METHOD THAT ADDS A DISCIPLINE TO THE REPO;
    RETURNS TRUE IF THE ENTITY WAS ADDED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean add(Discipline d) throws DisciplineRepoException {
        for (Discipline discipline : this.getAll()) {
            if (discipline.getId() == d.getId()) {
                throw new DisciplineRepoException("This discipline already exists in the disciplines repository...");
            }
        }
        return disciplines.add(d);
    }

    /*
    METHOD THAT REMOVES A DISCIPLINE FROM THE REPO;
    RETURNS TRUE IF THE ENTITY WAS REMOVED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean remove(Discipline d) throws DisciplineRepoException {
        if (disciplines.contains(d)) {
            return disciplines.remove(d);
        } else {
            throw new DisciplineRepoException("The repository doesn't contain this discipline...");
        }
    }

    /*
    METHOD THAT REMOVES THE DISCIPLINE HAVING THE GIVEN NAME FROM THE REPO;
    RETURNS TRUE IF THE ENTITY WAS REMOVED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean remove(int id) throws DisciplineRepoException {
        for (int i = 0; i < this.disciplines.size(); ++i) {
            if (this.disciplines.get(i).getId() == id) {
                return this.disciplines.remove(this.disciplines.get(i));
            }
        }
        throw new DisciplineRepoException("The repository doesn't contain any discipline having the given name...");
    }

    /*
    METHOD THAT UPDATES THE DISCIPLINE FOUND AT THE GIVEN INDEX AND CHANGES THE DISCIPLINE'S NAME AND FIELD WITH THE
    NEW GIVEN ONES;
     */

    public void update(int index, int id, String newName, String newField) throws DisciplineRepoException {
        if (index < this.disciplines.size() && index >= 0) {
            this.disciplines.get(index).setName(newName);
            this.disciplines.get(index).setField(newField);
            this.disciplines.get(index).setId(id);
        } else {
            throw new DisciplineRepoException("Discipline repo index out of bounds...");
        }
    }

    /*
    METHOD THAT UPDATES THE DISCIPLINE HAVING THE GIVEN ID AND CHANGES IT'S NAME AND FIELD;
    RETURNS TRUE IF THE ENTITY WAS UPDATED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean update(int id, String name, String newField) throws DisciplineRepoException {
        for (Discipline discipline : this.disciplines) {
            if (discipline.getId() == id) {
                discipline.setField(newField);
                discipline.setName(name);
                return true;
            }
        }
        throw new DisciplineRepoException("The repository doesn't contain any discipline having the given name...");
    }

    /*
    METHOD THAT RETURNS THE NUMBER OF DISCIPLINES FROM THE REPO
     */

    public int getSize() {
        return this.disciplines.size();
    }

    /*
    RETURNS THE DISCIPLINE HAVING THE GIVEN ID
     */

    public Discipline getDisciplineById(int id) {
        for (Discipline discipline : disciplines) {
            if (discipline.getId() == id) {
                return discipline;
            }
        }
        return null;
    }

    /*
    METHOD THAT RETURNS A LIST OF ALL THE DISCIPLINES FROM THE REPO
     */

    public ArrayList<Discipline> getAll() {
        return this.disciplines;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Discipline discipline : this.disciplines) {
            result.append(discipline.toString());
        }
        return result.toString();
    }
}

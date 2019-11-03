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
            if (discipline.getName().toLowerCase().equals(d.getName().toLowerCase())) {
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

    public boolean remove(String name) throws DisciplineRepoException {
        for (int i = 0; i < this.disciplines.size(); ++i) {
            if (this.disciplines.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return this.disciplines.remove(this.disciplines.get(i));
            }
        }
        throw new DisciplineRepoException("The repository doesn't contain any discipline having the given name...");
    }

    /*
    METHOD THAT UPDATES THE DISCIPLINE FOUND AT THE GIVEN INDEX AND CHANGES THE DISCIPLINE'S NAME AND FIELD WITH THE
    NEW GIVEN ONES;
     */

    public void update(int index, String newName, String newField) throws DisciplineRepoException {
        if (index < this.disciplines.size() && index >= 0) {
            this.disciplines.get(index).setName(newName);
            this.disciplines.get(index).setField(newField);
        } else {
            throw new DisciplineRepoException("Discipline repo index out of bounds...");
        }
    }

    /*
    METHOD THAT UPDATES THE DISCIPLINE HAVING THE GIVEN NAME AND CHANGES IT'S FIELD;
    RETURNS TRUE IF THE ENTITY WAS UPDATED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean update(String name, String newField) throws DisciplineRepoException {
        for (Discipline discipline : this.disciplines) {
            if (discipline.getName().equals(name)) {
                discipline.setField(newField);
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

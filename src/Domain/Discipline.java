package Domain;

import Exceptions.DisciplineException;

public class Discipline {
    private int id;
    private String name;
    private String field;


    /*
    CONSTRUCTOR
     */

    public Discipline(int id, String name, String field) throws DisciplineException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new DisciplineException("The id must be a positive number...");
        }
        if (name != null && !name.equals("")) {
            this.name = name;
        } else {
            throw new DisciplineException("The name cannot be null...");
        }
        if (field != null && !field.equals("")) {
            this.field = field;
        } else {
            throw new DisciplineException("The field cannot be null...");
        }
    }

    /*
    GETTERS AND SETTERS
     */

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setField(String field) {
        this.field = field;
    }

    /*
    TO_STRING() AND EQUALS() OVERRIDE METHODS
     */

    @Override
    public String toString() {
        return "Discipline: " +
                "name: " + name +
                ", field: " + field + ";\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discipline)) return false;
        Discipline that = (Discipline) o;
        return getName().equals(that.getName()) &&
                getField().equals(that.getField());
    }

}

package Domain;

import Exceptions.DisciplineException;

public class Discipline {
    private String name;
    private String field;


    /*
    CONSTRUCTOR
     */

    public Discipline(String name, String field) throws DisciplineException {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
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

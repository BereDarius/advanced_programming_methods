package Domain;

import Exceptions.RoomException;

public class Room {

    private String roomNumber;
    private int availableSeats;
    private String building;


    /*
    CONSTRUCTOR
     */

    public Room(String roomNumber, int availableSeats, String building) throws RoomException {
        if (roomNumber != null && !roomNumber.equals("")) {
            this.roomNumber = roomNumber;
        } else {
            throw new RoomException("The room number cannot be null or empty...");
        }
        if (availableSeats > 0) {
            this.availableSeats = availableSeats;
        } else {
            throw new RoomException("The number of available seats must be greater than 0...");
        }
        if (building != null && !building.equals("")) {
            this.building = building;
        } else {
            throw new RoomException("The building name cannot be null or empty...");
        }

    }

    /*
    GETTERS AND SETTERS
     */

    public String getBuilding() {
        return building;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    /*
    TO_STRING() AND EQUALS() OVERRIDE METHODS
     */

    @Override
    public String toString() {
        return "Room:\n" + "room number: " + roomNumber + ", availableSeats: " + availableSeats + ";\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return this.getAvailableSeats() == room.getAvailableSeats() &&
                this.getRoomNumber().equals(room.getRoomNumber()) &&
                this.getBuilding().equals(room.getBuilding());
    }

}

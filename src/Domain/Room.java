package Domain;

import Exceptions.RoomException;

import java.util.ArrayList;

public class Room {

    private String roomNumber;
    private int availableSeats;
    private String building;
    private ArrayList<String> activity_types;

    /*
    CONSTRUCTOR
     */

    public Room(String roomNumber, int availableSeats, String building, ArrayList<String> activities) throws RoomException {
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
        ArrayList<String> possible_activities = new ArrayList<>();
        possible_activities.add("lab");
        possible_activities.add("laboratory");
        possible_activities.add("lecture");
        possible_activities.add("seminar");
        for (String activity_type : activities) {
            if (!possible_activities.contains(activity_type)) {
                throw new RoomException("There is no activity type: " + activity_type);
            }
        }
        this.activity_types = activities;
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

    public ArrayList<String> getActivityTypes() { return this.activity_types; }

    public void setActivityTypes(ArrayList<String> newActivityTypes) { this.activity_types = newActivityTypes; }

    /*
    TO_STRING() AND EQUALS() OVERRIDE METHODS
     */

    @Override
    public String toString() {
        return "Room:\n" + "room number: " + roomNumber + ", availableSeats: " + availableSeats + ", possible activities: " + activity_types.toString();
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

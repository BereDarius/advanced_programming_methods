package Repos;

import Domain.Room;
import Exceptions.RoomRepoException;

import java.util.ArrayList;

public class RoomRepo {

    private ArrayList<Room> rooms;


    /*
    CONSTRUCTOR
     */

    public RoomRepo() {
        rooms = new ArrayList<>();
    }

    /*
    METHOD THAT ADDS A ROOM TO THE REPO;
    RETURNS TRUE IF THE ROOM WAS ADDED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean add(Room r) throws RoomRepoException {
        for (Room room : this.getAll()) {
            if (room.getRoomNumber().toLowerCase().equals(r.getRoomNumber().toLowerCase())) {
                throw new RoomRepoException("Room already exists in the rooms repo...");
            }
        }
        return rooms.add(r);
    }

    /*
    METHOD THAT REMOVES A ROOM FROM THE REPO;
    RETURNS TRUE IF THE ROOM WAS REMOVED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean remove(Room r) throws RoomRepoException {
        if (rooms.contains(r)) {
            return rooms.remove(r);
        } else {
            throw new RoomRepoException("The room doesn't exist within the rooms repository...");
        }
    }

    /*
    METHOD THAT REMOVES A ROOM HAVING THE GIVEN NAME FROM THE REPO;
    RETURNS TRUE IF THE ROOM WAS REMOVED AND THROWS AN EXCEPTION OTHERWISE
     */

    public boolean remove(String roomNum) throws RoomRepoException {
        for (int i = 0; i < this.rooms.size(); ++i) {
            if (this.rooms.get(i).getRoomNumber().equals(roomNum)) {
                return this.rooms.remove(this.rooms.get(i));
            }
        }
        throw new RoomRepoException("There is no room having the given number in the repository...");
    }

    /*
    METHOD THAT UPDATES THE ROOM FROM THE REPO FOUND AT THE GIVEN INDEX AND CHANGES THE VALUES OF THE ROOM NUMBER,
    NUMBER OF SEATS AND BUILDING NAME WITH THE NEW GIVEN ONES;
    THROWS AN EXCEPTION IF THE INDEX IS OUT OF BOUNDS
     */

    public void update(int index, String newRoomNum, int newNumOfSeats, String newBuilding) throws RoomRepoException {
        if (index >= 0 && index < rooms.size()) {
            this.rooms.get(index).setRoomNumber(newRoomNum);
            this.rooms.get(index).setAvailableSeats(newNumOfSeats);
            this.rooms.get(index).setBuilding(newBuilding);
        } else {
            throw new RoomRepoException("Rooms repository index out of bounds...");
        }
    }

    /*
    METHOD THAT UPDATES THE ROOM FROM THE REPO HAVING THE GIVEN ROOM NUMBER AND CHANGES THE VALUES OF THE NUMBER OF
    SEATS AND BUILDING NAME WITH THE NEW GIVEN ONES;
    THROWS AN EXCEPTION IF THERE IS NO ROOM HAVING THE GIVEN NUMBER
     */

    public boolean update(String roomNum, int newNumOfSeats, String newBuilding) throws RoomRepoException {
        for (Room room : this.rooms) {
            if (room.getRoomNumber().equals(roomNum)) {
                room.setAvailableSeats(newNumOfSeats);
                room.setBuilding(newBuilding);
                return true;
            }
        }
        throw new RoomRepoException("There is no room having the given number in the repository...");
    }

    /*
    METHOD THAT RETURNS THE NUMBER OF ROOMS FROM THE REPO
     */

    public int getSize() {
        return this.rooms.size();
    }

    /*
    METHOD THAT RETURNS A LIST CONTAINING ALL THE ROOMS FROM THE REPO
     */

    public ArrayList<Room> getAll() {
        return this.rooms;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Room room : this.rooms) {
            result.append(room.toString());
        }
        return result.toString();
    }

    public int getIndexByNum(String num) {
        for (int index = 0; index < this.rooms.size(); ++index) {
            if (this.rooms.get(index).getRoomNumber().toLowerCase().equals(num)) {
                return index;
            }
        }
        return -1;
    }

}

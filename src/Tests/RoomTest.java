package Tests;

import Domain.Room;
import Exceptions.RoomException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void exceptionIsThrown() {
        assertThrows(RoomException.class, () -> new Room("1", 0, "", new ArrayList<>()));
    }

    @Test
    void getBuilding() throws RoomException {
        ArrayList<String> activity_types = new ArrayList<>();
        activity_types.add("lab");
        Room room = new Room("123", 123, "abc", activity_types);
        assertEquals(room.getBuilding(), "abc");
    }

    @Test
    void getRoomNumber() throws RoomException {
        ArrayList<String> activity_types = new ArrayList<>();
        activity_types.add("lab");
        Room room = new Room("123", 123, "abc", activity_types);
        assertEquals(room.getRoomNumber(), "123");
    }

    @Test
    void setRoomNumber() throws RoomException {
        ArrayList<String> activity_types = new ArrayList<>();
        activity_types.add("lab");
        Room room = new Room("123", 123, "abc", activity_types);
        room.setRoomNumber("456");
        assertEquals(room.getRoomNumber(), "456");
    }

    @Test
    void getAvailableSeats() throws RoomException {
        ArrayList<String> activity_types = new ArrayList<>();
        activity_types.add("lab");
        Room room = new Room("123", 123, "abc", activity_types);
        assertEquals(room.getAvailableSeats(), 123);
    }

    @Test
    void setAvailableSeats() throws RoomException {
        ArrayList<String> activity_types = new ArrayList<>();
        activity_types.add("lab");
        Room room = new Room("123", 123, "abc", activity_types);
        room.setAvailableSeats(456);
        assertEquals(room.getAvailableSeats(), 456);
    }

    @Test
    void setBuilding() throws RoomException {
        ArrayList<String> activity_types = new ArrayList<>();
        activity_types.add("lab");
        Room room = new Room("123", 123, "abc", activity_types);
        room.setBuilding("def");
        assertEquals(room.getBuilding(), "def");
    }

    @Test
    void getActivityTypes() throws RoomException {
        ArrayList<String> activity_types = new ArrayList<>();
        activity_types.add("lab");
        Room room = new Room("123", 123, "abc", activity_types);
        assertEquals(room.getActivityTypes(), activity_types);
    }

    @Test
    void setActivityTypes() throws RoomException {
        ArrayList<String> activity_types = new ArrayList<>();
        activity_types.add("lab");
        Room room = new Room("123", 123, "abc", activity_types);
        ArrayList<String> new_activity_types = new ArrayList<>();
        new_activity_types.add("lecture");
        room.setActivityTypes(new_activity_types);
        assertEquals(room.getActivityTypes(), new_activity_types);
    }

    @Test
    void testEquals() throws RoomException {
        ArrayList<String> activity_types1 = new ArrayList<>();
        activity_types1.add("lab");
        Room room1 = new Room("123", 123, "abc", activity_types1);
        ArrayList<String> activity_types2 = new ArrayList<>();
        activity_types2.add("lecture");
        Room room2 = new Room("13", 23, "ab", activity_types2);
        ArrayList<String> activity_types3 = new ArrayList<>();
        activity_types3.add("lab");
        Room room3 = new Room("123", 123, "abc", activity_types3);
        assert room1.equals(room3);
        assertNotEquals(room1, room2);
    }
}
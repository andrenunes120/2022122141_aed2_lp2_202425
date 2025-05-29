package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.lp2.project.model.Room;
import edu.ufp.inf.lp2.project.model.TimeSlot;


import java.util.ArrayList;
import java.util.List;

/**
 * Gere as salas da instituição, usando uma árvore RedBlackBST.
 */
public class RoomManager {

    private final RedBlackBST<String, Room> rooms;

    public RoomManager() {
        this.rooms = new RedBlackBST<>();
    }

    public void addRoom(Room room) {
        rooms.put(room.getCode(), room);
    }

    public void removeRoom(String code) {
        rooms.delete(code);
    }

    public Room getRoom(String code) {
        return rooms.get(code);
    }

    public boolean roomExists(String code) {
        return rooms.contains(code);
    }

    public Iterable<String> getAllRoomCodes() {
        return rooms.keys();
    }

    public List<Room> searchRoomsByFloor(int floor) {
        List<Room> result = new ArrayList<>();
        for (String code : rooms.keys()) {
            Room r = rooms.get(code);
            if (r.getFloor() == floor) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Room> searchFreeRoomsAtSlot(TimeSlot slot) {
        List<Room> free = new ArrayList<>();
        for (String code : rooms.keys()) {
            Room room = rooms.get(code);
            if (room.isFreeAt(slot)) {
                free.add(room);
            }
        }
        return free;
    }
}

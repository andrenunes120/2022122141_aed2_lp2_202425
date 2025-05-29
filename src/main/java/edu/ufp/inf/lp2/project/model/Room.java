package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma sala da instituição, com capacidade, piso e horários de ocupação.
 */
public class Room {
    private final String code;          // ex: "B102"
    private final int floor;            // ex: 1
    private final int capacity;         // nº de lugares
    private final int numSockets;       // nº de tomadas elétricas

    private final List<TimeSlot> occupiedSlots;

    public Room(String code, int floor, int capacity, int numSockets) {
        this.code = code;
        this.floor = floor;
        this.capacity = capacity;
        this.numSockets = numSockets;
        this.occupiedSlots = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public int getFloor() {
        return floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNumSockets() {
        return numSockets;
    }

    public List<TimeSlot> getOccupiedSlots() {
        return occupiedSlots;
    }

    public void addOccupiedSlot(TimeSlot slot) {
        occupiedSlots.add(slot);
    }

    public boolean isFreeAt(TimeSlot slot) {
        for (TimeSlot s : occupiedSlots) {
            if (s.overlapsWith(slot)) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sala " + code + " (piso " + floor + ", capacidade " + capacity + ", tomadas " + numSockets + ")";
    }
}

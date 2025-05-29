package edu.ufp.inf.lp2.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma sala da instituição.
 * Contém informação como o código, piso, capacidade, número de tomadas
 * e os horários em que está ocupada.
 */
public class Room {

    private final String code;
    private final int floor;
    private final int capacity;
    private final int numSockets;
    private final List<TimeSlot> occupiedSlots;

    /**
     * Constrói uma nova sala com os dados fornecidos.
     *
     * @param code       código da sala
     * @param floor      piso onde está localizada
     * @param capacity   capacidade da sala (lugares)
     * @param numSockets número de tomadas elétricas
     */
    public Room(String code, int floor, int capacity, int numSockets) {
        this.code = code;
        this.floor = floor;
        this.capacity = capacity;
        this.numSockets = numSockets;
        this.occupiedSlots = new ArrayList<>();
    }

    /** @return o código da sala */
    public String getCode() {
        return code;
    }

    /** @return o piso da sala */
    public int getFloor() {
        return floor;
    }

    /** @return a capacidade (nº de lugares) da sala */
    public int getCapacity() {
        return capacity;
    }

    /** @return o número de tomadas da sala */
    public int getNumSockets() {
        return numSockets;
    }

    /** @return lista de horários ocupados da sala */
    public List<TimeSlot> getOccupiedSlots() {
        return occupiedSlots;
    }

    /**
     * Adiciona um horário em que a sala ficará ocupada.
     *
     * @param slot o horário a adicionar
     */
    public void addOccupiedSlot(TimeSlot slot) {
        occupiedSlots.add(slot);
    }

    /**
     * Verifica se a sala está livre num determinado horário.
     *
     * @param slot o horário a verificar
     * @return true se a sala estiver livre nesse horário
     */
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

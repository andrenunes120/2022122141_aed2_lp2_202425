package edu.ufp.inf.lp2.project.manager;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.lp2.project.model.Room;
import edu.ufp.inf.lp2.project.model.TimeSlot;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere as salas da instituição, usando uma árvore RedBlackBST para permitir pesquisa eficiente por código.
 */
public class RoomManager {

    private final RedBlackBST<String, Room> rooms;

    /** Construtor padrão que inicializa a estrutura RedBlackBST. */
    public RoomManager() {
        this.rooms = new RedBlackBST<>();
    }

    /**
     * Adiciona uma sala à estrutura.
     *
     * @param room sala a adicionar
     */
    public void addRoom(Room room) {
        rooms.put(room.getCode(), room);
    }

    /**
     * Remove uma sala dado o seu código.
     *
     * @param code código da sala a remover
     */
    public void removeRoom(String code) {
        rooms.delete(code);
    }

    /**
     * Retorna uma sala a partir do seu código.
     *
     * @param code código da sala
     * @return a sala correspondente ou null se não existir
     */
    public Room getRoom(String code) {
        return rooms.get(code);
    }

    /**
     * Verifica se uma sala existe pelo seu código.
     *
     * @param code código da sala
     * @return true se existir, false caso contrário
     */
    public boolean roomExists(String code) {
        return rooms.contains(code);
    }

    /**
     * Retorna todos os códigos de sala existentes.
     *
     * @return conjunto iterável com os códigos
     */
    public Iterable<String> getAllRoomCodes() {
        return rooms.keys();
    }

    /**
     * Procura todas as salas num determinado piso.
     *
     * @param floor piso a procurar
     * @return lista de salas localizadas nesse piso
     */
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

    /**
     * Procura todas as salas livres num determinado horário.
     *
     * @param slot horário a verificar
     * @return lista de salas livres nesse horário
     */
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

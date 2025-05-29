package edu.ufp.inf.lp2.project.test;
import edu.ufp.inf.lp2.project.model.*;
import edu.ufp.inf.lp2.project.manager.*;

public class R3 {
    public static RoomManager criarRoomsExemplo() {
        RoomManager rm = new RoomManager();

        for (int i = 101; i <= 114; i++) {
            rm.addRoom(new Room("FCHS-" + i, 1, 30, 6));
        }

        for (int i = 201; i <= 214; i++) {
            rm.addRoom(new Room("FCHS-" + i, 2, 40, 8));
        }

        return rm;

    }

    public static void testarRemoverSala() {
        RoomManager rm = criarRoomsExemplo();
        rm.removeRoom("FCHS-101");
        System.out.println("Removida FCHS-101? " + (rm.getRoom("FCHS-101") == null));
        rm.getAllRoomCodes().forEach(System.out::println);
    }

    public static void testarRoomExiste() {
        RoomManager rm = criarRoomsExemplo();
        System.out.println("Existe FCHS-110? " + rm.roomExists("FCHS-110"));
        System.out.println("Existe FCHS-999? " + rm.roomExists("FCHS-999"));
    }

    public static void testarTodosOsCodigos() {
        RoomManager rm = criarRoomsExemplo();
        System.out.println("Todos os códigos de salas:");
        for (String code : rm.getAllRoomCodes()) {
            System.out.println("  - " + code);
        }
    }

    public static void testarSalasPorPiso() {
        RoomManager rm = criarRoomsExemplo();
        System.out.println("Salas no piso 2:");
        for (Room r : rm.searchRoomsByFloor(2)) {
            System.out.println("  - " + r);
        }
    }

    public static void main(String[] args) {
        testarRemoverSala();
        System.out.println();
        testarRoomExiste();
        System.out.println();
        testarTodosOsCodigos();
        System.out.println();
        testarSalasPorPiso();
    }

}

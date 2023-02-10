package applications;

import GUI.ship.ShipInterface;
import services.ShipService;
import entities.Ship;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class ShipsController {
    public static void main(String[] args) {

        Ship ship = new Ship();
        Socket socket;
        try {
            socket = new Socket("localhost", 8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ShipService shipService = new ShipService(socket, ship);


        SwingUtilities.invokeLater(() -> {
            new ShipInterface(shipService);
            new Thread(() -> {
            }).start();

        });
    }
}

package applications;

import GUI.world.WorldInterface;
import sockets.WorldSocket;

import javax.swing.*;
import java.net.ServerSocket;

public class WorldController {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new WorldInterface();
            new Thread(() -> {
            }).start();

        });

        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            WorldSocket worldSocket = new WorldSocket(serverSocket);
            worldSocket.startServer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

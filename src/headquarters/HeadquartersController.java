package applications;

import GUI.headquarters.HeadquarterInterface;
import sockets.HeadquartersSocket;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

public class HeadquartersController {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            HeadquarterInterface headOfficeGUI = new HeadquarterInterface();

            @Override
            public void run() {

            }
        });

        ServerSocket serverSocket = new ServerSocket(1234);
        HeadquartersSocket headquartersSocket = new HeadquartersSocket(serverSocket);
        headquartersSocket.startServer();
    }
}

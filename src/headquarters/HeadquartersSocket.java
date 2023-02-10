package sockets;

import services.HeadquartersService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HeadquartersSocket {
    private ServerSocket serverSocket;

    public HeadquartersSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                new HeadquartersService(socket);
            }
        } catch (IOException e) {
            closeServer();
        }
    }

    public void closeServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

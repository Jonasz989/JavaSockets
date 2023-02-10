package sockets;

import world.WorldService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WorldSocket{
    protected ServerSocket serverSocket;
    public WorldSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                new WorldService(socket);
            }
        } catch (IOException e) {
            closeServer();
        }
    }
    public void closeServer(){
        try {
            if(serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

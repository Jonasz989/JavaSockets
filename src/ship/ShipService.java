package services;

import GUI.ship.ScanFrame;
import GUI.ship.ShipInterface;
import entities.Ship;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class ShipService {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Ship ship;
    private ArrayList<Ship> ships = new ArrayList<>();
    private ShipInterface shipInterface;

    public ShipService(Socket socket, Ship ship){
        try{
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.ship = ship;
            this.sendCreationMessage();
            this.listen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void listen(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (socket.isConnected()){
                    try{
                        message = reader.readLine();
                        checkMessage(message);
                    } catch (IOException e) {
                        close();
                    }
                }
            }
        }).start();
    }
    private void close(){
        try {
            socket.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendCreationMessage(){
        ship.setFirstMessage(false);
        try{
            String message = "newWarship;"+ship.getId()+";"+ship.getxPosition()+";"+ship.getyPosition();
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMessage(int direction){
        try{
            String message = ship.getId()+";";
            message+="move"+";"+direction;
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            close();
        }
    }
    public void scan(){
        try{
            String message = "scan"+";"+ship.getId()+";";
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            close();
        }
    }
    private void checkMessage(String message){
        ships.clear();
        String[] parts = message.split(";");
        if(parts[1].equals(ship.getId().toString())){
            for(int i=2;i<parts.length;i+=3){
                Ship var = new Ship();
                var.setAlive(true);
                var.setId(UUID.fromString(parts[i]));
                var.setxPosition(Integer.parseInt(parts[i+1]));
                var.setyPosition( Integer.parseInt(parts[i+2]));
                ships.add(var);
            }
            SwingUtilities.invokeLater(() -> {
                new ScanFrame(ships);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    }
                }).start();

            });
        }

    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}

package services;

import GUI.world.WorldInterface;
import entities.Ship;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorldService {
    final private Socket socket;
    final private BufferedReader reader;
    final private BufferedWriter writer;
    public static List<Ship> ships = new ArrayList<>();
    public static ArrayList<WorldService> worldServices = new ArrayList<>();

    public WorldService(Socket socket) {
        this.socket = socket;
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            worldServices.add(this);
            this.listen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listen() {
        new Thread(() -> {
            String message;
            while (socket.isConnected()) {
                try {
                    message = reader.readLine();
                    //TODO sprawdz wiadomosci
                    checkMessage(message);
                } catch (IOException e) {
                    close();
                }
            }
        }).start();
    }

    private void close() {
        try {
            socket.close();
            writer.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkMessage(String message) {
        String[] parts = message.split(";");
        System.out.println(message);
        if (message.contains("newWarship")) {
            //"UUID;positionX;positionY;new"
            Ship ship = new Ship();
            ship.setId(UUID.fromString(parts[1]));
            ship.setAlive(true);
            ship.setxPosition(Integer.parseInt(parts[2]));
            ship.setyPosition(Integer.parseInt(parts[3]));
            ships.add(ship);
            WorldInterface.updateArrayList(ship);
            WorldInterface.updatePanels();
        } else if (message.contains("scan")) {
            String id = parts[1];
            StringBuilder listOfPositions = new StringBuilder("scan;" + id + ";");
            String currentWarship = "";
            for (Ship w : ships) {
                currentWarship = "";
                currentWarship += w.getId() + ";" + w.getxPosition() + ";" + w.getyPosition() + ";";
                listOfPositions.append(currentWarship);
            }
            System.out.println("Scan");
            sendMessage(listOfPositions.toString());
        } else if (message.contains("move")) {
            System.out.println("Move");
            int direction = Integer.parseInt(parts[parts.length - 1]);
            String id = parts[0];
            for (Ship ship : ships) {
                if (ship.getId().toString().equals(id)) {
                    ship.move(direction);
                    String toBuoyHandler = "BuoyHandler;"
                            + ship.getWhichFieldX() + ";"
                            + ship.getWhichFieldY() + ";"
                            + ship.getId() + ";"
                            + ship.getxPosition() + ";"
                            + ship.getyPosition();
                    for (WorldService worldServices : worldServices) {
                        worldServices.sendMessage(toBuoyHandler);
                    }
                }
            }
        }
        WorldInterface.updatePanels();
    }

    private void sendMessage(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

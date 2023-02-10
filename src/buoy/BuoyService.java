package services;

import entities.Ship;
import world.WorldField;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class BuoyService {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private final int length = 40;
    private final int width = 40;
    private int fieldX;
    private int fieldY;
    private final WorldField[][] fields = new WorldField[length][width];
    private final ArrayList<Ship> ships = new ArrayList<>();

    public BuoyService(int x, int y) {
        try {
            this.socket = new Socket("localhost", 8080);
            Socket socketToBuoyHandler = new Socket("localhost", 1234);

            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socketToBuoyHandler.getOutputStream()));

            this.fieldX = x;
            this.fieldY = y;

            this.createWorldFields();

            this.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        new Thread(() -> {
            String msg;
            while (socket.isConnected()) {
                try {
                    msg = bufferedReader.readLine();
                    checkMessage(msg);
                } catch (IOException e) {
                    close();
                }
            }
        }).start();
    }

    private void createWorldFields() {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                fields[i][j] = new WorldField();
            }
        }
    }

    private void close() {
        try {
            socket.close();
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkMessage(String msg) {

        String[] parts = msg.split(";");
        int fieldXNumber = Integer.parseInt(parts[1]);
        int fieldYNumber = Integer.parseInt(parts[2]);
        UUID uuid = UUID.fromString(parts[3]);
        int warshipX = Integer.parseInt(parts[4]);
        int warshipY = Integer.parseInt(parts[5]);
        if (this.fieldX == fieldXNumber && this.fieldY == fieldYNumber) {
            this.clearSeaLevel();
            Optional<Ship> ship = ships.stream()
                    .filter(warship -> warship.getId().equals(uuid))
                    .findFirst()
                    .map(warship -> {
                        warship.setxPosition(warshipX);
                        warship.setyPosition(warshipY);
                        return warship;
                    });
            if (ship.isEmpty()) {
                Ship shipv2 = new Ship();
                shipv2.setxPosition(warshipX);
                shipv2.setyPosition(warshipY);
                shipv2.setId(uuid);
                shipv2.setWhichFieldX(fieldXNumber);
                shipv2.setWhichFieldY(fieldYNumber);
                ships.add(shipv2);
            }
            this.setSeaLevel();
            StringBuilder sea = new StringBuilder();
            for (WorldField[] fields1 : fields) {
                for (WorldField field : fields1) {
                    sea.append(field.getCurrentSeaLevel()).append(";");
                }
            }
            this.sendMessage(sea.toString());
        }
    }

    private void setSeaLevel() {
        for (Ship ship : ships) {
            WorldField[][] agitationTable = ship.getSeaLevel();
            int x = ship.getxPosition();
            int y = ship.getyPosition();

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    fields[y + agitationTable[i][j].getyPosition()][x + agitationTable[i][j].getxPosition()].addSeaLevel(agitationTable[i][j].getSeaLevel());
                }
            }
        }
    }

    public void clearSeaLevel() { //clearing sea level in world
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                fields[i][j].setCurrentSeaLevel(0);
            }
        }
    }

    private void sendMessage(String message) { //sending message to server
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

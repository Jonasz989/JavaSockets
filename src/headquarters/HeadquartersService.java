package services;

import GUI.headquarters.HeadquarterInterface;
import world.WorldField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class HeadquartersService {

    private int length = 40;
    private int width = 40;
    private final Socket socket;
    private final BufferedReader bufferedReader;
    private final WorldField[][] fields = new WorldField[length][width];
    public static ArrayList<HeadquartersService> headquartersServices = new ArrayList<>();

    public HeadquartersService(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            headquartersServices.add(this);
            this.setFields();
            this.listen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listen() {
        new Thread(() -> {
            String msg;
            while (socket.isConnected()) {
                try {
                    msg = bufferedReader.readLine();
                    processMessage(msg);
                } catch (IOException e) {
                    close();
                }
            }
        }).start();
    }

    private void processMessage(String message) {
        String[] parts = message.split(";");
        int[][] currentSeaLevel = new int[40][40];
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                currentSeaLevel[i][j] = Integer.parseInt(parts[i * 40 + j]);
                fields[i][j].setCurrentSeaLevel(currentSeaLevel[i][j]);
                System.out.print(fields[i][j]);
            }
            System.out.println();
        }
        HeadquarterInterface.updatePanels(fields);
    }

    private void setFields() {
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
}

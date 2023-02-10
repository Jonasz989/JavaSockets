package GUI.ship;

import entities.Ship;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScanFrame extends JFrame {
    ArrayList<Ship> ships;

    public ScanFrame(ArrayList<Ship> ships) {
        this.ships = ships;

        this.setSize(600, 600);
        this.setVisible(true);
        this.setLayout(new GridLayout(20, 1));
        this.setTitle("Scan Frame");
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        int size = ships.size();
        JPanel[] uuidPanels = new JPanel[size];
        JPanel[] xPositionsPanels = new JPanel[size];
        JPanel[] yPositionsPanels = new JPanel[size];
        JPanel[] finalRow = new JPanel[size];

        for (int i = 0; i < size; i++) {
            uuidPanels[i] = new JPanel();
            xPositionsPanels[i] = new JPanel();
            yPositionsPanels[i] = new JPanel();
            finalRow[i] = new JPanel();
            finalRow[i].setLayout(new GridLayout(1, 3));
        }
        for (int i = 0; i < size; i++) {

            int y = i + 1;
            JLabel uuidLabel = new JLabel("ID: " + y);
            JLabel posXLabel = new JLabel("X: " + ships.get(i).getxPosition());
            JLabel posYLabel = new JLabel("Y: " + ships.get(i).getyPosition());

            uuidPanels[i].add(uuidLabel);
            xPositionsPanels[i].add(posXLabel);
            yPositionsPanels[i].add(posYLabel);


            finalRow[i].add(uuidPanels[i]);
            finalRow[i].add(xPositionsPanels[i]);
            finalRow[i].add(yPositionsPanels[i]);

            this.add(finalRow[i]);
        }
    }
}


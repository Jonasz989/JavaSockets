package GUI.world;

import entities.Ship;
import world.World;
import world.WorldField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WorldInterface extends JFrame {
    final private static int length = 40;
    final private static int width = 40;
    private static World world = new World();
    public static WorldField[][] fields = world.getFields();
    public static JPanel[][] panels = new JPanel[length][width];
    public static ArrayList<Ship> ships = new ArrayList<>();

    public WorldInterface() {

        this.setSize(700, 700);
        this.setVisible(true);
        this.setTitle("World");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(length, width));
        this.setLocationRelativeTo(null);
        this.setResizable(false);


        createPanelsToGridLayout();
        setBuoyColor();
    }


    private void createPanelsToGridLayout() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                panels[i][j] = new JPanel();
                panels[i][j].setBackground(Color.getHSBColor(0.5001f,0.4f, 1f));
                panels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                this.add(panels[i][j]);
            }
        }
    }

    private void setBuoyColor() {
        int x, y;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                x = fields[i][j].getBuoy().getPositionX();
                y = fields[i][j].getBuoy().getPositionY();
                fields[y][x].setThereABuoy(true);
                panels[y][x].setBackground(Color.getHSBColor(0.08f, 1f, 0.6f));
            }
        }
    }

    public static void updatePanels() {
        setPositionsToFields();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (fields[i][j].isThereAShip()) {
                    panels[i][j].setBackground(Color.getHSBColor(0.6666f, 0.7f, 0.3f));
                } else if (fields[i][j].isThereABuoy()) {
                    panels[i][j].setBackground(Color.getHSBColor(0.08f, 1f, 0.6f));
                } else {
                    panels[i][j].setBackground(Color.getHSBColor(0.5001f,0.6f, 1f));
                }
            }
        }
    }

    public static void updateArrayList(Ship ship) {
        fields[ship.getyPosition()][ship.getxPosition()].setThereAShip(true);
        ships.add(ship);
    }

    private static void setPositionsToFields() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                fields[i][j].setThereAShip(false);
            }
        }
        for (Ship ship : ships) {
            fields[ship.getyPosition()][ship.getxPosition()].setThereAShip(true);
        }
    }
}

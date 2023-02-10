package GUI.headquarters;

import world.WorldField;

import javax.swing.*;
import java.awt.*;

public class HeadquarterInterface extends JFrame implements Runnable {
    private static int length = 40;
    private static int width = 40;
    public static JPanel[][] fieldPanels = new JPanel[length][width];


    public HeadquarterInterface() {
        this.setFrame();
        this.createFieldPanels();
    }

    private void createFieldPanels() {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                fieldPanels[i][j] = new JPanel();
                this.add(fieldPanels[i][j]);
            }
        }
    }

    private void setFrame() {
        this.setSize(600, 600);
        this.setVisible(true);
        this.setTitle("Head Office");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(40, 40));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public static void updatePanels(WorldField[][] fields) {

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                for (int c = 0; c < 24; c++) {
                    if (fields[i][j].getCurrentSeaLevel() == c) {
                        Color color = new Color(0, 0, c * 10);
                        fieldPanels[i][j].setBackground(color);
                        fieldPanels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    }
                }
            }
        }
    }

    @Override
    public void run() {

    }
}

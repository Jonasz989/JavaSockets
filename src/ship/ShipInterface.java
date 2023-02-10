package GUI.ship;

import entities.Ship;
import services.ShipService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ShipInterface extends JFrame implements ActionListener {
    private final ShipService shipService;
    private final Ship ship;
    private final JPanel alivePanel = new JPanel();
    private final JPanel positionXPanel = new JPanel();
    private final JPanel positionYPanel = new JPanel();
    private final JPanel movePanel = new JPanel();
    private final JPanel scanPanel = new JPanel();
    private final JButton move = new JButton("Move");
    private final JButton scan = new JButton("Scan");

    public ShipInterface(ShipService shipService) {
        this.shipService = shipService;
        this.ship = shipService.getShip();
        this.frame();
        this.statusPanel();
        this.xPositionPanel();
        this.yPositionPanel();
        this.movePanel();
        this.scanPanel();
    }

    private void frame() {
        this.setSize(200, 200);
        this.setVisible(true);
        this.setTitle("Ship");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(5, 1));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void statusPanel() {
        JLabel label = new JLabel();
        if (ship.isAlive()) {
            label.setText("We are sailing");
        } else {
            label.setText("Sunk");
        }
        alivePanel.add(label);
        this.add(alivePanel);
    }

    public void updatePanels() {
        alivePanel.removeAll();
        positionXPanel.removeAll();
        positionYPanel.removeAll();
        statusPanel();
        xPositionPanel();
        yPositionPanel();
        alivePanel.repaint();
        alivePanel.validate();
        positionXPanel.repaint();
        positionXPanel.validate();
        positionYPanel.repaint();
        positionYPanel.validate();
    }

    private void xPositionPanel() {
        JLabel label = new JLabel();
        String posX = "Position X: " + this.ship.getxPosition();
        label.setText(posX);
        positionXPanel.add(label);
        this.add(positionXPanel);
    }

    private void yPositionPanel() {
        JLabel label = new JLabel();
        String posY = "Position Y: " + this.ship.getyPosition();
        label.setText(posY);
        positionYPanel.add(label);
        this.add(positionYPanel);
    }

    private void movePanel() {
        move.addActionListener(this);
        move.setSize(50, 50);
        movePanel.add(move);
        this.add(movePanel);
    }

    private void scanPanel() {
        scan.addActionListener(this);
        scan.setSize(50, 50);
        scanPanel.add(scan);
        this.add(scanPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == move) {
            int direction = Math.abs(new Random().nextInt(8) + 1);
            this.ship.move(direction);
            shipService.sendMessage(direction);
            this.updatePanels();

        } else if (e.getSource() == scan) {
            shipService.scan();
        }
    }
}

package entities;

import world.WorldField;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Ship implements Runnable{


    private int xPosition;
    private int yPosition;
    private boolean alive = true;
    private UUID id = UUID.randomUUID();
    private boolean firstMessage = true;
    private int whichFieldX;
    private int whichFieldY;
    private boolean running = true;

    private WorldField[][] seaLevel = new WorldField[5][5];

    public Ship() {
        this.xPosition = Math.abs((new Random().nextInt() % 36) + 2);
        this.yPosition = Math.abs((new Random().nextInt() % 36) + 2);
        this.createAndChangeSeaLevel();
        this.setDistanceFromCenter();
        this.whichFieldX = this.xPosition / 5;
        this.whichFieldY = this.yPosition / 5;
    }

    private void createAndChangeSeaLevel() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                seaLevel[i][j] = new WorldField();
            }
        }
        //0---0
        //-----
        //-----
        //-----
        //0---0
        seaLevel[0][0].setSeaLevel(0);
        seaLevel[0][4].setSeaLevel(0);
        seaLevel[4][0].setSeaLevel(0);
        seaLevel[4][4].setSeaLevel(0);
        //-1-1-
        //1---1
        //-----
        //1---1
        //-1-1-
        seaLevel[0][1].setSeaLevel(1);
        seaLevel[0][3].setSeaLevel(1);
        seaLevel[1][0].setSeaLevel(1);
        seaLevel[1][4].setSeaLevel(1);
        seaLevel[3][0].setSeaLevel(1);
        seaLevel[3][4].setSeaLevel(1);
        seaLevel[4][1].setSeaLevel(1);
        seaLevel[4][3].setSeaLevel(1);
        //-----
        //-2-2-
        //2---2
        //-2-2-
        //-----
        seaLevel[0][2].setSeaLevel(2);
        seaLevel[1][1].setSeaLevel(2);
        seaLevel[1][3].setSeaLevel(2);
        seaLevel[2][0].setSeaLevel(2);
        seaLevel[2][4].setSeaLevel(2);
        seaLevel[3][1].setSeaLevel(2);
        seaLevel[3][3].setSeaLevel(2);
        seaLevel[4][2].setSeaLevel(2);
        //-----
        //--3--
        //-3-3-
        //--3--
        //-----
        seaLevel[1][2].setSeaLevel(3);
        seaLevel[2][1].setSeaLevel(3);
        seaLevel[2][3].setSeaLevel(3);
        seaLevel[3][2].setSeaLevel(3);
        //-----
        //-----
        //--4--
        //-----
        //-----
        seaLevel[2][2].setSeaLevel(4);
    }

    private void setDistanceFromCenter() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                seaLevel[i][j].setxPosition(i - 2);
                seaLevel[i][j].setyPosition(j - 2);
            }
        }
    }

    @Override
    public void run() {
        while (running) {

        }
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public WorldField[][] getSeaLevel() {
        return seaLevel;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstMessage(boolean firstMessage) {
        this.firstMessage = firstMessage;
    }

    public void move(int direction) {
        if (direction == 0 && xPosition - 1 >= 2 && yPosition - 1 >= 2) {
            xPosition--;
            yPosition--;
        } else if (direction == 1 && yPosition - 1 >= 2) {
            yPosition--;
        } else if (direction == 2 && xPosition + 1 <= 38 && yPosition + 1 <= 38) {
            xPosition++;
            yPosition++;
        } else if (direction == 3 && xPosition - 1 >= 2) {
            xPosition--;
        } else if (direction == 4 && xPosition + 1 <= 38) {
            xPosition++;
        } else if (direction == 5 && xPosition - 1 >= 2 && yPosition + 1 <= 38) {
            xPosition--;
            yPosition++;
        } else if (direction == 6 && yPosition + 1 <= 38) {
            yPosition++;
        } else if (direction == 7 && xPosition + 1 <= 2 && yPosition + 1 <= 38) {
            xPosition++;
            yPosition++;
        } else {
            direction = new Random().nextInt(8) + 1;
            this.move(direction);
        }
        this.whichFieldX = this.xPosition / 5;
        this.whichFieldY = this.yPosition / 5;
    }

    @Override
    public String toString() {
        return "Warship{" +
                "running=" + running +
                ", positionX=" + xPosition +
                ", positionY=" + yPosition +
                ", alive=" + alive +
                ", id=" + id +
                ", agitationTable=" + Arrays.toString(seaLevel) +
                '}';
    }

    public int getWhichFieldX() {
        return whichFieldX;
    }

    public void setWhichFieldX(int whichField) {
        this.whichFieldX = whichField;
    }

    public int getWhichFieldY() {
        return whichFieldY;
    }

    public void setWhichFieldY(int whichFieldY) {
        this.whichFieldY = whichFieldY;
    }
}

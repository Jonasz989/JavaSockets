package orld;

import entities.Buoy;

public class WorldField {



    private int xPosition;
    private int yPosition;
    private int seaLevel;
    private int currentSeaLevel;
    private boolean isThereAShip;
    private final Buoy buoy = new Buoy();
    private boolean isThereABuoy = false;

    public WorldField() {

    }

    public void addSeaLevel(int newSeaLevel) {
        this.currentSeaLevel += newSeaLevel;
    }
    @Override
    public String toString() {
        return "Field{Buoy nr. = " + buoy + "}";
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
    public int getSeaLevel() {
        return seaLevel;
    }
    public void setSeaLevel(int seaLevel) {
        this.seaLevel = seaLevel;
    }
    public int getCurrentSeaLevel() {
        return currentSeaLevel;
    }
    public void setCurrentSeaLevel(int currentSeaLevel) {
        this.currentSeaLevel = currentSeaLevel;
    }
    public boolean isThereAShip() {
        return isThereAShip;
    }
    public void setThereAShip(boolean thereAShip) {
        this.isThereAShip = thereAShip;
    }
    public boolean isThereABuoy() {
        return isThereABuoy;
    }
    public void setThereABuoy(boolean thereABuoy) {
        this.isThereABuoy = thereABuoy;
    }

    public Buoy getBuoy() {
        return buoy;
    }

}

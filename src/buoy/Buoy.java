package entities;

public class Buoy {



    //TODO ID boi
    private int ID;
    private int positionX;
    private int positionY;

    public Buoy(){

    }

    public String getBuoyPosition() {
        return "Buoy nr. " + this.ID + " X: " + positionX + " Y: " + positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }



}

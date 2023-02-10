package orld;

public class World {


    private final int length = 40;
    private final int width = 40;
    private final WorldField[][] worldFields = new WorldField[length][width];

    public World() {
        this.createWorld();
    }

    public void createWorld() {
        int buoyYPosition = 2;
        for (int i = 0; i < length; i++) {
            int buoyXPosition = 2;
            for (int j = 0; j < width; j++) {
                worldFields[i][j] = new WorldField();
                worldFields[i][j].setxPosition(i);
                worldFields[i][j].setyPosition(j);
                worldFields[i][j].getBuoy().setPositionX(buoyXPosition);
                worldFields[i][j].getBuoy().setPositionY(buoyYPosition);
                buoyXPosition += 5;
            }
            buoyYPosition += 5;
        }
    }

    public WorldField[][] getFields() {
        return worldFields;
    }
}

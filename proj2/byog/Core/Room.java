package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;

public class Room extends GameObject {
    private TETile element = Tileset.WALL;
    private TETile[][] room;
    private int doorX;
    private int doorY;

    public Room(int w, int h) {
        super(w, h);
        doorX = xMaxLength / 2;
        doorY = 0;
        room = new TETile[xMaxLength][yMaxLength];
        generateWall();
        generateFloor();
        generateDoor();
    }

    public TETile[][] getRoom() {
        return room;
    }

    public int getDoorX() {
        return doorX;
    }

    public int getDoorY() {
        return doorY;
    }

    private void generateDoor() {
        room[doorX][doorY] = Tileset.UNLOCKED_DOOR;
    }

    private void generateWall() {
        for (int i = 0; i < xMaxLength; i++) {
            room[i][0] = Tileset.WALL;
        }

        for (int i = 0; i < xMaxLength; i++) {
            room[i][yMaxLength - 1] = Tileset.WALL;
        }

        for (int j = 0; j < yMaxLength; j++) {
            room[0][j] = Tileset.WALL;
        }

        for (int j = 0; j < yMaxLength; j++) {
            room[xMaxLength - 1][j] = Tileset.WALL;
        }

    }

    private void generateFloor() {
        for (int i = 1; i < xMaxLength - 1; i++) {
            for (int j = 1; j < yMaxLength - 1; j++) {
                room[i][j] = Tileset.FLOOR;
            }
        }
    }

    @Override
    public TETile getElement() {
        return element;
    }

    public TETile getElement(int x, int y) {
        return room[x][y];
    }

    public int[] chooseDoorPos(ArrayList<int[]> wallPos) {
        int index = RANDOM.nextInt(wallPos.size());
        int[] position = wallPos.get(index);
        return position;
    }

    public void doorLeft() {
        room[doorX][doorY] = Tileset.WALL;
        doorX = 0;
        doorY = yMaxLength / 2;
        generateDoor();
    }

    public void doorUp() {
        room[doorX][doorY] = Tileset.WALL;
        doorX = xMaxLength / 2;
        doorY = yMaxLength - 1;
        generateDoor();
    }


    public void doorRight() {
        room[doorX][doorY] = Tileset.WALL;
        doorX = xMaxLength - 1;
        doorY = yMaxLength / 2;
        generateDoor();
    }

    public void doorDown() {
        room[doorX][doorY] = Tileset.WALL;
        doorX = xMaxLength / 2;
        doorY = 0;
        generateDoor();
    }

}


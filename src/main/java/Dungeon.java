package main.java;

import java.util.ArrayList;
import java.util.Arrays;

public class Dungeon {
    int number;
    ArrayList<Floor> floors;
    Floor currentFloor;

    public Dungeon(int number, int numberOfFloors) {
        this.number = number + 1;
        floors = generateFloors(numberOfFloors);
        currentFloor = floors.get(0);
    }

    public ArrayList<Floor> generateFloors(int numberOfFloors) {
        ArrayList<Floor> floors = new ArrayList<Floor>();
        for (int i = 0; i < numberOfFloors; i++) {
            floors.add(new Floor(i));
        }
        return floors;
    }

    public void ascendToNextFloor() {
        floors.remove(0);
        currentFloor = floors.get(0);
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public int getNumber() {
        return number;
    }

    public void setCurrentFloor(int floorNum) {
        this.currentFloor = floors.get(floorNum);
    }
}

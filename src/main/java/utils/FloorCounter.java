package utils;

import java.util.TreeMap;

public class FloorCounter implements Runnable {
    private static final TreeMap<String, TreeMap<Integer, Integer>> floorMap = new TreeMap<>();
    private final String city;
    private final int floors;

    public FloorCounter(String city, int floors) {
        this.city = city;
        this.floors = floors;
    }

    public static TreeMap<String, TreeMap<Integer, Integer>> getFloorMap() {
        return floorMap;
    }

    public String getCity() {
        return city;
    }

    public int getFloors() {
        return floors;
    }

    @Override
    public void run() {
        synchronized (floorMap) {
            TreeMap<Integer, Integer> runFloorMap = floorMap.get(city);
            //if city null ==> create
            if (runFloorMap == null) {
                runFloorMap = new TreeMap<Integer, Integer>();
                runFloorMap.put(floors, 1);
                floorMap.put(city, runFloorMap);
                return;
            }
            //floor check
            Integer counter = runFloorMap.get(floors);
            if (counter == null) {
                runFloorMap.put(floors, 1);
            } else {
                runFloorMap.put(floors, counter + 1);
            }
            floorMap.put(city, runFloorMap);
        }
    }
}

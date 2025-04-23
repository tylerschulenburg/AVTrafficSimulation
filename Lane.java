package objects;

import java.util.*;

public class Lane {
    // Defining the Y-range for each lane
    public final double laneTop, laneBottom;
    public ArrayList<Car> carsInLane;
    
    // Constructor to define lane ranges and initialize the car list
    public Lane(double laneTop, double laneBottom) {
        this.laneTop = laneTop;
        this.laneBottom = laneBottom;
        this.carsInLane = new ArrayList<Car>();
    }

    // Method to check if the car is within the lane's Y-bound
    public boolean isInLane(Car car) {
        return car.y > laneTop && car.y < laneBottom;
    }
    
    // Method to add a car to the lane, sorted by x-position
    public void insertCar(Car car) {
        if (isInLane(car)) {
            // Find the correct position based on the car's x position
            int insertIndex = 0;
            while (insertIndex < carsInLane.size() && carsInLane.get(insertIndex).x < car.x) {
                insertIndex++;
            }
            carsInLane.add(insertIndex, car);
        }
    }
    
    // Method to remove a car from the lane
    public void removeCar(Car car) {
        carsInLane.remove(car);
    }
    
    // Method to get the next car in the lane in front of the given car
    public Car getNextCar(Car car) {
        int carIndex = carsInLane.indexOf(car);
        if (carIndex >= 0 && carIndex + 1 < carsInLane.size()) {
            return carsInLane.get(carIndex + 1);
        }
        return null;  // No car in front of this one
    }
    
    
    // Method to get the car behind the given car in the lane
    public Car getPrevCar(Car car) {
        int carIndex = carsInLane.indexOf(car);
        if (carIndex > 0) {
            return carsInLane.get(carIndex - 1);
        }
        return null;  // No car behind this one
    }
    
    public double assignYPosition() {
        return (laneTop + laneBottom) / 2;  // Assign Y to the middle of the lane
    }
    
    // Method to update the lane's cars (could be used for autonomous vehicle logic)
    public void update() {
        // Example: you can loop through all cars to apply bilateral distancing or other AI logic
        for (Car car : carsInLane) {
        	
        	car.update();
            // Autonomous behavior (like bilateral distancing) can be handled here
        }
    }
    
    // Method to return a list of cars in this lane (for debugging or other purposes)
    public ArrayList<Car> getCarsInLane() {
        return carsInLane;
    }
}
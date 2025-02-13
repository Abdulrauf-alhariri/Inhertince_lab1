
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.awt.geom.Point2D;
import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Set;

public class CarTransporter extends Truck {
    private Car[] loadedCars;
    private boolean rampUpTrue;
    private int currentCarNr;
    private static final Set<Car> globalAllLoadedCars = new HashSet<>();


    public CarTransporter() {
        super(2, Color.red, 500, "MercedesBenz");
        this.rampUpTrue = false;
        this.loadedCars = new Car[10];
        this.currentCarNr = 0;

    }

    public Car[] getLoadedCars() {
        return this.loadedCars;
    }

    public void rampRaise() {
        this.rampUpTrue = true;
    }

    public void rampLower() {
        this.rampUpTrue = false;
    }

    public Boolean getRamp() {
        return this.rampUpTrue;
    }
    // This method load the car
    public void load(Car car) {
        boolean isInRange = isIsInRange(car);
        boolean spaceForCar = this.currentCarNr < this.loadedCars.length + 1;
        // We check ramp is down, car coordinates is in range 10 to car transporter
        // we check car is not loaded
        if(!globalAllLoadedCars.contains(car)) {
            if (!this.getRamp() && isInRange && spaceForCar) {
                // we load the car to the car transporter
                car.setCurrentSpeed(0);
                this.loadedCars[this.currentCarNr] = car;
                this.currentCarNr += 1;
                globalAllLoadedCars.add(car);
            }
            else {
                System.out.println("Cannot load car!");
            }
        }
    }
    // Maybe better with circle
    private boolean isIsInRange(Car car) {
        Point2D.Double carCoordinates = car.getCoordinates();
        // Here we check coordinates in range 10
        Double xRange = Math.abs(carCoordinates.x - this.getCoordinates().x);
        Double yRange = Math.abs(carCoordinates.y - this.getCoordinates().y);
        boolean isInRange = xRange <= 10 && yRange <= 10;
        return isInRange;
    }

    public void unloadCarPlacement(Car car) {
        Direction transportDirection = this.getDirection();
        if (transportDirection == Direction.NORTH) {
            car.setCoordinates(this.getCoordinates().x, this.getCoordinates().y - 10);
        } else if (transportDirection == Direction.EAST) {
            car.setCoordinates(this.getCoordinates().x - 10, this.getCoordinates().y);
        } else if (transportDirection == Direction.SOUTH) {
            car.setCoordinates(this.getCoordinates().x, this.getCoordinates().y + 10);
        } else if (transportDirection == Direction.WEST) {
            car.setCoordinates(this.getCoordinates().x + 10, this.getCoordinates().y );
        }
    }

    // Here we unload cars, and we begin with the last car was loaded
    public Car unload() {
        // It will be good to check if there is any object
        // on the new coordinates of the unloaded car, and within the coordinate system (within a map)
        if (!this.getRamp()) {
            Car car = this.loadedCars[this.currentCarNr - 1];
            this.unloadCarPlacement(car);   //Set placement of car when unloading.
            this.loadedCars[this.currentCarNr - 1] = null;
            globalAllLoadedCars.remove(car);
            this.currentCarNr -= 1;
            return car;
        }
        System.out.println("Ramp is not down!");
        return null;
    }

    @Override
    public void move() {
        if(this.getRamp()) {
            super.move();
        }

        // We set the coordinates of each car to the coordinates of the
        // Transport car
        for(int i = 0; i < this.currentCarNr; i++) {
            Car car = this.loadedCars[i];
            car.setCoordinates(this.getCoordinates().x, this.getCoordinates().y);
        }
    }

    @Override
    protected double speedFactor() {
        if(!this.getRamp()) {
            return super.speedFactor();
        }
        return 0;
    }
}

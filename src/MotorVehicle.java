
import java.awt.*;
import java.awt.geom.Point2D;

public abstract class MotorVehicle implements Movable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private Point2D.Double xAndY;
    private Direction direction;

    public MotorVehicle(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.xAndY = new Point2D.Double(0,0);
        this.direction = Direction.NORTH;
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public void setCurrentSpeed(double speed) {currentSpeed = speed;}

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public Direction getDirection() {return direction;}

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public Point2D.Double getCoordinates() {
        return this.xAndY;
    }

    public void setCoordinates(Double x, Double y) {
        this.xAndY.x = x;
        this.xAndY.y = y;
    }

    public void gas(double amount){
        if(amount > 1) {
            amount = 1;
        } else if (amount < 0) {
            amount = 0;
        }
        incrementSpeed(amount);
    }

    public void brake(double amount){
        if(amount > 1) {
            amount = 1;
        } else if (amount < 0) {
            amount = 0;
        }
        decrementSpeed(amount);
    }

    public void incrementSpeed(double amount) {
        double attemptedSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,  getEnginePower());
        setCurrentSpeed(attemptedSpeed);
    }
    public void decrementSpeed(double amount) {
        double attemptedSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
        setCurrentSpeed(attemptedSpeed);

    }

    public double speedFactor(){
        return getEnginePower() * 0.01;
    }


    @Override
    public void move() {
        if (this.direction == Direction.NORTH) {
            this.xAndY.y += this.currentSpeed;
        } else if (this.direction == Direction.EAST) {
            this.xAndY.x += this.currentSpeed;
        } else if (this.direction == Direction.SOUTH) {
            this.xAndY.y -= this.currentSpeed;
        } else if (this.direction == Direction.WEST) {
            this.xAndY.x -= this.currentSpeed;
        }
    }

    @Override
    public void turnLeft() {
        if (this.direction == Direction.NORTH) {
            this.direction = Direction.WEST;
        } else if (this.direction == Direction.WEST) {
            this.direction = Direction.SOUTH;
        } else if (this.direction == Direction.SOUTH) {
            this.direction = Direction.EAST;
        } else if (this.direction == Direction.EAST) {
            this.direction = Direction.NORTH;
        }
    }

    @Override
    public void turnRight() {
        if (this.direction == Direction.NORTH) {
            this.direction = Direction.EAST;
        } else if (this.direction == Direction.WEST) {
            this.direction = Direction.NORTH;
        } else if (this.direction == Direction.SOUTH) {
            this.direction = Direction.WEST;
        } else if (this.direction == Direction.EAST) {
            this.direction = Direction.SOUTH;
        }
    }
}

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

public abstract class Car implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    private final Point2D.Double xAndY;
    private Direction direction;

    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.xAndY = new Point2D.Double(0,0);
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    abstract void incrementSpeed(double amount);

    abstract double speedFactor();

    abstract void decrementSpeed(double amount);

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

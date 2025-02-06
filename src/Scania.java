import java.awt.*;

public class Scania extends Car {
    private double angle;
    public Scania() {
        super(2,Color.black,300, "Scania");
        this.angle = 0;
    }

    public void flatBedRaise(double amount){
        double newAngle = this.angle + amount;

        if(newAngle > 70) {
            this.angle = 70;
        } else {
            this.angle = newAngle;
        }
    }

    public void flatBedLower(double amount){
        double newAngel = this.angle - amount;

        if (newAngel < 0){
            this.angle = 0;
        } else {
            this.angle = newAngel;
        }
    }

    public double getFlatBedAngle() {
        // Angle between 0 and 70
        return this.angle;
    }

    @Override
    public void move(){
        Direction direction = getDirection();
        double currentSpeed = getCurrentSpeed();
        // Car move only if angle is 0
        if (this.angle == 0) {
            if (direction == Direction.NORTH) {
                this.xAndY.y += currentSpeed;
            } else if (direction == Direction.EAST) {
                this.xAndY.x += currentSpeed;
            } else if (direction == Direction.SOUTH) {
                this.xAndY.y -= currentSpeed;
            } else if (direction == Direction.WEST) {
                this.xAndY.x -= currentSpeed;
            }
        }
    }

    @Override
    void incrementSpeed(double amount) {
        double attemptedSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower());
        if( attemptedSpeed > getEnginePower()) {
            attemptedSpeed = getEnginePower();
        } else if (attemptedSpeed < 0){
            attemptedSpeed = 0;
        }else{
            attemptedSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower());};
        setCurrentSpeed(attemptedSpeed);
    }

    @Override
    double speedFactor() {
        if(this.angle == 0) {
        return getEnginePower() * 0.1; }
        return 0;
    }

    @Override
    void decrementSpeed(double amount) {
        double attemptedSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
        if( attemptedSpeed > getEnginePower()) {
            attemptedSpeed = getEnginePower();
        } else if (attemptedSpeed < 0){
            attemptedSpeed = 0;
        }else{
            attemptedSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);};
        setCurrentSpeed(attemptedSpeed);
    }
}

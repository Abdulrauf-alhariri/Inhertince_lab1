
import java.awt.*;

public class Scania extends Truck {
    private double angle;
    public Scania() {
        super(2,Color.black,130, "Scania");
        this.angle = 0;
    }
    //Separat klass för flaket?
    public void flatBedRaise(double amount){
        double newAngle = this.angle + amount;

        if(newAngle > 70) {
            this.angle = 70;
        } else {
            this.angle = newAngle;
        }
    }

    public void flatBedLower(double amount){
        double newAngle = this.angle - amount;

        if (newAngle < 0){
            this.angle = 0;
        } else {
            this.angle = newAngle;
        }
    }

    public double getFlatBedAngle() {
        // Angle between 0 and 70
        return this.angle;
    }

    @Override
    public void move(){
        // Car move only if angle is 0
        if (this.angle == 0) {
            super.move();

        }
    }

    @Override
    protected double speedFactor() {
        if(this.angle == 0) {
        return super.speedFactor();}

        return 0;
    }

}

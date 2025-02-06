import java.awt.*;

public class CarTransporter extends Truck {
    private int loadCapacity;
    private boolean rampUpTrue;

    public CarTransporter() {
        super(2, Color.red, 500, "MercedesBenz");
        this.rampUpTrue = false;
    }

    public void rampRaise() {
        this.rampUpTrue = true;

    }

    public void rampLower() {
        this.rampUpTrue = false;
    }

    public void load() {

    }

    public void unload() {

    }

    @Override
    public void move() {
        if(!this.rampUpTrue) {
            super.move();
        }
    }

    @Override
    public double speedFactor() {
        if(!this.rampUpTrue) {
            return super.speedFactor();
        }
        return 0;
    }
}

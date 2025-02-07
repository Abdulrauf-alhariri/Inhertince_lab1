import java.awt.*;

public class Car extends MotorVehicle {
    private boolean carLoaded;


    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
        this.carLoaded = false;
    }

    public boolean getCarLoaded() {
        return this.carLoaded;
    }

    public void setCarLoaded() {
        this.carLoaded = true;
    }

    public void unsetCarLoaded() {
        this.carLoaded = false;
    }



}


import java.awt.*;

public class Volvo240 extends Car {

    private final static double trimFactor = 1.25;
    private double speed;
    
    public Volvo240(){
        super(4, Color.black, 100, "Volovo240");
        this.stopEngine();
    }

    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

    public void incrementSpeed(double amount){
        double attemptedSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower());
        if( attemptedSpeed > getEnginePower()) {
            speed = getEnginePower();
        } else if (attemptedSpeed < 0){
            speed = 0;
        }else{
            speed = Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower());};
        setCurrentSpeed(speed);
    }

    public void decrementSpeed(double amount){
        double attemptedSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
        if( attemptedSpeed > getEnginePower()) {
            speed = getEnginePower();
        } else if (attemptedSpeed < 0){
            speed = 0;
        }else{
            speed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);};
        setCurrentSpeed(speed);
    }
}

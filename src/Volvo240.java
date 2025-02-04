
import java.awt.*;

public class Volvo240 extends Car {

    private final static double trimFactor = 1.25;
    
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
            attemptedSpeed = getEnginePower();
        } else if (attemptedSpeed < 0){
            attemptedSpeed = 0;
        }else{
            attemptedSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower());};
        setCurrentSpeed(attemptedSpeed);
    }

    public void decrementSpeed(double amount){
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

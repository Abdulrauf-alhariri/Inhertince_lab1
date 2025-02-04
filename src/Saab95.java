
import java.awt.*;

public class Saab95 extends Car{

    private boolean turboOn;
    
    public Saab95(){
        super(2,Color.red, 125, "src.Saab95");
	    turboOn = false;
        this.stopEngine();
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }
    
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

    public void incrementSpeed(double amount){
        double attemptedSpeed = getCurrentSpeed() + speedFactor() * amount;
        if( attemptedSpeed > getEnginePower()) {
            attemptedSpeed = getEnginePower();
        } else if (attemptedSpeed < 0){
            attemptedSpeed = 0;
        }else{
            attemptedSpeed = getCurrentSpeed() + speedFactor() * amount;};

        setCurrentSpeed(attemptedSpeed);
    }

    public void decrementSpeed(double amount){
        double attemptedSpeed = getCurrentSpeed() - speedFactor() * amount;
        if( attemptedSpeed > getEnginePower()) {
            attemptedSpeed = getEnginePower();
        } else if (attemptedSpeed < 0){
            attemptedSpeed = 0;
        }else{
            attemptedSpeed = getCurrentSpeed() - speedFactor() * amount;};

        setCurrentSpeed(attemptedSpeed);
    }


}

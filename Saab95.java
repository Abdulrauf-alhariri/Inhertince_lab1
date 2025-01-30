import java.awt.*;

public class Saab95 extends Car{

    private boolean turboOn;
    private double speed;
    
    public Saab95(){
        super(2,Color.red, 125, "Saab95");
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
            speed = getEnginePower();
        } else if (attemptedSpeed < 0){
            speed = 0;
        }else{
            speed = getCurrentSpeed() + speedFactor() * amount;};

        setCurrentSpeed(speed);
    }

    public void decrementSpeed(double amount){
        double attemptedSpeed = getCurrentSpeed() - speedFactor() * amount;
        if( attemptedSpeed > getEnginePower()) {
            speed = getEnginePower();
        } else if (attemptedSpeed < 0){
            speed = 0;
        }else{
            speed = getCurrentSpeed() - speedFactor() * amount;};

        setCurrentSpeed(speed);
    }


}

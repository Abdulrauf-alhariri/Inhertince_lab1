
import java.awt.*;

public class Saab95 extends Car {

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

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }


}

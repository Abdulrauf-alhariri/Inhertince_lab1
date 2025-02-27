import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehichleController implements ControllerInterface{

    VehichleView frame;
    private final int delay = 50;
    private Timer timer = new Timer(delay, new VehichleController.TimerListener());
    public ModelFacade model;


    public VehichleController(ModelFacade model){
        this.timer.start();
        this.model = model;
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.ticker();
        }
    }

    public void gas(int amount) {
        model.gas(amount);
    }

    public void brake(int amount) {
        model.brake(amount);
    }

    public void turboOn() {
        model.turboOn();
    }

    public void stopVehicles() {
        model.stopVehicles();
    }

    public void startVehicles() {
        model.startVehicles();
    }
}


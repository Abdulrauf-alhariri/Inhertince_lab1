import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class VehichleController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    VehichleView frame;
    // A list of cars, modify if needed
     ArrayList<MotorVehicle> vehicles = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        VehichleController vc = new VehichleController();

        vc.vehicles.add(new Volvo240());
        vc.vehicles.add(new Saab95());
        vc.vehicles.add(new Scania());

        // Start a new view and send a reference of self
        vc.frame = new VehichleView("CarSim 1.0", vc);

        // Start the timer
        vc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    public Boolean isInRange(MotorVehicle vehicle, Workshop<MotorVehicle> workshop) {

        return false;
    };
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (MotorVehicle vehicle : vehicles) {
                vehicle.move();
                int x = (int) Math.round(vehicle.getCoordinates().x);
                int y = (int) Math.round(vehicle.getCoordinates().y);

                if(!(0 <= x && x < frame.drawPanel.worldSize.x) ||
                        !(0 <= y && y < frame.drawPanel.worldSize.y)){
                    vehicle.invertDirection();
                }




                frame.drawPanel.moveit(x, y, vehicle);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (MotorVehicle vehicle : vehicles
                ) {
            vehicle.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (MotorVehicle vehicle : vehicles
        ) {
            vehicle.brake(brake);
        }
    }

    void turboOn() {

        for(MotorVehicle vehicle : vehicles) {
            if(vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    void stopVehicles() {
        for(MotorVehicle vehicle : vehicles)  {
            vehicle.stopEngine();
        }
    }
    void startVehicles() {
        for(MotorVehicle vehicle : vehicles)  {
            vehicle.startEngine();
        }
    }
}

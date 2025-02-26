import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ModelFacade {
    ArrayList<MotorVehicle> vehicles = new ArrayList<>();
    ArrayList<Workshop> workshops = new ArrayList<>();
    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());
    private List<ModelUpdated> observers;

    public ModelFacade(){
        this.timer.start();
    }
    // Vi borde verkligen ändra ">"-tecknet men det funkar nu. :)
    public Boolean isTouching(MotorVehicle v, Workshop w) {
        if (v.getCoordinates().x > w.getCoordinates().x) {
            return true;
        }
        return false;
    }

    public Boolean isTouching(MotorVehicle v1, MotorVehicle v2) {
        if (v1.getCoordinates() == v2.getCoordinates()) {
            return true;
        }
        return false;
    }

    void loadWorkshops() {
        for(Workshop w: workshops){
            for(MotorVehicle v : vehicles) {
                if(isTouching(v, w)){
                    w.vehicleEntry(v);
                }
            }
        }
    }
    // DrawPanel ska lyssna på ett interface.
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (MotorVehicle vehicle : vehicles) {
                vehicle.move();
                int x = (int) Math.round(vehicle.getCoordinates().x);
                int y = (int) Math.round(vehicle.getCoordinates().y);

                if(!(0 <= x && x < worldSize.x) ||
                        !(0 <= y && y < worldSize.y)) {
                    vehicle.invertDirection();
                }
                loadWorkshops();
            }
        }
    }

    public void addObserver(ModelUpdated v){
        observers.add(v);
    }
    public void removeObserver(ModelUpdated u){
        observers.remove(u);
    }
    private void notifyObservers(ModelUpdated x){
        for(ModelUpdated object : observers){
            object.modelUpdateNotification();
        }
    }
}

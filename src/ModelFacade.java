import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelFacade {
    ArrayList<MotorVehicle> vehicles = new ArrayList<>();
    ArrayList<Workshop> workshops = new ArrayList<>();
    private List<ModelUpdated> observers = new ArrayList<>();
    private static int worldSizeX = 800;
    private static int worldSizeY = 800;

    // Vi borde verkligen ändra ">"-tecknet men det funkar nu. :)
    public Boolean isTouching(MotorVehicle v, Workshop w) {
        if (v.getCoordinates().x > w.getCoordinates().x && v.getCoordinates().x < (w.getCoordinates().x + 50)) {
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
        for (Workshop w : workshops) {
            for (MotorVehicle v : vehicles) {
                if (isTouching(v, w)) {
                    w.vehicleEntry(v);
                }
            }
        }
    }
    void unloadWorkshop() {
        for (Workshop w : workshops) {
            for (Object v : w.vehiclesInRepair) {
                w.vehicleExit((MotorVehicle) v);
            }
        }
    }
    void addVehicle() {
        MotorVehicle[] vehicleList = {new Volvo240(), new Saab95(), new Scania()};
        if (vehicles.size() <= 10) {
            int randomVehicle = new Random().nextInt(vehicleList.length);
            double randomX = new Random().nextDouble(500);
            double randomY = new Random().nextDouble(500);
            MotorVehicle vehicle = vehicleList[randomVehicle];
            vehicle.setCoordinates(randomX, randomY);
            vehicles.add(vehicle);
        }
    }
    void removeVehicle() {
        if(vehicles.size() > 0) {
            vehicles.removeLast();
        }
    }


    // DrawPanel ska lyssna på ett interface.
    void ticker() {
        for (MotorVehicle vehicle : vehicles) {
            vehicle.move();
            int x = (int) Math.round(vehicle.getCoordinates().x);
            int y = (int) Math.round(vehicle.getCoordinates().y);

            if (!(0 <= x && x < worldSizeX) ||
                    !(0 <= y && y < worldSizeY)) {
                vehicle.invertDirection();
            }
            loadWorkshops();
        }
        notifyObservers();
    }


    public void addObserver(ModelUpdated v) {
        observers.add(v);
    }

    public void removeObserver(ModelUpdated u) {
        observers.remove(u);
    }

    public  int getWorldSizeX() {
        return worldSizeX;
    }

    public int getWorldSizeY() {
        return worldSizeY;
    }

    private void notifyObservers() {
        for (ModelUpdated object : observers) {
            object.modelUpdateNotification();
        }
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (MotorVehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (MotorVehicle vehicle : vehicles
        ) {
            vehicle.brake(brake);
        }
    }

    public void turboOn() {
        for(MotorVehicle vehicle : vehicles) {
            if(vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    public void stopVehicles() {
        for(MotorVehicle vehicle : vehicles)  {
            vehicle.stopEngine();
        }
    }

    public void startVehicles() {
        for(MotorVehicle vehicle : vehicles)  {
            vehicle.startEngine();
        }
    }
}
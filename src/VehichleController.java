public class VehichleController implements ControllerInterface{

    VehichleView frame;


    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (MotorVehicle vehicle : vehicles
                ) {
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

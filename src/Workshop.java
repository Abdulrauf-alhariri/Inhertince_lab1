
import java.awt.*;
import java.util.*;
import java.util.List;

public class Workshop <T extends MotorVehicle> {

    private int maximumVehicles; // Maximum vehicles that can be accepted simultaneously
    private Set<Class<? extends T>> servicedVehicles; // Types of vehicles accepted for repair
    protected List<T> vehiclesInRepair; // A list of the vehicles currently in the workshop

    @SafeVarargs // Allows for any number of Vehicle classes (incl their subclasses) to be added as allowed
    public Workshop(int maximumVehicles, Class<? extends T>...allowedVehicleTypes) {
        this.maximumVehicles = maximumVehicles;
        this.servicedVehicles = new HashSet<>(Arrays.asList(allowedVehicleTypes));
        this.vehiclesInRepair = new ArrayList<>();
    }
    // If function called by the vehicle; returning a boolean allows it to know if admitted to workshop or not
    public boolean vehicleEntry(T vehicle){
        if ( ! servicedVehicles.contains(vehicle.getClass())){
            return false;}
        if (vehiclesInRepair.size() >= maximumVehicles){
            return false;}
        vehiclesInRepair.add(vehicle);
        return true;}

    public void vehicleExit(T vehicle){
        vehiclesInRepair.remove(vehicle);    // do we need to know that it is the same instance of the class??
    }


    }
/* TEST */
class Main {
    public static void main(String[] args) {
        Workshop<Car> carWorkshop = new Workshop<>(10, Saab95.class);{
        Volvo240 aVolvo = new Volvo240();
        Saab95 aSaab95 = new Saab95();
        Truck aTruck = new Truck(2, Color.black,100,"BIG TRUCK");

        carWorkshop.vehicleEntry(aVolvo);
        carWorkshop.vehicleEntry(aTruck);
        }
    }}




import java.awt.*;
import java.util.*;
import java.util.List;

/*Creates a workshop for MotorVehicles. In instantiation CAN define which Class and its subclasses
* will be allowed to be serviced in general. within < >,  But also as arguments specify which Classes(Vehicles) of those that will be served,
* these can be updated later. At the moment Vehicles can be serviced in several workshops at the same time, which could potentially cause problems. */

public class Workshop <T extends MotorVehicle> {

    private int maximumVehicles; // Maximum vehicles that can be accepted simultaneously
    private Set<Class<? extends T>> servicedVehicles; // Types of vehicles accepted for repair
    protected List<T> vehiclesInRepair; // A list of the vehicles currently in the workshop. List gives option of indexing i.e. first-in, first-out
    private static final Set<MotorVehicle> globalAllVehiclesInRepair = new HashSet<>(); //Keeps track which vehicles are already in service.

    @SafeVarargs // Allows for any number of Vehicle classes (incl their subclasses) to be added as allowed
    public Workshop(int maximumVehicles, Class<? extends T>...allowedVehicleTypes) {
        this.maximumVehicles = maximumVehicles;
        this.servicedVehicles = new HashSet<>(Arrays.asList(allowedVehicleTypes));
        this.vehiclesInRepair = new ArrayList<>();
    }
    // If function called by the vehicle; returning a boolean allows it to know if admitted to workshop or not
    protected boolean vehicleEntry(T vehicle){
        if (globalAllVehiclesInRepair.contains(vehicle)) {  // Won't add vehicle if already in repair
            return false;}
        if ( ! servicedVehicles.contains(vehicle.getClass())){
            return false;}
        if (vehiclesInRepair.size() >= maximumVehicles){
            return false;}

        globalAllVehiclesInRepair.add(vehicle);
        vehiclesInRepair.add(vehicle);
        return true;}

    protected void vehicleExit(T vehicle){
        vehiclesInRepair.remove(vehicle);// Will remove the same reference object of vehicle.
        globalAllVehiclesInRepair.remove(vehicle);
    }
    protected void addServicedVehicle(Class<? extends T> vehicleClass){
        servicedVehicles.add(vehicleClass);
    }

    }
/* TEST */
/*
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
*/

// TEST




import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

/*Creates a workshop for MotorVehicles. In instantiation CAN define which Class and its subclasses
* will be allowed to be serviced in general. within < >,  But also as arguments specify which Classes(Vehicles) of those that will be served,
* these can be updated later. At the moment Vehicles can be serviced in several workshops at the same time, which could potentially cause problems. */

public class Workshop <T extends MotorVehicle> {

    private int maximumVehicles; // Maximum vehicles that can be accepted simultaneously
    private Set<Class<? extends T>> servicedVehicles; // Types of vehicles accepted for repair
    protected List<T> vehiclesInRepair; // A list of the vehicles currently in the workshop. List gives option of indexing i.e. first-in, first-out
    private static final Set<MotorVehicle> globalAllVehiclesInRepair = new HashSet<>();//Keeps track which vehicles are already in service.
    private Point2D.Double xAndY;
    private String workshopName;
    @SafeVarargs // Allows for any number of Vehicle classes (incl their subclasses) to be added as allowed
    public Workshop(int maximumVehicles, String workshopName, Class<? extends T>...allowedVehicleTypes) {
        this.maximumVehicles = maximumVehicles;
        this.servicedVehicles = new HashSet<>(Arrays.asList(allowedVehicleTypes));
        this.vehiclesInRepair = new ArrayList<>();
        this.xAndY = new Point2D.Double(300,0);
        this.workshopName = workshopName;
    }
    // If function called by the vehicle; returning a boolean allows it to know if admitted to workshop or not
    public String getWorkshopName(){ return this.workshopName; }

    protected boolean vehicleEntry(T vehicle){
        if (globalAllVehiclesInRepair.contains(vehicle)) {  // Won't add vehicle if already in repair
            return false;}
        if ( ! servicedVehicles.contains(vehicle.getClass())){
            return false;}
        if (vehiclesInRepair.size() >= maximumVehicles){
            return false;}

        globalAllVehiclesInRepair.add(vehicle);
        vehiclesInRepair.add(vehicle);
        vehicle.stopEngine();
        vehicle.setIsLoaded();
        return true;}

    protected void vehicleExit(T vehicle){
        vehiclesInRepair.remove(vehicle);// Will remove the same reference object of vehicle.
        globalAllVehiclesInRepair.remove(vehicle);
        vehicle.setIsLoaded();
        System.out.println(vehicle.getIsLoaded());
        if (vehicle.getDirection() == Direction.NORTH) {
            vehicle.setCoordinates(vehicle.getCoordinates().x, vehicle.getCoordinates().y - 50);
        } else if (vehicle.getDirection() == Direction.EAST) {
            vehicle.setCoordinates(vehicle.getCoordinates().x + 100, vehicle.getCoordinates().y);
        } else if (vehicle.getDirection() == Direction.SOUTH) {
            vehicle.setCoordinates(vehicle.getCoordinates().x, vehicle.getCoordinates().y + 50);
        } else if (vehicle.getDirection() == Direction.WEST) {
            vehicle.setCoordinates(vehicle.getCoordinates().x - 100, vehicle.getCoordinates().y);
        }
    }
    protected void addServicedVehicle(Class<? extends T> vehicleClass){
        servicedVehicles.add(vehicleClass);
    }

    protected Point2D.Double getCoordinates() {
        return this.xAndY;
    }

    protected void setCoordinates(Double x,Double y) {
        this.xAndY.x = x;
        this.xAndY.y = y;
    }

    }
/* TEST */
/*
class Main {
    public static void main(String[] args) {
        Workshop<Car> carWorkshop = new Workshop<>(10, Saab95.class, volco240.class);{
        Volvo240 aVolvo = new Volvo240();
        Saab95 aSaab95 = new Saab95();
        Truck aTruck = new Truck(2, Color.black,100,"BIG TRUCK");

        carWorkshop.vehicleEntry(aVolvo);
        carWorkshop.vehicleEntry(aTruck);
        }
    }}
*/

// TEST



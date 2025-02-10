import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopTest {
    private Workshop<Saab95> saab95OnlyCarWorkshop;
    private Workshop<Car> carWorkshop;
    private Workshop<Truck> truckWorkshop;
    private Workshop<CarTransporter> carTransporterWorkshop;
    private Workshop generalVehicleWorkshop;

    private Volvo240 volvoA;
    private Volvo240 volvoB;
    private Saab95 saab95A;
    private Truck truckA;
    private Truck truckB;
    private CarTransporter carTransporterA;

    @BeforeEach
    void setUp() {
        saab95OnlyCarWorkshop = new Workshop<Saab95>(10, Saab95.class);
        carWorkshop = new Workshop<Car>(2, Saab95.class, Volvo240.class);
        carTransporterWorkshop = new Workshop<>(10, CarTransporter.class);
        truckWorkshop = new Workshop<>(10, Truck.class);
        generalVehicleWorkshop = new Workshop<>(3, Saab95.class, Volvo240.class, CarTransporter.class, Truck.class, Car.class);

        volvoA = new Volvo240();
        volvoB = new Volvo240();
        saab95A = new Saab95();
        truckA = new Truck(2, Color.black, 100, "BIG TRUCK");
        truckB = new Truck(2, Color.red, 80, "Small TRUCK");
        carTransporterA = new CarTransporter();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void vehicleEntry() {

        Assertions.assertTrue(saab95OnlyCarWorkshop.vehicleEntry(saab95A));
        Assertions.assertFalse(saab95OnlyCarWorkshop.vehicleEntry(saab95A)); // Duplicate entry
       // Assertions.assertFalse(saab95OnlyCarWorkshop.vehicleEntry(volvoA));  // Will give compile time error!


        Assertions.assertFalse(truckWorkshop.vehicleEntry(carTransporterA)); // Truck workshop is not initiated with carTransporter.

        Assertions.assertTrue(generalVehicleWorkshop.vehicleEntry(truckA));
        Assertions.assertTrue(generalVehicleWorkshop.vehicleEntry(volvoA));
        Assertions.assertTrue(generalVehicleWorkshop.vehicleEntry(carTransporterA));
        Assertions.assertFalse(generalVehicleWorkshop.vehicleEntry(truckB)); // Should already be full
        generalVehicleWorkshop.vehicleExit(truckA);
        Assertions.assertTrue(generalVehicleWorkshop.vehicleEntry(truckB));


    }

    @Test
    void vehicleExit() {
        carWorkshop.vehicleEntry(volvoA);
        carWorkshop.vehicleEntry(saab95A);
        assertTrue(carWorkshop.vehiclesInRepair.contains(volvoA));
        assertTrue(carWorkshop.vehiclesInRepair.contains(saab95A));
        carWorkshop.vehicleExit(volvoA);
        assertFalse(carWorkshop.vehiclesInRepair.contains(volvoA));
        carWorkshop.vehicleExit(saab95A);
        assertFalse(carWorkshop.vehiclesInRepair.contains(saab95A));

    }
}
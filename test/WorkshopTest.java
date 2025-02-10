import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopTest {
    private Workshop<Car> saab95OnlyCarWorkshop;
    private Workshop<Car> carWorkshop;
    private Workshop<Truck> truckWorkshop;

    private Volvo240 volvoA;
    private Volvo240 volvoB;
    private Saab95 saab95A;
    private Truck truckA;
    private Truck truckB;

    @BeforeEach
    void setUp() {
        saab95OnlyCarWorkshop = new Workshop<>(10, Saab95.class);
        carWorkshop = new Workshop<>(2, Car.class);
        truckWorkshop = new Workshop<>(10, Truck.class);

        volvoA = new Volvo240();
        volvoB = new Volvo240();
        saab95A = new Saab95();
        truckA = new Truck(2, Color.black, 100, "BIG TRUCK");
        truckB = new Truck(2, Color.red, 80, "Small TRUCK");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void vehicleEntry() {
        saab95OnlyCarWorkshop.vehicleEntry(saab95A);
    }

    @Test
    void vehicleExit() {
    }
}
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class CarTransporterTest {

    @Test
    void rampRaise() {
        CarTransporter transporter = new CarTransporter();
        transporter.rampRaise();

        assertEquals(true, transporter.getRamp());
    }

    @Test
    void rampLower() {
        CarTransporter transporter = new CarTransporter();
        transporter.rampLower();
        assertEquals(false, transporter.getRamp());

        // Here we check car can't move if ramp is down
        Point2D.Double transportCoord = transporter.getCoordinates();
        transporter.move();

        Point2D.Double newTransportCoord = transporter.getCoordinates();

        assertEquals(transportCoord, newTransportCoord);
    }

    @Test
    void load() {
        CarTransporter transporter = new CarTransporter();
        Car volvo = new Volvo240();
        Car saaab = new Saab95();

        transporter.load(volvo);
        transporter.load(saaab);

        Car[] cars = transporter.getLoadedCars();

        assertTrue(Arrays.asList(cars).contains(volvo));
        assertTrue(Arrays.asList(cars).contains(saaab));
    }

    @Test
    void unload() {
        CarTransporter transporter = new CarTransporter();
        Car volvo = new Volvo240();
        Car saaab = new Saab95();

        // First we load cars
        transporter.load(volvo);
        transporter.load(saaab);

        // Now we unload cars
        transporter.unload();

        // We check saab is unloaded now
        Car[] cars = transporter.getLoadedCars();

        assertFalse(Arrays.asList(cars).contains(saaab));

        transporter.unload();
        assertFalse(Arrays.asList(cars).contains(volvo));



    }
}
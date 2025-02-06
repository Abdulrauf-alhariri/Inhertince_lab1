import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {

    @Test
    void flatBedRaise() {
        Scania scania = new Scania();

        scania.flatBedRaise(12);
        assertEquals(12, scania.getFlatBedAngle());

        // Checking if angle can't be higher than 70
        scania.flatBedRaise(80);
        assertEquals(70, scania.getFlatBedAngle());
    }

    @Test
    void flatBedLower() {
        Scania scania = new Scania();

        // First we increase the angle != 0
        scania.flatBedRaise(30);

        // Now, we decrease the angle
        scania.flatBedLower(10);
        assertEquals(20,scania.getFlatBedAngle() );
        // CHecking angle can't be less than 0
        scania.flatBedLower(40);
        assertEquals(0, scania.getFlatBedAngle());
    }

    @Test
    void getFlatBedAngle() {
    }

    @Test
    void move() {
    }

    @Test
    void incrementSpeed() {
    }

    @Test
    void speedFactor() {
    }

    @Test
    void decrementSpeed() {
    }
}
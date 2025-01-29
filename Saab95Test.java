import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {
    Saab95 saab = new Saab95();

    @Test
    void getNrDoors() {
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    void getEnginePower() {
        assertEquals(125, saab.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        // Default is 0, when start engine is 0.1
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed());
    }

    @Test
    void getColor() {
        assertEquals(Color.red, saab.getColor());
    }

    @Test
    void setColor() {
        saab.setColor(Color.black);
        assertEquals(Color.black, saab.getColor());
    }

    @Test
    void startEngine() {
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void incrementSpeed() {
        double oldSpeed = saab.getCurrentSpeed();
        double newSpeed = oldSpeed + saab.speedFactor() * 1.5;
        saab.incrementSpeed(1.5);
        assertEquals(newSpeed, saab.getCurrentSpeed());
    }

    @Test
    void speedFactor() {
        // Testing with turbo on
        saab.setTurboOn();
        double engineTurboOn = saab.getEnginePower() * 0.01 * 1.3;
        assertEquals(engineTurboOn, saab.speedFactor());

        // Testing with turbo off
        saab.setTurboOff();
        double engineTurboOff = saab.getEnginePower() * 0.01 ;
        assertEquals(engineTurboOff, saab.speedFactor());
    }

    @Test
    void decrementSpeed() {
        double oldSpeed = saab.getCurrentSpeed();
        saab.decrementSpeed(1.5);
        assertTrue(saab.getCurrentSpeed() < oldSpeed);
    }

    @Test
    void move() {
        // Default dirction north
        saab.startEngine();
        double oldY = saab.xAndY.y;
        double newY = oldY + saab.getCurrentSpeed();
        saab.move();
        assertEquals(newY, saab.xAndY.y);

        // Change direction to east
        saab.turnRight();
        double oldX = saab.xAndY.x;
        double newX = oldX + saab.getCurrentSpeed();
        saab.move();
        assertEquals(newX, saab.xAndY.x);

    }

    @Test
    void turnLeft() {
        // Default direction is North
        saab.turnLeft();
        saab.turnLeft();

        assertEquals(Direction.SOUTH, saab.getDirection());
    }

    @Test
    void turnRight() {
        // Default direction is North
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();

        assertEquals(Direction.WEST, saab.getDirection());
    }
}


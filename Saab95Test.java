import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {

    @Test
    void getNrDoors() {
        Saab95 saab = new Saab95();
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    void getEnginePower() {
        Saab95 saab = new Saab95();
        assertEquals(125, saab.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        Saab95 saab = new Saab95();
        // Default is 0, when start engine is 0.1
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed());
    }

    @Test
    void getColor() {
        Saab95 saab = new Saab95();
        assertEquals(Color.red, saab.getColor());
    }

    @Test
    void setColor() {
        Saab95 saab = new Saab95();
        saab.setColor(Color.black);
        assertEquals(Color.black, saab.getColor());
    }

    @Test
    void startEngine() {
        Saab95 saab = new Saab95();
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        Saab95 saab = new Saab95();
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void incrementSpeed() {
        Saab95 saab = new Saab95();

        for (double i = 1; i < 2 ; i+= 0.1) {
            double oldSpeed = saab.getCurrentSpeed();
            double newSpeed = oldSpeed + saab.speedFactor() * i;
            saab.incrementSpeed(i);
            assertEquals(newSpeed, saab.getCurrentSpeed());
        }
    }

    @Test
    void speedFactor() {
        Saab95 saab = new Saab95();
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
        Saab95 saab = new Saab95();
        for (double i = 0; i < 2; i+= 0.1) {
            saab.incrementSpeed(i);
        }
        double oldSPeed = saab.getCurrentSpeed();
        saab.decrementSpeed(1.2);
        assertTrue(saab.getCurrentSpeed() < oldSPeed);

        // Decrementing the speed a lot to see that speed < 0
        for (double i = 0; i < 10; i+= 0.1) {
            saab.decrementSpeed(i);
        }
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void move() {
        Saab95 saab = new Saab95();
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
        Saab95 saab = new Saab95();
        // Default direction is North
        saab.turnLeft();
        saab.turnLeft();

        assertEquals(Direction.SOUTH, saab.getDirection());
    }

    @Test
    void turnRight() {
        Saab95 saab = new Saab95();
        // Default direction is North
        saab.turnRight();
        saab.turnRight();
        saab.turnRight();

        assertEquals(Direction.WEST, saab.getDirection());
    }

    @Test
    void gas() {
        // Giving amount > 1, like 10 then it should not gas more than 1
        Saab95 saab = new Saab95();
        double oldSpeed = saab.getCurrentSpeed() + saab.speedFactor() * 1;
        saab.gas(10);
        assertEquals(saab.getCurrentSpeed(), oldSpeed);

        // Here we check if amount < 0, then gas should set amount to 0
        saab.gas(-2);
        oldSpeed = saab.getCurrentSpeed();
        saab.incrementSpeed(0);
        assertEquals(saab.getCurrentSpeed(), oldSpeed);
    }

    @Test
    void brake() {
        // Giving amount > 1, like 10 then it should not gas more than 1
        Saab95 saab = new Saab95();

        // First we add some speed to the viechle
        for (double i = 0; i < 2; i+= 0.1) {
            saab.incrementSpeed(i);
        }

        double oldSpeed = saab.getCurrentSpeed() - saab.speedFactor() * 1;
        saab.brake(10);
        assertEquals(saab.getCurrentSpeed(), oldSpeed);

        // Here we check if amount < 0, then gas should set amount to 0
        saab.brake(-2);
        oldSpeed = saab.getCurrentSpeed();
        saab.decrementSpeed(0);
        assertEquals(saab.getCurrentSpeed(), oldSpeed);
    }
}


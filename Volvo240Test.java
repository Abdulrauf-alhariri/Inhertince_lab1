import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Volvo240Test {

    @Test
    void getNrDoors() {
        Volvo240 volvo = new Volvo240();
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    void getEnginePower() {
        Volvo240 volvo = new Volvo240();
        assertEquals(100, volvo.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        // Default is 0, when start engine is 0.1
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed());
    }

    @Test
    void getColor() {

        Volvo240 volvo = new Volvo240();
        assertEquals(Color.black, volvo.getColor());
    }

    @Test
    void setColor() {
        Volvo240 volvo = new Volvo240();
        volvo.setColor(Color.red);
        assertEquals(Color.red, volvo.getColor());
    }

    @Test
    void startEngine() {
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        Volvo240 volvo = new Volvo240();
        volvo.stopEngine();
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    void incrementSpeed() {
        Volvo240 volvo = new Volvo240();
        for (double i = 1; i < 2 ; i+= 0.1) {
            double oldSpeed = volvo.getCurrentSpeed();
            double newSpeed = Math.min(oldSpeed + volvo.speedFactor() * i,volvo.getEnginePower());
            volvo.incrementSpeed(i);
            assertEquals(newSpeed, volvo.getCurrentSpeed());
        }
    }

    @Test
    void speedFactor() {
        Volvo240 volvo = new Volvo240();
        double engineTurboOn = volvo.getEnginePower() * 0.01 * 1.25;
        assertEquals(engineTurboOn, volvo.speedFactor());
    }

    @Test
    void decrementSpeed() {
        Volvo240 volvo = new Volvo240();

        for (double i = 0; i < 2; i+= 0.1) {
            volvo.incrementSpeed(i);
        }
        double oldSPeed = volvo.getCurrentSpeed();
        volvo.decrementSpeed(1.2);
        assertTrue(volvo.getCurrentSpeed() < oldSPeed);

        // Decrementing the speed a lot to see that speed < 0
        for (double i = 0; i < 10; i+= 0.1) {
            volvo.decrementSpeed(i);
        }
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    void move() {
        Volvo240 volvo = new Volvo240();
        // Default dirction north
        volvo.startEngine();
        double oldY = volvo.xAndY.y;
        double newY = oldY + volvo.getCurrentSpeed();
        volvo.move();
        assertEquals(newY, volvo.xAndY.y);

        // Change direction to east
        volvo.turnRight();
        double oldX = volvo.xAndY.x;
        double newX = oldX + volvo.getCurrentSpeed();
        volvo.move();
        assertEquals(newX, volvo.xAndY.x);

    }

    @Test
    void turnLeft() {
        Volvo240 volvo = new Volvo240();
        // Default direction is North
        volvo.turnLeft();
        volvo.turnLeft();

        assertEquals(Direction.SOUTH, volvo.getDirection());
    }

    @Test
    void turnRight() {
        Volvo240 volvo = new Volvo240();
        // Default direction is North
        volvo.turnRight();
        volvo.turnRight();
        volvo.turnRight();

        assertEquals(Direction.WEST, volvo.getDirection());
    }
    @Test
    void gas() {
        // Giving amount > 1, like 10 then it should not gas more than 1
        Volvo240 volvo = new Volvo240();
        double oldSpeed = volvo.getCurrentSpeed() + volvo.speedFactor() * 1;
        volvo.gas(10);
        assertEquals(volvo.getCurrentSpeed(), oldSpeed);

        // Here we check if amount < 0, then gas should set amount to 0
        volvo.gas(-2);
        oldSpeed = volvo.getCurrentSpeed();
        volvo.incrementSpeed(0);
        assertEquals(volvo.getCurrentSpeed(), oldSpeed);
    }

    @Test
    void brake() {
        // Giving amount > 1, like 10 then it should not gas more than 1
        Volvo240 volvo = new Volvo240();

        // First we add some speed to the viechle
        for (double i = 0; i < 2; i+= 0.1) {
            volvo.incrementSpeed(i);
        }

        double oldSpeed = volvo.getCurrentSpeed() - volvo.speedFactor() * 1;
        volvo.brake(10);
        assertEquals(volvo.getCurrentSpeed(), oldSpeed);

        // Here we check if amount < 0, then gas should set amount to 0
        volvo.brake(-2);
        oldSpeed = volvo.getCurrentSpeed();
        volvo.decrementSpeed(0);
        assertEquals(volvo.getCurrentSpeed(), oldSpeed);
    }
}


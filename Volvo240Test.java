import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Volvo240Test {
    Volvo240 volvo = new Volvo240();

    @Test
    void getNrDoors() {
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    void getEnginePower() {
        assertEquals(100, volvo.getEnginePower());
    }

    @Test
    void getCurrentSpeed() {
        // Default is 0, when start engine is 0.1
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed());
    }

    @Test
    void getColor() {
        assertEquals(Color.black, volvo.getColor());
    }

    @Test
    void setColor() {
        volvo.setColor(Color.red);
        assertEquals(Color.red, volvo.getColor());
    }

    @Test
    void startEngine() {
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed());
    }

    @Test
    void stopEngine() {
        volvo.stopEngine();
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    void incrementSpeed() {
        double oldSpeed = volvo.getCurrentSpeed();
        double newSpeed = Math.min(oldSpeed + volvo.speedFactor() * 1.5,volvo.getEnginePower());
        volvo.incrementSpeed(1.5);
        assertEquals(newSpeed, volvo.getCurrentSpeed());
    }

    @Test
    void speedFactor() {
        double engineTurboOn = volvo.getEnginePower() * 0.01 * 1.25;
        assertEquals(engineTurboOn, volvo.speedFactor());
    }

    @Test
    void decrementSpeed() {
        double oldSpeed = volvo.getCurrentSpeed();
        if(oldSpeed > 0) {
            double newSpeed = Math.max(oldSpeed - volvo.speedFactor() * 1.5,0);
            volvo.decrementSpeed(1.5);
            assertTrue(volvo.getCurrentSpeed() < oldSpeed);
        }
    }

    @Test
    void move() {
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
        // Default direction is North
        volvo.turnLeft();
        volvo.turnLeft();

        assertEquals(Direction.SOUTH, volvo.getDirection());
    }

    @Test
    void turnRight() {
        // Default direction is North
        volvo.turnRight();
        volvo.turnRight();
        volvo.turnRight();

        assertEquals(Direction.WEST, volvo.getDirection());
    }
}


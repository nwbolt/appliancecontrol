package com.interview.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ModelTests {

    //Fan Tests

    @Test
    public void testFanSpeedAndOnOff() {
        Fan fan = new Fan(1, "test fan name");

        // default off
        assertEquals(0, fan.getSpeed());
        assertFalse(fan.isOn());

        // turn on by speed 1
        fan.setSpeed(1);
        assertEquals(1, fan.getSpeed());
        assertTrue(fan.isOn());

        // turn on by speed 2
        fan.setSpeed(2);
        assertEquals(2, fan.getSpeed());
        assertTrue(fan.isOn());

        // turn off
        fan.turnOff();
        assertEquals(0, fan.getSpeed());
        assertFalse(fan.isOn());
    }

     @Test(expected = IllegalArgumentException.class)
    public void testFanInvalidSpeedNegative() {
        Fan fan = new Fan(1, "test fan name");
        fan.setSpeed(-1);  
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFanInvalidSpeedAboveTwo() {
        Fan fan = new Fan(1, "test fan name");
        fan.setSpeed(3);  
    }

    // Light Tests

    @Test
    public void testLightOnOff() {
        Light light = new Light(2, "test light name");
        assertFalse(light.isOn());

        light.turnOn();
        assertTrue(light.isOn());

        light.turnOff();
        assertFalse(light.isOn());
    }

    // Air Conditioner Tests
    @Test
    public void testAirConditionerModeAndOnOff() {
        AirConditioner aircon = new AirConditioner(3, "test airconditioner name");

        assertFalse(aircon.isOn());

        aircon.setMode(AirConditioner.Mode.COOLING);
        assertEquals(AirConditioner.Mode.COOLING, aircon.getMode());
        assertTrue(aircon.isOn());

        aircon.turnOff();
        assertEquals(AirConditioner.Mode.OFF, aircon.getMode());
        assertFalse(aircon.isOn());
    }
    
}

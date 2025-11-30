package com.interview.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Test;

import com.interview.model.AirConditioner;
import com.interview.model.Fan;
import com.interview.model.Light;

public class ControllerTests {

    //Controller Tests

    @Test
    public void testControllerAddAndTurnOffAll() {

        SmartApplianceController controller = new SmartApplianceController();

        // Create appliance Objects
        Fan fan = new Fan(1, "test Fan");
        Light light = new Light(2, "test Light");
        AirConditioner airconditioner = new AirConditioner(3, "test Airconditioner");

        controller.addAppliance(fan);
        controller.addAppliance(light);
        controller.addAppliance(airconditioner);

        // give appliances Objects inital states 
        fan.setSpeed(1);
        light.turnOn();
        airconditioner.setMode(AirConditioner.Mode.COOLING);

        controller.turnAllOff();

        assertFalse(fan.isOn());
        assertFalse(light.isOn());
        assertFalse(airconditioner.isOn());

        assertEquals(0, fan.getSpeed());
        assertEquals(AirConditioner.Mode.OFF, airconditioner.getMode());
    }

    @Test
    public void testControllerGetApplianceById() {
        SmartApplianceController controller = new SmartApplianceController();

        // Create appliance Objects with ID 1
        Fan testfan = new Fan(1, "test Fan");
        controller.addAppliance(testfan);
        assertEquals(testfan, controller.getApplianceById(1));

       // test to get non-existing appliance by ID
       try {
            controller.getApplianceById(99);
            fail("Expected NoSuchElementException for non-existing appliance ID");
        } catch (NoSuchElementException e) {
            // expected exception
        }
    }
    
}

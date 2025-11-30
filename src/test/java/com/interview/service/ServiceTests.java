package com.interview.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

import com.interview.controller.SmartApplianceController;
import com.interview.model.AirConditioner;
import com.interview.model.Fan;
import com.interview.model.Light;

public class ServiceTests {


 @Test
    public void testAppliancesTurnOFFatYearlyUpdate() {
        SmartApplianceController controller = new SmartApplianceController();

        // Create appliance Objects
        Fan fan = new Fan(1, "test Fan");
        Light light = new Light(2, "test Light");
        AirConditioner airconditioner = new AirConditioner(3, "test Airconditioner");

        controller.addAppliance(fan);
        controller.addAppliance(light);
        controller.addAppliance(airconditioner);

        // give appliances Objects inital states 
        fan.setSpeed(2);
        light.turnOn();
        airconditioner.setMode(AirConditioner.Mode.COOLING);

        YearlyUpdateService service = new YearlyUpdateService(controller);
        service.performYearlyUpdate();

        assertFalse(fan.isOn());
        assertFalse(light.isOn());
        assertFalse(airconditioner.isOn());

        assertEquals(0, fan.getSpeed());
        assertEquals(AirConditioner.Mode.OFF, airconditioner.getMode());
    }   
    
 @Test
    public void testNextUpdateCalculation() {
        SmartApplianceController controller = new SmartApplianceController();
        YearlyUpdateService service = new YearlyUpdateService(controller);

        ZonedDateTime beforeJan1 = ZonedDateTime.of(2024, 12, 31, 23, 0, 0, 0, ZoneId.systemDefault());
        ZonedDateTime nextUpdate = service.nextUpdateDelay(beforeJan1);

        assertEquals(2025, nextUpdate.getYear());
        assertEquals(1, nextUpdate.getMonthValue());
        assertEquals(1, nextUpdate.getDayOfMonth());
        assertEquals(1, nextUpdate.getHour());
    }



    

    
}

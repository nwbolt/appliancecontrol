package com.interview.controller;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import com.interview.model.Appliance;

public class SmartApplianceController {

    private final Map<Integer, Appliance> appliancesMap = new ConcurrentHashMap<>();

    /**
     * Adds a new appliance to the controller.
     * @param appliance appliance to add
     * @throws IllegalArgumentException if addded appliance is null
     */
    public void addAppliance(Appliance appliance) {
        if(appliance == null) {
            throw new IllegalArgumentException("addAppliancee: Appliance cannot be null");
        }
        appliancesMap.put(appliance.getId(), appliance);
    }

    /**
     * Returns an appliance by its ID.
     * @param id appliance ID
     * @return Appliance with given ID
     * @throws NoSuchElementException if there to appliance with that ID
     */
    public Appliance getApplianceById(int id) {
        Appliance appliance = appliancesMap.get(id); 
        if (appliance == null) {
            throw new NoSuchElementException("No appliance with ID " + id);
        }
        return appliance;

    }

    //Turns off all registered appliances.
    public void turnAllOff() {
        for (Appliance appliance : appliancesMap.values()) {
            appliance.turnOff();
        }
    }

    /**
     * Displays the current state that all registed applances are in. 
     * @return String report
     */
    public String currentstateReport() {
        StringBuilder report = new StringBuilder();
        report.append("------------ Smart Home Dashboard ------------ \n");

        if(appliancesMap.isEmpty()) {
            report.append("No appliances registered.\n");
        } else {
            for (Appliance appliance : appliancesMap.values()) {
                report.append(appliance.currentState()).append("\n");
            }
        }
        report.append("-----------------------------------------------\n");
        return report.toString();
    }  
}

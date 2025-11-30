package com.interview.model;

public class Light implements  Appliance {
    private final int id;
    private final String name;
    private boolean isOn;

    public Light(int id, String name) {
        this.id = id;
        this.name = name;
        this.isOn = false;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void turnOn() {
        isOn = true;
    }

    @Override
    public void turnOff() {
        isOn = false;
    }

    @Override
    public String currentState() {
        return isOn ? name + " is ON" : name + " is OFF";
    }
    
    
}

package com.interview.model;

public class AirConditioner implements Appliance {
    public enum Mode {
        OFF,
        COOLING
    }
    
    private final int id;
    private final String name;
    private Mode mode;

    public AirConditioner(int id, String name) {
        this.id = id;
        this.name = name;
        this.mode = Mode.OFF;    //default mode is off
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public boolean isOn() { 
        return mode != Mode.OFF;
    }

    @Override
    public void turnOn() {
        setMode(Mode.COOLING);
    }

    @Override
    public void turnOff() {
        setMode(Mode.OFF);
    }

    @Override
    public String currentState() {
        if (mode == Mode.OFF) {
            return name + " is OFF";
        } else {
            return name + " is ON in " + mode + " mode";
        }
    }   
}

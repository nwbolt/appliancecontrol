package com.interview.model;

public class Fan implements Appliance {
    private final int id;
    private final String name;
    private int speed;

    public Fan(int id, String name) {
        this.id = id;
        this.name = name;
        this.speed = 0;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
         if (speed < 0 || speed > 2) 
        {
            throw new IllegalArgumentException("Invalid Digit. Fan speed must be set between 0 and 2.");
        }
        this.speed = speed;

    }

    @Override
    public boolean isOn() { 
        return speed > 0;  // if fan speed is bigger than 0 the fan is ON
    }

    @Override
    public void turnOn() { 
        setSpeed(1); 
    }

    @Override
    public void turnOff() {
         setSpeed(0); 
        }


    @Override
    public String currentState() {
        if (speed == 0) {
            return name + " is OFF";
        } else {
            return name + " is ON at speed " + speed;
        }
    }
    
}

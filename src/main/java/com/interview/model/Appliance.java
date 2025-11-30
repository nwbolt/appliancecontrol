package com.interview.model;

public interface Appliance {

    int getId();
    String getName();
    boolean isOn();

    void turnOn();

    void turnOff();

    // returns current state of appliance depending on appliance implementation
    String currentState();
    
}

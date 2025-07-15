package com.tss.behavioural.state.model;

public class RedLightState implements TrafficLightState {

	@Override
    public void handleRequest(TrafficLight trafficLight) {
        System.out.println("Traffic Light is RED: Cars must stop.");
        trafficLight.setState(new GreenLightState()); // Transition to Green
    }

}

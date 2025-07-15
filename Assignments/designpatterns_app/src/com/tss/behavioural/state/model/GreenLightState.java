package com.tss.behavioural.state.model;

public class GreenLightState implements TrafficLightState {

	@Override
    public void handleRequest(TrafficLight trafficLight) {
        System.out.println("Traffic Light is GREEN: Cars can go.");
        trafficLight.setState(new YellowLightState()); // Transition to Yellow
    }

}

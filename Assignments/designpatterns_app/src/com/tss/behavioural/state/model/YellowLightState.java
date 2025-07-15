package com.tss.behavioural.state.model;

public class YellowLightState implements TrafficLightState {

	@Override
	public void handleRequest(TrafficLight trafficLight) {
		System.out.println("Traffic Light is YELLOW: Prepare to stop.");
		trafficLight.setState(new RedLightState()); // Transition to Red
	}
}

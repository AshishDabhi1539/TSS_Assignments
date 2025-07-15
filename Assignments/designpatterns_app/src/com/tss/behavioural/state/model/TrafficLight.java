package com.tss.behavioural.state.model;

public class TrafficLight {
	private TrafficLightState currentState;

    public TrafficLight() {
        this.currentState = new RedLightState(); // Initial state
    }

    public void setState(TrafficLightState state) {
        this.currentState = state;
    }

    public void changeLight() {
        currentState.handleRequest(this);
    }
}

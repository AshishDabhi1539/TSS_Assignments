package com.tss.behavioural.state.test;

import com.tss.behavioural.state.model.TrafficLight;

public class TrafficLightTest {
	public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        // Simulate the traffic light changing through several cycles
        for (int i = 0; i < 6; i++) {
            trafficLight.changeLight();

            // Adding delay to simulate real-world timing (optional)
            try {
                Thread.sleep(2000); // 1 second delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

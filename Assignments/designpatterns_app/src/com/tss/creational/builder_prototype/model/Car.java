package com.tss.creational.builder_prototype.model;

public class Car {
	private String engine;
	private int wheels;
	private String color;

	private Car(CarBuilder builder) {
		super();
		this.engine = builder.engine;
		this.wheels = builder.wheels;
		this.color = builder.color;
	}

	@Override
	public String toString() {
		return "Car [Engine=" + engine + ", Wheels=" + wheels + ", Color=" + color + "]";
	}

	public static class CarBuilder {
		private String engine;
		private int wheels;
		private String color;

		public String getEngine() {
			return engine;
		}

		public CarBuilder setEngine(String engine) {
			this.engine = engine;
			return this;
		}

		public int getWheels() {
			return wheels;
		}

		public CarBuilder setWheels(int wheels) {
			this.wheels = wheels;
			return this;
		}

		public String getColor() {
			return color;
		}

		public CarBuilder setColor(String color) {
			this.color = color;
			return this;
		}

		public Car build() {
			return new Car(this);
		}

	}

}

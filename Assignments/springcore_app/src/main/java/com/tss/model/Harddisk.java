package com.tss.model;

public class Harddisk {
    private int capacity;

    public Harddisk() {
        super();
    }

    public Harddisk(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Harddisk [capacity=" + capacity + "GB]";
    }
}

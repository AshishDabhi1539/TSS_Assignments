package com.tss.exception.model;

import com.tss.exception.InsufficientWaterException;
import com.tss.exception.OverfillException;

public class WaterDispenser {
	private int maxCapacity;
    private int currentLevel = 0;
    
    public void fillWater(int fillAmount, int maxCapacity) throws OverfillException{
        if(currentLevel + fillAmount > maxCapacity){
            throw new OverfillException("Overfilling. Max capacity is " + maxCapacity + " liters.");
        }
        currentLevel += fillAmount;
        System.out.println(fillAmount + " liters filled successfully.");
    }
    
    public void dispenseWater(int dispenserAmount, int maxCapacity) throws InsufficientWaterException{
        if(dispenserAmount > currentLevel){
            throw new InsufficientWaterException("Not enough water to dispense " + dispenserAmount + " liters.");
        }
        currentLevel -= dispenserAmount;
        System.out.println(dispenserAmount + " liters dispensed successfully.");
    }
    
    public int getCurrentLevel(){
        return currentLevel;
    }
}

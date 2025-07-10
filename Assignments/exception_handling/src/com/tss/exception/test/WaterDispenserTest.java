package com.tss.exception.test;

import java.util.Scanner;

import com.tss.exception.model.WaterDispenser;

public class WaterDispenserTest {

	public static void main(String[] args) {
        WaterDispenser dispenser = new WaterDispenser();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Maximum Capacity for fill : ");
        final int maxCapacity = scanner.nextInt();
        
        try (scanner) {
        while(true){
            System.out.println("\n--WaterMenu--");
            System.out.println("1. Fill Water");
            System.out.println("2. Dispense Water");
            System.out.println("3. Exit");
            System.out.print("Enter your choice : ");
            
            int choice = scanner.nextInt();
            
            
                switch(choice){
                    case 1: 
                        System.out.print("Enter liters to fill : ");
                        int fillAmount = scanner.nextInt();
                        dispenser.fillWater(fillAmount,maxCapacity);
                        break;
                        
                    case 2: 
                        System.out.print("Enter liters to dispenser : ");
                        int dispenserAmount = scanner.nextInt();
                        dispenser.dispenseWater(dispenserAmount,maxCapacity);
                        break;
                    
                    case 3: 
                        System.out.print("Exiting...");
                        System.exit(0);
                        
                    default:
                        System.out.println("Invalid Choice.");
                }
            }
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }finally{
            System.out.println("Current water level : " + dispenser.getCurrentLevel() + " liters.");
        }
    }

}

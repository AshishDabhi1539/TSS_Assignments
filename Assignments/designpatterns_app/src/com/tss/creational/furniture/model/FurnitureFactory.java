package com.tss.creational.furniture.model;

public class FurnitureFactory {
	public static IFurniture getFurniture(String style, String type) {

        if (style.equalsIgnoreCase("Modern")) {
            if (type.equalsIgnoreCase("Chair"))
            	return new ModernChair();
            
            if (type.equalsIgnoreCase("Sofa")) 
            	return new ModernSofa();
            
            if (type.equalsIgnoreCase("Table")) 
            	return new ModernTable();
        } 
        else if (style.equalsIgnoreCase("Victorian")) {
            if (type.equalsIgnoreCase("Chair")) 
            	return new VictorianChair();
            
            if (type.equalsIgnoreCase("Sofa")) 
            	return new VictorianSofa();
            
            if (type.equalsIgnoreCase("Table")) 
            	return new VictorianTable();
        }

        return null;
	}
}

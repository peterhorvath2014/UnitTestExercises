package com.epam.torpedo.field.ship;

import java.util.ArrayList;

import com.epam.torpedo.file.FileHandler;

public class ShipFactory {
	
	public void createShipsFromFile() {
		FileHandler fileHandler = new FileHandler();
		ArrayList<String> fileLines = fileHandler.retrieveFileLines();
		for (String fileLine: fileLines) {
			System.out.println(fileLine);
			if (fileLine.matches("^\\d+")) {
				
			} else {
				
			}
		}
	}
	
	

	/*private ArrayList<Ship> multiplyShips(StringShip stringShip, Integer numberOfShipsNeed) {
		ArrayList<Ship> generatedShips = new ArrayList<>();
		for (int i = 0; i < numberOfShipsNeed; i++) {
			generatedShips.add(new StringShip(stringShip));
		}
		return generatedShips;
	}*/

}

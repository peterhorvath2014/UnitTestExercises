package com.epam.torpedo.field.ship;

import java.util.ArrayList;

import com.epam.torpedo.file.FileHandler;

public class ShipFactory {
	
	public void createShipsFromFile() {
		FileHandler fileHandler = new FileHandler();
		ArrayList<String> fileLines = fileHandler.retrieveFileLines();
		Ship ship = new Ship();
		for (String line: fileLines) {
			System.out.println(line);
			if (line.matches("^\\d+")) {
				
			} else {
				ship.addLine(line.trim().split(" "));
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

package com.epam.torpedo.field.ship;

import java.util.ArrayList;
import java.util.List;

import com.epam.torpedo.field.AbstractField;
import com.epam.torpedo.file.FileHandler;

public class ShipFactory {
	
	public void createShipsFromFile() {
		FileHandler fileHandler = new FileHandler();
		ArrayList<String> fileLines = fileHandler.retrieveFileLines();
		List<Ship> ships = new ArrayList<Ship>();
		AbstractField ship = new Ship();
		for (String line: fileLines) {
			if (line.matches("^\\d+")) {
				for (int i = 0; i < Integer.valueOf(line); i++) {
					ships.add(ship.generateDeniedFields());
				}
				ship = new Ship();
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

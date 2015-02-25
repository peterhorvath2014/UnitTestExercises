package com.epam.torpedo.ship;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;

public class ShipFactory {

	public Ship createOneShip() {
		OneShip ship = new OneShip();
		ship.fillShip();
		return ship;
	}

	public Ship createTwoShip() {
		TwoShip ship = new TwoShip();
		ship.fillShip();
		return ship;
	}

	public Ship createThreeShip() {
		ThreeShip ship = new ThreeShip();
		ship.fillShip();
		return ship;
	}

	public Ship createFourShip() {
		FourShip ship = new FourShip();
		ship.fillShip();
		return ship;
	}

	public Ship createTetrisShip() {
		TetrisShip ship = new TetrisShip();
		ship.fillShip();
		return ship;
	}

	public ArrayList<Ship> createShipsFromFile(String fileName) {
		ArrayList<Ship> ships = new ArrayList<>();

		try (InputStream fis = new FileInputStream(fileName);
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			ships = generateShipsFromFile(br);
		} catch (IOException e) {
			System.out.println("Exception during read in ship lines from file. Filename: " + fileName + "\n" + e.getStackTrace());
		}

		return ships;

	}

	private ArrayList<Ship> generateShipsFromFile(BufferedReader br) throws IOException {
		ArrayList<Ship> ships = new ArrayList<>();
		String line = "";
		String shipLines = "";
		while ((line = br.readLine()) != null) {
			if (line.matches("^d")) {
				// TODO nem lép be ide valamiért -y nem találja meg meddig kellene olvasnia egy hajót
				System.out.println("**********read digit");
				StringShip stringShip = new StringShip();
				stringShip.fillShip(shipLines);
				System.out.println(shipLines);
				shipLines = ""; // clear "used" pattern
				Integer numberOfShipsNeed = Integer.valueOf(line);
				ships.addAll(multiplyShips(stringShip, numberOfShipsNeed));
				
			} else {
				System.out.println("**********read normal line");
				
				shipLines += line + "\n";
			}

		}

		return ships;
	}

	private ArrayList<Ship> multiplyShips(StringShip stringShip, Integer numberOfShipsNeed) {
		ArrayList<Ship> generatedShips = new ArrayList<>();
		for (int i = 0; i < numberOfShipsNeed; i++) {
			generatedShips.add(new StringShip(stringShip));
		}
		return generatedShips;
	}
	
	
	

}

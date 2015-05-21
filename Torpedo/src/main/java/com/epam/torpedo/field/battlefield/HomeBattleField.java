package com.epam.torpedo.field.battlefield;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.RealField;
import com.epam.torpedo.field.ship.Ship;
import com.epam.torpedo.field.ship.ShipFactory;

public class HomeBattleField extends RealField implements AttackHistoryHolder {
  private static final Logger logger = LogManager.getLogger();
  private NavigableMap<Coordinate, Cell> attackHistory = new TreeMap<Coordinate, Cell>();

  public HomeBattleField(GameConfiguration gameConfiguration) {
    super(gameConfiguration);
    fillFieldWithShipsFromFileOnRandomPosition();
  }

  private void fillFieldWithShipsFromFileOnRandomPosition() {
    List<Ship> ships = ShipFactory.createShipsFromFile();
    // TODO check if ships fit to battleField
    // TODO make it random
    Coordinate coordinate = new Coordinate(0, 0);
    for (Ship ship : ships) {
      addFieldToPosition(ship.getField(), coordinate);
      int newX = coordinate.getX() + 5;
      int newY = coordinate.getY();
      if (newX > 5) {
        newX = 0;
        newY = 5;
      }
      coordinate = new Coordinate(newY, newX);
    }
  }

  @Override
  public NavigableMap<Coordinate, Cell> getAttackHistory() {
    return attackHistory;
  }

  @Override
  public void addAttackHistory(Coordinate coordinate, Cell cell) {
    attackHistory.put(coordinate, cell);
  }

  @Override
  protected Cell getDefaultFillingType() {
    return Cell.EMPTY;
  }

  public Cell checkFire(Coordinate coordinate) {
    Cell cell = getCell(coordinate);
    Cell result = Cell.MISSED;
    if (isShipPart(coordinate)) {
      result = Cell.HIT;
    }
    setCell(coordinate, cell);
    if (isSunk(coordinate)) {
      changeAllConnectedHitToSunk(coordinate);
      result = Cell.SUNK;
    }
    return result;
  }

  private boolean isSunk(Coordinate coordinate) {
    // TODO continue from here...
    // recursive call to all surrounding cell which has HIT on it

    for (int i = coordinate.getY() - 1; i <= coordinate.getY() + 1; i++) {
      for (int j = coordinate.getX() - 1; j <= coordinate.getX() + 1; j++) {
        Coordinate connectedCoordinate = new Coordinate(i, j);
        if (!isCoordinateOutOfBounds(connectedCoordinate)) {
          if (isHit(connectedCoordinate)) {
            isSunk(connectedCoordinate);
          }
        }
      }
    }
    return false;
  }

  public boolean isEveryShipSunk() {
    // TODO Auto-generated method stub
    return false;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    for (List<Cell> line : field) {
      for (Cell cell : line) {
        sb.append(cell.name().substring(0, 1) + " ");
      }
      sb.append("/n");
    }
    return sb.toString();
  }

  /*
   * public int getAttackHistoryLength() { // TODO Auto-generated method stub
   * return 0; }
   */

}

package com.epam.jdbcexercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

public class Lab1 {
	private static final String CONNECTION_URL = "jdbc:mysql://localhost/epam-jdbc";

	private static final String CONNECTION_USER = "epam";

	private static final String CONNECTION_PASSWORD = "jdbc";

	private Connection connection;

	private Statement statement;

	private ResultSet resultSet;

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
	}

	private void disconnect() {
		if (null != statement) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.err.println("Database error: " + e.getMessage());
			}
		}
		if (null != resultSet) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Database error: " + e.getMessage());
			}
		}
		if (null != resultSet) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println("Database error: " + e.getMessage());
			}
		}
	}

	private void run() {
		try {
			connect();

			dropTables();
			
			createTables();
			
			selectEmployees();

		} catch (ClassNotFoundException e) {
			System.err.println("Cannot load MySQL JDBC driver: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Database error: " + e.getMessage());
		} finally {
			disconnect();
		}
	}

	public void createTables() throws SQLException {
		String tableNameEmployee = "employee";
		createTable(tableNameEmployee);
		
		addNotNullColumn(tableNameEmployee, "first_name", "varchar", 50);
		addNotNullColumn(tableNameEmployee, "last_name", "varchar", 50);
		addNotNullColumn(tableNameEmployee, "gender", "varchar", 1);
		addNotNullColumn(tableNameEmployee, "position", "varchar", 50);
		addNotNullColumn(tableNameEmployee, "year_of_employment", "int", 11);
		
		insertValuesEmployee();
		
		
		String tableNamePosition = "employee_position";
		createTable(tableNamePosition);
		
		addNotNullColumn(tableNamePosition, "position_name", "varchar", 50);
		addNotNullColumn(tableNamePosition, "monthly_salary", "int", 11);
		
		insertValuesPosition();
	}

	public void dropTables() throws SQLException {
		executeStatement("DROP TABLE `epam-jdbc`.`employee`");
		executeStatement("DROP TABLE `epam-jdbc`.`employee_position`");
	}

	public void selectEmployees() throws SQLException {
		String selectSQL = "SELECT first_name, last_name, gender, position, year_of_employment, monthly_salary "
				+ "FROM employee "
				+ "INNER JOIN employee_position ON (employee.position = employee_position.position_name) "
				+ "WHERE monthly_salary > ? " 
				+ "ORDER BY monthly_salary DESC, last_name ASC";
		PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
		preparedStatement.setInt(1, 1000);
		
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			final StringBuilder builder = new StringBuilder();
			builder.append(resultSet.getString("first_name"));
			builder.append(" ");
			builder.append(resultSet.getString("last_name"));
			builder.append(" (");
			builder.append("M".equalsIgnoreCase(resultSet.getString("gender")) ? "male" : "female");
			builder.append("), ");
			builder.append(resultSet.getString("position"));
			builder.append(", salary: ");
			builder.append(resultSet.getString("monthly_salary"));
			builder.append(", applied since ");
			builder.append(resultSet.getInt("year_of_employment"));
			System.out.println(builder);
		}
	}

	private void insertValuesEmployee() throws SQLException {
		String sqlInsert = "INSERT INTO `employee` (first_name, last_name, gender, position, year_of_employment) VALUES ('Jacob','Smith','M','manager',2008),('Emily','Johnson','F','developer',2014),('Michael','Williams','M','developer',2003),('Hannah','Brown','F','developer',2014),('Matthew','Jones','M','assistant',2012),('Madison','Miller','F','assistant',2007),('Joshua','Davis','M','manager',2013),('Ashley','Garcia','F','developer',1996),('Christopher','Rodriguez','M','developer',2005),('Sarah','Wilson','F','developer',1999),('Nicholas','Martinez','M','assistant',2006),('Alexis','Anderson','F','assistant',1999),('Andrew','Taylor','M','manager',2001),('Samantha','Thomas','F','developer',2006),('Joseph','Hernandez','M','developer',2013),('Jessica','Moore','F','developer',2001),('Daniel','Martin','M','assistant',1996),('Elizabeth','Jackson','F','assistant',2002),('Tyler','Thompson','M','manager',2003),('Taylor','White','F','developer',1999),('William','Lopez','M','developer',1998),('Lauren','Lee','F','developer',2011),('Brandon','Gonzalez','M','assistant',2005),('Alyssa','Harris','F','assistant',2003),('Ryan','Clark','M','manager',1999),('Kayla','Lewis','F','developer',2006),('John','Robinson','M','developer',1997),('Abigail','Walker','F','developer',2006),('Zachary','Perez','M','assistant',2013),('Brianna','Hall','F','assistant',2007),('David','Young','M','manager',1997),('Olivia','Allen','F','developer',1999),('Anthony','Sanchez','M','developer',1998),('Emma','Wright','F','developer',2002),('James','King','M','assistant',2013),('Megan','Scott','F','assistant',2010),('Justin','Green','M','manager',2010),('Grace','Baker','F','developer',1997),('Alexander','Adams','M','developer',2013),('Victoria','Nelson','F','developer',2011),('Jonathan','Hill','M','assistant',1997),('Rachel','Ramirez','F','assistant',2008),('Christian','Campbell','M','manager',1996),('Anna','Mitchell','F','developer',2004),('Austin','Roberts','M','developer',2006),('Sydney','Carter','F','developer',2004),('Dylan','Phillips','M','assistant',2014),('Destiny','Evans','F','assistant',2011),('Ethan','Turner','M','manager',2011),('Morgan','Torres','F','developer',1995),('Benjamin','Parker','M','developer',2001),('Jennifer','Collins','F','developer',2005),('Noah','Edwards','M','assistant',2004),('Jasmine','Stewart','F','assistant',2011),('Samuel','Flores','M','manager',2007),('Haley','Morris','F','developer',2010),('Robert','Nguyen','M','developer',2004),('Julia','Murphy','F','developer',2013),('Nathan','Rivera','M','assistant',2001),('Kaitlyn','Cook','F','assistant',2009),('Cameron','Rogers','M','manager',2014),('Nicole','Morgan','F','developer',2002),('Kevin','Peterson','M','developer',2009),('Amanda','Cooper','F','developer',2011),('Thomas','Reed','M','assistant',2009),('Katherine','Bailey','F','assistant',2004),('Jose','Bell','M','manager',2008),('Natalie','Gomez','F','developer',2013),('Hunter','Kelly','M','developer',2000),('Hailey','Howard','F','developer',2000),('Jordan','Ward','M','assistant',2003),('Alexandra','Cox','F','assistant',2000),('Kyle','Diaz','M','manager',2000),('Savannah','Richardson','F','developer',2014),('Caleb','Wood','M','developer',2006),('Chloe','Watson','F','developer',2005),('Jason','Brooks','M','assistant',1998),('Rebecca','Bennett','F','assistant',2007),('Logan','Gray','M','manager',2003),('Stephanie','James','F','developer',2007),('Aaron','Reyes','M','developer',2004),('Maria','Cruz','F','developer',1996),('Eric','Hughes','M','assistant',1997),('Sophia','Price','F','assistant',1999),('Brian','Myers','M','manager',1996),('Mackenzie','Long','F','developer',2014),('Gabriel','Foster','M','developer',1996),('Allison','Sanders','F','developer',2000),('Adam','Ross','M','assistant',2005),('Isabella','Morales','F','assistant',2010),('Jack','Powell','M','manager',2011),('Amber','Sullivan','F','developer',2004),('Isaiah','Russell','M','developer',2012),('Mary','Ortiz','F','developer',1996),('Juan','Jenkins','M','assistant',2010),('Danielle','Gutierrez','F','assistant',1999),('Luis','Perry','M','manager',2001),('Gabrielle','Butler','F','developer',1998),('Connor','Barnes','M','developer',2012),('Jordan','Fisher','F','developer',2010)";
		executeStatement(sqlInsert);
	}
	
	private void insertValuesPosition() throws SQLException {
		String sqlInsert = "INSERT INTO `employee_position` (position_name, monthly_salary) VALUES ('manager',3000),('developer',1500),('assistant',800)";
		executeStatement(sqlInsert);
	}

	private void addNotNullColumn(String tableName, String columnName, String sqlType, int length) throws SQLException {
		String sqlCreate = "ALTER TABLE " + tableName + " ADD " + columnName + " " + sqlType + "(" + length + ") NOT NULL";
		executeStatement(sqlCreate);
	}

	public void executeStatement(String sqlCreate) throws SQLException {
		Statement stmt = connection.createStatement();
	    stmt.execute(sqlCreate);
	}

	private void createTable(String tableName) throws SQLException {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS " + tableName + " (id int(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY (id)) ";
		executeStatement(sqlCreate);
	}
	
	
	public static void main(String[] args) {
		new Lab1().run();
	}

}

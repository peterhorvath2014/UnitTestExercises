package com.epam.torpedo.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileHandler {
  private static final Logger logger = LogManager.getLogger();
	private static final String DEFAULT_FILE_NAME = "src/main/resources/ships.txt";

	public ArrayList<String> retrieveFileLines() {
		ArrayList<String> lines = new ArrayList<String>();

		try (InputStream fis = new FileInputStream(DEFAULT_FILE_NAME);
				InputStreamReader inputStreamReader = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
		  logger.error("Exception during read from file. Filename: " + DEFAULT_FILE_NAME + "\n"
					+ e.getLocalizedMessage());
		}
		return lines;
	}
}

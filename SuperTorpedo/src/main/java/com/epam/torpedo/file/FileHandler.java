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
	private static final String DEFAULT_FILE_NAME = "/ships.txt";

	public ArrayList<String> retrieveFileLines(String filePath) {
		logger.debug("filePath: " + filePath);
		ArrayList<String> lines = new ArrayList<String>();
		String usedFilePath = !filePath.equals("") ? filePath : DEFAULT_FILE_NAME;
		logger.debug("usedFilePath: " + usedFilePath);
		try (InputStream fis = new FileInputStream(usedFilePath);
				InputStreamReader inputStreamReader = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			String errorMessage = "Exception during read from file. Filename: " + usedFilePath + "\n"
					+ e.getLocalizedMessage();
			logger.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
		return lines;
	}
}

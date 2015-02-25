package com.epam.torpedo.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class FileHandler {
	private static final String DEFAULT_FILE_NAME = "ships.txt";

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
			System.out.println("Exception during read from file. Filename: " + DEFAULT_FILE_NAME + "\n"
					+ e.getStackTrace());
		}
		return lines;
	}
}

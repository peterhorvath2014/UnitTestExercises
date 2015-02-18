package com.epam.exercise1.repositroy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.epam.exercise1.entity.Examination;

public class ExaminationRepository {

	static final String CSV_FILE_NAME = "examinations.csv";
	private Map<Integer, Examination> examinations = new HashMap<>();

	public ExaminationRepository() {
		try (BufferedReader br = new BufferedReader(new FileReader(
				CSV_FILE_NAME))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");
				Examination examination = parseExaminationFromCsvParts(parts);
				examinations.put(examination.getId(), examination);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Examination parseExaminationFromCsvParts(String[] parts) {
		Examination examination = new Examination();
		examination.setId(Integer.parseInt(parts[0]));
		examination.setExaminerId(Integer.parseInt(parts[1]));
		examination.setExamDate(new Date(Long.parseLong(parts[2])));
		return examination;
	}

	public void create(Examination examination) {
		if (examination == null) {
			throw new IllegalArgumentException(
					"Parameter \"examination\" cannot be null.");
		}
		if (examination.getId() == null) {
			throw new IllegalArgumentException(
					"Id of parameter \"examination\" cannot be null.");
		}
		if (examination.getExaminerId() == null) {
			throw new IllegalArgumentException(
					"ExaminerId of parameter \"examination\" cannot be null.");
		}
		if (examination.getExamDate() == null) {
			throw new IllegalArgumentException(
					"ExamDateId of parameter \"examination\" cannot be null.");
		}

		if (examinations.containsKey(examination.getId())) {
			throw new IllegalArgumentException(
					"Id of parameter \"examination\" already exists in repository.");
		}
		this.examinations.put(examination.getId(), examination);
		saveAllToCsv();
	}

	private void saveAllToCsv() {
		try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE_NAME))) {
			for (Examination exam : retrieveAll()) {
				saveExaminationToCsv(exam, pw);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveExaminationToCsv(Examination examination, PrintWriter pw) {
		pw.print(examination.getId());
		pw.print(";");
		pw.print(examination.getExaminerId());
		pw.print(";");
		pw.print(examination.getExamDate().getTime());
		pw.println();
	}

	public Examination retrieveById(Integer i) {
		return this.examinations.get(i);
	}

	public Collection<Examination> retrieveAll() {
		return examinations.values();
	}

}

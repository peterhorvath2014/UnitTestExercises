package com.epam.exercise1.repositroy;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.exercise1.entity.Examination;

public class ExaminationRepositoryTest {
	private static final int EXAMINER_ID = 1;
	private ExaminationRepository repository;
	private final int EXAMINATION_ID = 1;

	@BeforeMethod
	public void init() {
		File csvFile = new File(ExaminationRepository.CSV_FILE_NAME);

		if (csvFile.isFile()) {
			csvFile.delete();
		}

		repository = new ExaminationRepository();
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCreateWhenNullThenThrowsException() {
		repository.create(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCreateWhenNullIdThenThrowsException() {
		Examination examination = new Examination();
		examination.setId(null);

		repository.create(examination);
	}

	@Test
	public void testCreateWhenValidThenStores() {
		Examination examination = new Examination();

		examination.setId(EXAMINATION_ID);
		examination.setExaminerId(EXAMINER_ID);
		examination.setExamDate(new Date());

		repository.create(examination);
		Examination result = repository.retrieveById(EXAMINATION_ID);
		Assert.assertEquals(result.getId(), examination.getId());
		Assert.assertEquals(result.getExamDate(), examination.getExamDate());
		Assert.assertEquals(result.getExaminerId(), examination.getExaminerId());
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCreateWithSameIdsThenThrowsException() {
		Examination examination = new Examination();

		examination.setId(EXAMINATION_ID);
		examination.setExaminerId(EXAMINER_ID);
		examination.setExamDate(new Date());

		repository.create(examination);
		repository.create(examination);
	}

	@Test
	public void testRetrieveByIdWhenRepositoryIsEmptyThenNull() {
		Assert.assertNull(repository.retrieveById(EXAMINATION_ID));
	}

	@Test
	public void testRetrieveAllWhenEmptyThenEmptyCollection() {
		Collection<Examination> examinations = repository.retrieveAll();
		Assert.assertTrue(examinations.isEmpty());
	}

	@Test
	public void testRetrieveAllWhenExaminationsCreatedThenAllRetrieved() {
		Examination examination = new Examination();
		examination.setId(EXAMINATION_ID);
		examination.setExaminerId(EXAMINER_ID);
		examination.setExamDate(new Date());

		repository.create(examination);
		Collection<Examination> examinations = repository.retrieveAll();

		Assert.assertEquals(examinations.size(), 1);
	}

	@Test
	public void testPersistingOnModification() {
		Examination examination = new Examination();
		examination.setId(EXAMINATION_ID);
		examination.setExaminerId(EXAMINER_ID);
		examination.setExamDate(new Date());
		repository.create(examination);

		Examination examination2 = new Examination();
		examination2.setId(EXAMINATION_ID + 1);
		examination2.setExaminerId(EXAMINER_ID);
		examination2.setExamDate(new Date());
		repository.create(examination2);

		repository = new ExaminationRepository();

		Collection<Examination> examinations = repository.retrieveAll();

		Assert.assertEquals(examinations.size(), 2);
	}

}

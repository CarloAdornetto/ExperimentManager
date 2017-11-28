package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.ExperimentTonesList;

public class ExperimentTonesListTest {

	ExperimentTonesList etl4Test;

	@Before
	public void setUp() throws Exception {
		this.etl4Test = new ExperimentTonesList();
	}

	@Test
	public void testExperimentTonesList() {
		assertNotNull(etl4Test.getTones());
		assertNotNull(etl4Test.getRecPercentages());
	}

	@Test
	public void testIsValidUniqueId() {
		String toneId = "t1";
		etl4Test.addNewTone(toneId, 0, 0, 0, 50);
		assertFalse(etl4Test.isValidUniqueId(toneId));
		assertFalse(etl4Test.isValidUniqueId(" "));
		assertTrue(etl4Test.isValidUniqueId("t2"));
	}

	@Test
	public void testIsValidPercentage() {
		assertFalse(etl4Test.isValidPercentage("101"));
		assertFalse(etl4Test.isValidPercentage("10.2"));
		assertTrue(etl4Test.isValidPercentage("100"));
	}

	@Test
	public void testIsValidPercentageSum() {
		assertFalse(etl4Test.isValidPercentageSum(101));
		assertFalse(etl4Test.isValidPercentageSum(50));
		assertTrue(etl4Test.isValidPercentageSum(100));
		assertTrue(etl4Test.isValidPercentageSum(0));
	}

	@Test
	public void testAddNewTone() {
		String toneId = "t1";
		etl4Test.addNewTone(toneId, 0, 0, 0, 50);
		assertTrue(etl4Test.getTones().containsKey(toneId));
		assertTrue(etl4Test.getRecPercentages().containsKey(toneId));
	}

	@Test
	public void testDeleteTone() {
		String toneId = "t1";
		etl4Test.addNewTone(toneId, 0, 0, 0, 50);
		assertTrue(etl4Test.getTones().containsKey(toneId));
		assertTrue(etl4Test.getRecPercentages().containsKey(toneId));
		etl4Test.deleteTone(toneId);
		assertFalse(etl4Test.getTones().containsKey(toneId));
		assertFalse(etl4Test.getRecPercentages().containsKey(toneId));
	}

	@Test
	public void testGetTones() {
		assertNotNull(etl4Test.getTones());		
	}

	@Test
	public void testGetPercentages() {
		assertNotNull(etl4Test.getRecPercentages());
	}

	@Test
	public void testGetToneSamplesById() {
		String toneId = "t1";
		etl4Test.addNewTone(toneId, 0, 0, 0, 50);
		assertSame(etl4Test.getTones().get(toneId).getSamples(), etl4Test.getToneSamplesById(toneId));
	}

	@Test
	public void testGetPercentageSum() {
		int p1 = 50;
		int p2 = 20;
		etl4Test.addNewTone("t1", 0, 0, 0, p1);
		etl4Test.addNewTone("t2", 0, 0, 0, p2);

		assertEquals(p1+p2, etl4Test.getPercentageSum());
	}

	@Test
	public void testGetMaxToneDuration() {
		double maxDur = 5.0;
		etl4Test.addNewTone("t1", 0, 2.2, 0, 30);
		etl4Test.addNewTone("t2", 0, 1.9, 0, 60);
		etl4Test.addNewTone("t1", 0, maxDur, 0, 10);
		assertEquals(maxDur, etl4Test.getMaxToneDuration(), 0.0);
	}

}

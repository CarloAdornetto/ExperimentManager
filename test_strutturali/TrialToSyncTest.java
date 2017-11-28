package test_strutturali;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import application.TrialToSync;

public class TrialToSyncTest {

	TrialToSync trialToSync;
	long instant4Test = 564783;
	String toneId4Test = "tts";

	@Before
	public void setUp() throws Exception {
		trialToSync = new TrialToSync(toneId4Test, instant4Test);
	}

	@Test
	public void testTrialToSync() {
		assertNotNull(trialToSync);
		assertEquals(toneId4Test, trialToSync.getToneId());
		assertEquals(instant4Test, trialToSync.getInstant());
	}

	@Test
	public void testGetInstant() {
		assertEquals(instant4Test, trialToSync.getInstant());
	}

}

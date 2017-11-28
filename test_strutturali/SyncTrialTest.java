package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.SyncTrial;

public class SyncTrialTest {

	SyncTrial syncTrial;
	long realInstant4Test = 65486;
	String toneId4Test = "st";

	@Before
	public void setUp() throws Exception {
		syncTrial = new SyncTrial(toneId4Test, realInstant4Test);
	}

	@Test
	public void testSyncTrial() {
		assertNotNull(syncTrial);
		assertEquals(toneId4Test, syncTrial.getToneId());
		assertEquals(realInstant4Test, syncTrial.getRealInstant());
	}

	@Test
	public void testGetRealInstant() {
		assertEquals(realInstant4Test, syncTrial.getRealInstant());
	}

}

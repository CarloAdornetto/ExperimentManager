package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.SyncResponse;

public class SyncResponseTest {

	SyncResponse syncResponse;
	long realInstant4Test =  102384;
	String toneId4Test = "sr";

	@Before
	public void setUp() throws Exception {
		syncResponse = new SyncResponse(toneId4Test, realInstant4Test);
	}

	@Test
	public void testSyncResponse() {
		assertNotNull(syncResponse);
		assertEquals(toneId4Test, syncResponse.getResponseId());
		assertEquals(realInstant4Test, syncResponse.getRealInstant());
	}

	@Test
	public void testGetRealInstant() {	
		assertEquals(realInstant4Test, syncResponse.getRealInstant());
	}

}

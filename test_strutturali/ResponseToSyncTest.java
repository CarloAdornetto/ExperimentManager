package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.ResponseToSync;

public class ResponseToSyncTest {

	ResponseToSync responseToSync;
	long instant4Test =  102384;
	String toneId4Test = "rts";

	@Before
	public void setUp() throws Exception {
		responseToSync = new ResponseToSync(toneId4Test, instant4Test);
	}

	@Test
	public void testResponseToSync() {
		assertNotNull(responseToSync);
		assertEquals(toneId4Test, responseToSync.getResponseId());
		assertEquals(instant4Test, responseToSync.getInstant());
	}

	@Test
	public void testGetInstant() {	
		assertEquals(instant4Test, responseToSync.getInstant());
	}

}

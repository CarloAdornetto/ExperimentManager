package test_strutturali;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import application.ResponseToSync;
import application.SyncResponse;
import application.SyncTrial;
import application.Synchronizer;
import application.TrialToSync;

public class SynchronizerTest {

	Synchronizer s;
	static final long firstVideoWrite4Test = 1000;
	ArrayList<TrialToSync> trialsToSync;
	ArrayList<ResponseToSync> responsesToSync;

	@Before
	public void setUp() throws Exception {
		s = new Synchronizer();
		s.setFirstVideoWrite(firstVideoWrite4Test);
		trialsToSync = new ArrayList<TrialToSync>(); 
		responsesToSync = new ArrayList<ResponseToSync>(); 
		for (int i = 0; i < 5; i++) {
			trialsToSync.add(new TrialToSync("tts"+i, firstVideoWrite4Test+i));
			responsesToSync.add(new ResponseToSync("rts"+i, firstVideoWrite4Test+i));
		}
	}

	@Test
	public void testSynchronizer() {
		assertNotNull(s);
	}

	@Test
	public void testSetFirstVideoWrite() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		s.setFirstVideoWrite(999);
		final Field field = s.getClass().getDeclaredField("firstVideoWrite");
		field.setAccessible(true);
		assertEquals("Fields didn't match", field.get(s), (long)999);
	}

	@Test
	public void testSynchronizeTrial() {
		ArrayList<SyncTrial> syncResult = s.synchronizeTrial(trialsToSync);
		assertEquals(trialsToSync.size(), syncResult.size());
		long diff;
		for (int i=0; i<5; i++) {
			diff =  trialsToSync.get(i).getInstant() - firstVideoWrite4Test;
			assertEquals(diff, syncResult.get(i).getRealInstant());
		}
	}

	@Test
	public void testSynchronizeResponse() {
		ArrayList<SyncResponse> syncResult = s.synchronizeResponse(responsesToSync);
		assertEquals(responsesToSync.size(), syncResult.size());
		long diff;
		for (int i=0; i<5; i++) {
			diff =  responsesToSync.get(i).getInstant() - firstVideoWrite4Test;
			assertEquals(diff, syncResult.get(i).getRealInstant());
		}
	}

}

package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.Trial;

public class TrialTest {

	Trial t;
	String id = "t1";

	@Before
	public void setUp() throws Exception {
		t = new Trial(id);
	}

	@Test
	public void testTrial() {
		assertNotNull(t);
	}

	@Test
	public void testGetToneId() {
		assertEquals(id, t.getToneId());
	}

}
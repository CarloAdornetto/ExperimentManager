package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.TrialToPlay;

public class TrialToPlayTest {

	TrialToPlay trialToPlay;
	long interval4Test =  102384;
	String toneId4Test = "ttp";

	@Before
	public void setUp() throws Exception {
		trialToPlay = new TrialToPlay(toneId4Test, interval4Test);
	}

	@Test
	public void testTrialToPlay() {
		assertNotNull(trialToPlay);
		assertEquals(toneId4Test, trialToPlay.getToneId());
		assertEquals(interval4Test, trialToPlay.getInterval());
	}

	@Test
	public void testGetInterval() {
		assertEquals(interval4Test, trialToPlay.getInterval());
	}

}

package test_strutturali;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import application.StdAudio;

public class StdAudioTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StdAudio.init();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		StdAudio.close();
	}

	@Test
	public void testCreateNote() {
		double[] note = StdAudio.createNote(440, 3, 0);
		assertNotNull(note);
	}

	@Test (expected = java.lang.IllegalArgumentException.class)
	public void testPlayExcep1() {
		StdAudio.play(null);	
	}

	@Test (expected = java.lang.IllegalArgumentException.class)
	public void testPlayExcep2() throws IllegalArgumentException {
		StdAudio.play(new double[] {2,-2, 0.0/0.0});	
	}

	@Test
	public void testPlay() {
		double[] note = StdAudio.createNote(440, 1, 0);
		long played = StdAudio.play(note);
		assertEquals(played, System.currentTimeMillis(), 1500);
	}

}

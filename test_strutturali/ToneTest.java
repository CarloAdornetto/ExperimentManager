package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.Tone;

public class ToneTest {

	Tone tone;

	@Before
	public void setUp() throws Exception {
		tone = new Tone("t1", 440.0, 1.0, 5);
	}

	@Test
	public void testTone() {
		assertNotNull(tone.getSamples());
	}

	@Test
	public void testIsValidId() {
		assertTrue(Tone.isValidId("toneId"));
		assertFalse(Tone.isValidId("tone Id"));
		assertFalse(Tone.isValidId(""));
	}

	@Test
	public void testIsValidFrequency() {
		assertTrue(Tone.isValidFrequency("440.0"));
		assertFalse(Tone.isValidFrequency("-400"));
	}

	@Test
	public void testIsValidDuration() {
		assertTrue(Tone.isValidDuration("1.5"));
		assertFalse(Tone.isValidDuration("-1.4"));
	}

	@Test
	public void testIsValidAmplitude() {
		assertTrue("", Tone.isValidAmplitude("5"));
		assertFalse("", Tone.isValidAmplitude("-1"));
		assertFalse("", Tone.isValidAmplitude("11"));
	}

	@Test
	public void testGetDuration() {
		assertEquals("", 1.0, tone.getDuration(), 0.0);
	}

	@Test
	public void testGetFrequency() {
		assertEquals("", 440.0, tone.getFrequency(), 0.0);
	}

	@Test
	public void testGetAmplitude() {
		assertEquals("", 5, tone.getAmplitude(), 0.0);
	}

	@Test
	public void testGetSamples() {
		assertNotNull(tone.getSamples());
	}

	@Test
	public void testGetId() {
		assertTrue("", tone.getId().equals("t1"));

	}

	@Test
	public void testGetToneInfo() {
		String info =  tone.getFrequency() + " Hz \t\t" 
				+ tone.getDuration() + " s \t\t" 
				+ tone.getAmplitude() + " vol";

		assertTrue("", tone.getToneInfo().equals(info));
	}

}

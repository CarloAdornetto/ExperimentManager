package test_strutturali;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.AudioTimer;
import application.ExperimentTonesList;
import application.StdAudio;
import application.TrialToPlay;
import application.TrialToSync;

public class AudioTimerTest {

	AudioTimer audioTimer4Test;
	ArrayList<TrialToPlay> experimentSetup4Test; 
	ExperimentTonesList experimentTonesList4Test;
	ArrayList<TrialToSync> trialsToSync4Test;    
	static final long interval4Test = 300;
	static final long tolerance = 50;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		StdAudio.init();
	}

	@Before
	public void setUp() throws Exception {
		experimentTonesList4Test = new ExperimentTonesList();
		experimentTonesList4Test.addNewTone("t1", 440, 0.1, 0, 100);

		experimentSetup4Test = new ArrayList<TrialToPlay>();
		for (int i = 0; i < 5; i++) {
			experimentSetup4Test.add(new TrialToPlay("t1", interval4Test));
		}

		trialsToSync4Test = new ArrayList<TrialToSync>();

		audioTimer4Test = new AudioTimer(experimentSetup4Test,
				experimentTonesList4Test,
				trialsToSync4Test);
	}

	//Depending on the system and his status, you may need to modify 'tolerance'
	@Test
	public void testAudioTimerRun() throws InterruptedException {

		audioTimer4Test.start();
		Thread.sleep(interval4Test*10);

		assertEquals(experimentSetup4Test.size(), trialsToSync4Test.size());

		long diff;
		for (int i = 1; i < trialsToSync4Test.size(); i++) {
			diff = trialsToSync4Test.get(i).getInstant() - trialsToSync4Test.get(i-1).getInstant()
					- interval4Test;
			//the first iteration of the AudioTimer run takes more time than the others
			if (i != 1) {  
				assertTrue(diff < tolerance);
			} 
		}
	}

	@Test
	public void testKill() throws InterruptedException {
		audioTimer4Test.start();
		Thread.sleep(interval4Test);
		audioTimer4Test.kill();
		Thread.sleep(interval4Test);
		assertFalse(audioTimer4Test.isAudioTimerIsRunning());
	}

}

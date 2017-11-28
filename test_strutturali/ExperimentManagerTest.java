package test_strutturali;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import application.ExperimentManager;
import application.ExperimentParameters;
import application.LibrariesLoader;
import application.StdAudio;
import application.StreamVideoManager;

@RunWith(Parameterized.class)
public class ExperimentManagerTest {

	ExperimentManager expManager4Test;
	ExperimentParameters expParam4Test;

	//Questa classe di test eseguirà una successione di esperimenti cartterizzati da parametri
	//scelti per garantire una copertura massima sulla classe ExperimentManager
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {     
			{ 0.6, 0.2, 4 }, { 0.6, 0.2, 6 }, { 0.6, 0.2, 10 }, { 0.1, 0.3, 4 }, { 0, 0, 4 } 
		});
	}

	public ExperimentManagerTest(double mean, double stdDev, int trialNum) {
		expManager4Test = new ExperimentManager();
		expParam4Test = new ExperimentParameters();

		expParam4Test.setMean(mean);
		expParam4Test.setStandardDeviation(stdDev);
		expParam4Test.setTrialNumber(trialNum);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LibrariesLoader.loadOpenCVdll(); 

		StreamVideoManager.startCamera(null);
		StdAudio.init();

		ExperimentManager.experimentTonesList.addNewTone("t1", 440, 0.15, 0, 30);
		ExperimentManager.experimentTonesList.addNewTone("t2", 440, 0.21, 0, 60);
		ExperimentManager.experimentTonesList.addNewTone("t3", 440, 0.15, 0, 10);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		StreamVideoManager.closeCamera();
		StdAudio.close();
	}

	@Test
	public void testStartAndStop() throws IOException, InterruptedException {
		//test also for the keepResponse method 
		expManager4Test.keepResponse("E");
		expManager4Test.start(expParam4Test);
		Thread.sleep(3000);
		expManager4Test.keepResponse("E");
		expManager4Test.stop();

		File dir = new File(expManager4Test.getExperimentResultDir());
		File video = new File(expManager4Test.getVideoDestinationFile());
		File report = new File(expManager4Test.getReportDestinationFile());
		File response = new File(expManager4Test.getResponseDestinationFile());

		assertTrue(dir.exists());
		assertTrue(video.exists());
		assertTrue(report.exists());
		assertTrue(response.exists());

		FileUtils.deleteDirectory(dir);
	}

}

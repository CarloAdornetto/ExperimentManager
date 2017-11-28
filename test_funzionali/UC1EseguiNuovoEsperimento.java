package test_funzionali;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.ExperimentManager;
import application.ExperimentParameters;
import application.LibrariesLoader;
import application.StdAudio;
import application.StreamVideoManager;

public class UC1EseguiNuovoEsperimento {

	//Ricordo le modifiche apportate a questo use cases: 
	//vedi "2 System Overview" del documento sdd_p6_adornetto_carlo.odt

	ExperimentManager expManager4Test;
	ExperimentParameters expParam4Test;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		LibrariesLoader.loadOpenCVdll(); 

		//To make preconditions hold
		StreamVideoManager.startCamera(null);
		StdAudio.init();
		ExperimentManager.experimentTonesList.addNewTone("t1", 440, 0.3, 0, 40);
		ExperimentManager.experimentTonesList.addNewTone("t2", 440, 0.3, 0, 60);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		StreamVideoManager.closeCamera();
		StdAudio.close();
	}

	@Before
	public void setUp() throws Exception {
		expManager4Test = new ExperimentManager();
		expParam4Test = new ExperimentParameters();

		//To make preconditions hold
		expParam4Test.setMean(1.0);
		expParam4Test.setStandardDeviation(0.5);
		expParam4Test.setTrialNumber(6);
	}

	//Scenario Principale : Il Ricercatore chiede al sistema di avviare l’Esperimento,
	//il sistema avvia l'esperimento e al termine di esso salva correttamente i risultati.
	@Test
	public void eseguiNuovoEsperimento() throws IOException, InterruptedException {
		expManager4Test.start(expParam4Test);
		Thread.sleep(1000);
		//see step 6
		expManager4Test.keepResponse("E");
		Thread.sleep(1000);

		//Scenario Alternativo : 5a. Il Ricercatore decide di interrompere l’Esperimento
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

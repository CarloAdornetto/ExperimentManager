package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.ExperimentManager;
import application.ExperimentParameters;

public class UC2ImpostaParametriEsperimento {

	ExperimentParameters expParam4Test;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//needed to verify the valid set condition
		ExperimentManager.experimentTonesList.addNewTone("t1", 440, 0.5, 0, 30);
		ExperimentManager.experimentTonesList.addNewTone("t2", 440, 0.5, 0, 60);
	}

	@Before
	public void setUp() throws Exception {
		expParam4Test = new ExperimentParameters();
	}

	//Scenario Principale : Il ricercatore inserisce i parametri esperimento
	//che vengono validati dal sistema
	@Test
	public void impostaParametriEsperimento() {
		String trialNum = "15";
		String mean = "4.5";
		String stdDev = "1.5";

		if (expParam4Test.isValidTrialNumber(trialNum) && expParam4Test.isValidMean(mean) 
				&& expParam4Test.isValidStdDev(stdDev) && expParam4Test.isValidParameterSet(mean, stdDev)) {

			expParam4Test.setTrialNumber(Integer.parseInt(trialNum));
			expParam4Test.setMean(Double.parseDouble(mean));
			expParam4Test.setStandardDeviation(Double.parseDouble(stdDev));
		}

		assertEquals(Integer.parseInt(trialNum), expParam4Test.getTrialNumber());
		assertEquals(Double.parseDouble(mean), expParam4Test.getMean(), 0.0);
		assertEquals(Double.parseDouble(stdDev), expParam4Test.getStandardDeviation(), 0.0);
	}

	//Scenario Alternativo : 3a. Il ricercatore decide di annullare l'operazione 
	@Test
	public void impostaParametriEsperimentoA1() {
		assertEquals(expParam4Test.getTrialNumber(), 0);
		assertEquals(expParam4Test.getMean(), 0.0, 0.0);          
		assertEquals(expParam4Test.getStandardDeviation(), 0.0, 0.0); 
	}

	//Scenario Alternativo : 4a. Il Sistema non valida i dati inseriti
	@Test
	public void impostaParametriEsperimentoA2() {
		String trialNum = "-1.5";
		String mean = "-2";
		String stdDev = "1.5";

		if (expParam4Test.isValidTrialNumber(trialNum) && expParam4Test.isValidMean(mean) 
				&& expParam4Test.isValidStdDev(stdDev) && expParam4Test.isValidParameterSet(mean, stdDev)) {

			expParam4Test.setTrialNumber(Integer.parseInt(trialNum));
			expParam4Test.setMean(Double.parseDouble(mean));
			expParam4Test.setStandardDeviation(Double.parseDouble(stdDev));
		}

		assertEquals(expParam4Test.getTrialNumber(), 0);
		assertEquals(expParam4Test.getMean(), 0.0, 0.0);          
		assertEquals(expParam4Test.getStandardDeviation(), 0.0, 0.0); 
	}

}

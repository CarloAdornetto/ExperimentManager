package test_funzionali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.ExperimentManager;
import application.ExperimentTonesList;
import application.Tone;

public class UC3AggiungiTono {

	ExperimentTonesList expTonesList = ExperimentManager.experimentTonesList;

	@Before
	public void setUp() throws Exception {
		expTonesList.getTones().clear();
		expTonesList.getRecPercentages().clear();
	}

	//Scenario Principale : Il Ricercatore riesce ad aggiungere con successo 
	//il nuovo Tono.
	@Test
	public void aggiungiTono() {
		String id = "tone";
		String freq = "440.0";
		String dur = "1.0";
		String vol = "5.0";
		String recPercentage = "50";

		if (Tone.isValidId(id) && expTonesList.isValidUniqueId(id) 
				&& Tone.isValidFrequency(freq)
				&& Tone.isValidDuration(dur) 
				&& Tone.isValidAmplitude(vol)
				&& expTonesList.isValidPercentage(recPercentage)) {

			expTonesList.addNewTone(id, Double.parseDouble(freq), 
					Double.parseDouble(dur), Double.parseDouble(vol), 
					Integer.parseInt(recPercentage));
		}

		assertNotNull(expTonesList.getTones().get(id).getSamples());
		assertEquals(Integer.parseInt(recPercentage), (int)expTonesList.getRecPercentages().get(id));
	}

	//Scenario Alternativo : 3a. Il ricercatore decide di annullare l'operazione. 
	@Test
	public void aggiungiTonoA1() {
		assertEquals(expTonesList.getTones().size(), 0);
		assertEquals(expTonesList.getRecPercentages().size(), 0);
	}

	//Scenario Alternativo : 4a. Il Sistema non valida i dati inseriti.
	@Test
	public void aggiungiTonoA2() {
		String id = "tone";
		String freq = "-440.0";
		String dur = "1.0";
		String vol = "5.0";
		String recPercentage = "150";

		if (Tone.isValidId(id) && expTonesList.isValidUniqueId(id) 
				&& Tone.isValidFrequency(freq)
				&& Tone.isValidDuration(dur) 
				&& Tone.isValidAmplitude(vol)
				&& expTonesList.isValidPercentage(recPercentage)) {

			expTonesList.addNewTone(id, Double.parseDouble(freq), 
					Double.parseDouble(dur), Double.parseDouble(vol), 
					Integer.parseInt(recPercentage));
		}

		assertFalse(expTonesList.getTones().containsKey(id));
	}
}
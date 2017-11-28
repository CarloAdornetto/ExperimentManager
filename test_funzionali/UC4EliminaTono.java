package test_funzionali;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.ExperimentManager;
import application.ExperimentTonesList;

public class UC4EliminaTono {

	ExperimentTonesList expTonesList = ExperimentManager.experimentTonesList;

	@Before
	public void setUp() throws Exception {
		expTonesList.addNewTone("t1", 440, 0.15, 0, 30);
		expTonesList.addNewTone("t2", 440, 0.21, 0, 60);
		expTonesList.addNewTone("t3", 440, 0.15, 0, 10);
	}

	@After
	public void tearDown() throws Exception {
		expTonesList.getTones().clear();
		expTonesList.getRecPercentages().clear();
	}

	//Scenario Principale : Il Ricercatore inserisce l'id del Tono da eliminare
	//ed il sistema rimuove il Tono
	@Test
	public void eliminaTono() {
		String id = "t1";

		if (expTonesList.getTones().containsKey(id)) {
			expTonesList.deleteTone(id);
		}

		assertFalse(expTonesList.getTones().containsKey(id));
	}

	//Scenario Alternativo : 2a La lista dei Toni è vuota
	@Test
	public void eliminaTonoA1() {
		expTonesList.getTones().clear();
		expTonesList.getRecPercentages().clear();

		assertTrue(expTonesList.getTones().isEmpty());
		assertTrue(expTonesList.getTones().isEmpty());
		assertTrue(expTonesList.getRecPercentages().isEmpty());
		assertTrue(expTonesList.getRecPercentages().isEmpty());
	}


	//Scenario Alternativo : 2a il Ricercatore decide di annullare l'operazione e 
	//la lista dei Toni Esperimento resta invariata
	@Test
	public void eliminaTonoA2() {
		expTonesList.getTones().containsKey("t1");
		expTonesList.getTones().containsKey("t2");
		expTonesList.getTones().containsKey("t3");
		expTonesList.getRecPercentages().containsKey("t1");
		expTonesList.getRecPercentages().containsKey("t2");
		expTonesList.getRecPercentages().containsKey("t3");
	}
}

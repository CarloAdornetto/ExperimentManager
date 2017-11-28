package test_funzionali;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.LibrariesLoader;
import application.StreamVideoManager;
import application.Utils;

public class UC5ModificaImpostazioniCamera {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LibrariesLoader.loadOpenCVdll();
		StreamVideoManager.startCamera(null);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		StreamVideoManager.closeCamera();
	}

	@Before
	public void setUp() throws Exception {
		StreamVideoManager.greyScale = false;
	}

	//Scenario Principale : Il Ricercatore effettua le modifiche (validate dal sistema) con successo.
	@Test
	public void modificaImpostazioniCamera() {
		//Modifiche
		String fps = "10";
		boolean greyScale = true;

		//il Ricercatore Conferma
		if (Utils.isPositiveInteger(fps,10) 
				&& (Integer.parseInt(fps,10) < 1000)
				&& (Integer.parseInt(fps,10) > 0)) {

			StreamVideoManager.closeCamera();
			StreamVideoManager.fps = Integer.parseInt(fps,10);
			StreamVideoManager.setMillisPerFrame(Integer.parseInt(fps));
			StreamVideoManager.greyScale = greyScale;
			StreamVideoManager.startCamera(null);

		}
		assertEquals(StreamVideoManager.fps, Integer.parseInt(fps,10));
		assertEquals(StreamVideoManager.greyScale, greyScale);
	}

	//Scenario Alternativo : 3a Il Ricercatore decide di annullare l'operazione. 
	//Le impostazioni restano invariate
	@Test
	public void modificaImpostazioniCameraA1() {
		//impostazioni attuali
		int fpsBefore = StreamVideoManager.fps;
		boolean greyScaleBefore = StreamVideoManager.greyScale;

		assertEquals(StreamVideoManager.fps, fpsBefore);
		assertEquals(StreamVideoManager.greyScale, greyScaleBefore);
	}

	//Scenario Alternativo : 6a Il sistema non valida le modifiche. le impostazioni
	//restano invariate
	@Test
	public void modificaImpostazioniCameraA2() {
		//impostazioni attuali
		int fpsBefore = StreamVideoManager.fps;
		boolean greyScaleBefore = StreamVideoManager.greyScale;

		//Modifiche
		String fps = "10001";
		boolean greyScale = true;


		//il Ricercatore Conferma
		if (Utils.isPositiveInteger(fps,10) 
				&& (Integer.parseInt(fps,10) < 1000)
				&& (Integer.parseInt(fps,10) > 0)) {

			StreamVideoManager.closeCamera();
			StreamVideoManager.fps = Integer.parseInt(fps,10);
			StreamVideoManager.setMillisPerFrame(Integer.parseInt(fps));
			StreamVideoManager.greyScale = greyScale;
			StreamVideoManager.startCamera(null);

		}

		assertNotEquals(StreamVideoManager.fps, Integer.parseInt(fps,10));
		assertNotEquals(StreamVideoManager.greyScale, greyScale);

		assertEquals(StreamVideoManager.fps, fpsBefore);
		assertEquals(StreamVideoManager.greyScale, greyScaleBefore);
	}

}

package test_strutturali;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import application.LibrariesLoader;
import application.StreamVideoManager;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StreamVideoManagerTest extends Application {

	//Metodo vuoto. Non verrà mostrato nessuno Stage durante i test.
	@Override
	public void start(Stage primaryStage) throws Exception {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LibrariesLoader.loadOpenCVdll();
		//ho bisogno di un thread JavaFX per rggiungere la massima copertura
		//sulla chiamata alla funzione OnFXThread().
		Thread t = new Thread("JavaFX Init Thread") {
			public void run() {
				try {
					Application.launch(StreamVideoManagerTest.class, new String[0]);
				} catch (Exception e)  {
				}
			}
		};
		t.start();
		Thread.sleep(7000);
	}

	@Before
	public void setUp() throws Exception {
		StreamVideoManager.closeCamera();
		StreamVideoManager.cameraId = 0;
		StreamVideoManager.cameraActive = false;
		StreamVideoManager.fps = 15;
		StreamVideoManager.greyScale = false;
		StreamVideoManager.rec = false;
		StreamVideoManager.isFirstWrite = true;
		StreamVideoManager.firstVideoWrite = 0;
	}

	@Test
	public void testStartCloseCamera1() throws InterruptedException {
		StreamVideoManager.startCamera(null);
		assertTrue(StreamVideoManager.cameraActive);
		StreamVideoManager.startCamera(null);
		assertTrue(StreamVideoManager.cameraActive);
		Thread.sleep(1500);
		StreamVideoManager.closeCamera();
		assertFalse(StreamVideoManager.cameraActive);
		StreamVideoManager.closeCamera();
		assertFalse(StreamVideoManager.cameraActive);
	}

	//la funzione start fallisce per id non valido
	@Test
	public void testStartCloseCamera2() throws InterruptedException {
		StreamVideoManager.cameraId = 5;
		StreamVideoManager.startCamera(null);
		assertFalse(StreamVideoManager.cameraActive);
		StreamVideoManager.closeCamera();
	}

	//test della funzione start con parametro non "null".
	@Test
	public void testStartCloseCamera3() throws InterruptedException {
		ImageView iv = new ImageView();
		StreamVideoManager.startCamera(iv);
		assertTrue(StreamVideoManager.cameraActive);
		Thread.sleep(1500);
		StreamVideoManager.closeCamera();
		assertFalse(StreamVideoManager.cameraActive);
	}

	@Test
	public void testStarStoptRec1() throws InterruptedException {
		StreamVideoManager.startCamera(null);	
		StreamVideoManager.startRec("video.avi");
		Thread.sleep(1500);
		StreamVideoManager.stopRec();
		StreamVideoManager.closeCamera();

		File file = new File("video.avi");
		assertTrue(file.exists());
		assertTrue(file.delete());

	}

	//cattura e registrazione in scala di grigi.
	@Test
	public void testStarStoptRec2() throws InterruptedException {
		StreamVideoManager.greyScale = true;
		StreamVideoManager.startCamera(null);	

		StreamVideoManager.startRec("greyVideo.avi");
		Thread.sleep(1500);
		StreamVideoManager.stopRec();
		StreamVideoManager.closeCamera();

		File gfile = new File("greyVideo.avi");
		assertTrue(gfile.exists());
		assertTrue(gfile.delete());
	}

	//chiusura camera durante la registrazione.
	@Test
	public void testStarStoptRec3() throws InterruptedException {
		StreamVideoManager.startCamera(null);	

		StreamVideoManager.startRec("Video.avi");
		Thread.sleep(1500);
		StreamVideoManager.closeCamera();
		StreamVideoManager.stopRec();

		File gfile = new File("Video.avi");
		assertTrue(gfile.exists());
		assertTrue(gfile.delete());
	}

	@Test
	public void testSetMillisPerFrame() {
		int fps = 50;
		StreamVideoManager.setMillisPerFrame(fps);
	}

	@Test
	public void testGetFirstVideoWrite() {
		long fvw = StreamVideoManager.getFirstVideoWrite();
		assertNotNull(fvw);
	}

}

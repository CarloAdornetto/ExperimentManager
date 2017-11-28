package test_strutturali;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import application.ImageUtility;
import application.LibrariesLoader;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ImageUtilityTest extends Application {

	//Metodo vuoto. Non verrà mostrato nessuno Stage durante i test.
	@Override
	public void start(Stage primaryStage) throws Exception {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LibrariesLoader.loadOpenCVdll();
		//ho bisogno di un thread JavaFX per rggiungere la massima copertura
		//sulla funzione OnFXThread().
		Thread t = new Thread("JavaFX Init Thread") {
			public void run() {
				try {
					Application.launch(ImageUtilityTest.class, new String[0]);
				} catch (Exception e) {
				}
			}
		};
		t.start();
		Thread.sleep(500);
	}

	@Test
	public void testMat2Image() throws UnsupportedOperationException {
		Mat testFrameColor = new Mat(320, 240, CvType.CV_8UC3, new Scalar(255, 230, 240));
		Mat testFrameGreyScale = new Mat(320, 240, CvType.CV_8UC1, new Scalar(255, 230, 240));

		Image buffImgColor = ImageUtility.mat2Image(testFrameColor);
		Image buffImgGreyScale = ImageUtility.mat2Image(testFrameGreyScale);

		assertNotNull(buffImgColor);
		assertNotNull(buffImgGreyScale);
	}

	@Test (expected = java.lang.UnsupportedOperationException.class)
	public void testMat2ImageExcept() throws Exception {
		Mat frame = new Mat(320, 240, CvType.CV_16U, new Scalar(255, 230, 240));
		ImageUtility.mat2Image(frame);
	}

	@Test
	public <T> void testOnFXThread() throws UnsupportedOperationException, InterruptedException{
		Mat testFrame = new Mat(320, 240, CvType.CV_8UC3, new Scalar(255, 230, 240));
		ImageView iv = new ImageView();
		Image imageToShow = ImageUtility.mat2Image(testFrame);
		ImageUtility.onFXThread(iv.imageProperty(), imageToShow);
		Thread.sleep(500);
		assertNotNull(iv.imageProperty().getValue());
	}

}

package test_strutturali;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ StreamVideoManagerTest.class, AudioTimerTest.class, ExperimentManagerTest.class, ExperimentParametersTest.class,
		ExperimentTonesListTest.class, ImageUtilityTest.class, ResponseTest.class, ResponseToSyncTest.class,
		StdAudioTest.class, SynchronizerTest.class, SyncResponseTest.class,
		SyncTrialTest.class, ToneTest.class, TrialTest.class, TrialToPlayTest.class, TrialToSyncTest.class,
		UtilsTest.class })
public class AllTests {

}

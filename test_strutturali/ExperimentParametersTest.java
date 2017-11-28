package test_strutturali;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import application.ExperimentManager;
import application.ExperimentParameters;

@RunWith(ExperimentParametersTest.class)
@SuiteClasses({ ExperimentParametersTest.ValidationsTest.class, ExperimentParametersTest.ParametersSetTest.class,
	ExperimentParametersTest.SetterGetterTest.class})
public class ExperimentParametersTest extends Suite{

	public ExperimentParametersTest(Class<?> klass, RunnerBuilder builder) throws InitializationError {
		super(klass, builder);
	}

	@RunWith(Parameterized.class)
	public static class ValidationsTest {
		String s;
		ExperimentParameters ep4Test; 

		@Parameters
		public static Collection<String[]> data() {
			return Arrays.asList(new String[][] {
				{"0"}, {"-12"}, {"4.4"}, {"5"}  
			});
		}

		public ValidationsTest(String s) {
			ep4Test = new ExperimentParameters();
			this.s = s;
		}

		@Test
		public void testIsValidTrialNumber() {
			if (s.equals("5")) {
				assertTrue(ep4Test.isValidTrialNumber(s));
			} else {
				assertFalse(ep4Test.isValidTrialNumber(s));
			}
		}

		@Test
		public void testIsValidMean() {
			if (!s.equals("-12")) {
				assertTrue(ep4Test.isValidMean(s));
			} else {
				assertFalse(ep4Test.isValidMean(s));
			}
		}

		@Test
		public void testIsValidStdDev() {
			if (!s.equals("-12")) {
				assertTrue(ep4Test.isValidStdDev(s));
			} else {
				assertFalse(ep4Test.isValidStdDev(s));
			}
		}
	}

	@RunWith(Parameterized.class)
	public static class ParametersSetTest {
		ExperimentParameters ep4Test;
		String candidateMean;
		String candidateStdDev;

		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
			ExperimentManager.experimentTonesList.addNewTone("t1", 440, 1, 5, 100);
		}

		@Parameters
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] {
				//{mean, stdDev}
				{"4", "1"}, {"3", "1"}, {"2", "1.5"}
			});
		}

		public ParametersSetTest(String mean, String stdDev ) {
			this.ep4Test = new ExperimentParameters();
			this.candidateMean = mean;
			this.candidateStdDev = stdDev;
		}

		@Test
		public void testIsValidParameterSet() {
			boolean cond = ep4Test.isValidParameterSet(this.candidateMean, this.candidateStdDev);
			if (Double.parseDouble(candidateMean) - Double.parseDouble(candidateStdDev) 
					< ExperimentParameters.VALID_SET_FACTOR  * 
					ExperimentManager.experimentTonesList.getMaxToneDuration()) {

				assertFalse(cond);
			} else {
				assertTrue(cond);
			}
		}
	}

	public static class SetterGetterTest {
		static ExperimentParameters ep4Test;
		static int trialNumber;
		static double mean;
		static double stdDev;

		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
			ep4Test = new ExperimentParameters();
			trialNumber = 10;
			mean = 5.5;
			stdDev = 3.0;
		}	

		@Test
		public void testSetGetTrialNumber() {
			ep4Test.setTrialNumber(trialNumber);
			assertEquals(trialNumber, ep4Test.getTrialNumber());
		}

		@Test
		public void testSetGetMean() {
			ep4Test.setMean(mean);
			assertEquals(mean, ep4Test.getMean(), 0.0);
		}

		@Test
		public void testSetGetStandardDeviation() {
			ep4Test.setStandardDeviation(stdDev);
			assertEquals(stdDev, ep4Test.getStandardDeviation(), 0.0);
		}
	}

}

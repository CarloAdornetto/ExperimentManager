package test_strutturali;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import application.Utils;

@RunWith(Parameterized.class)
public class UtilsTest {

	Utils u = new Utils();
	String s;

	@Parameters
	public static Collection<String[]> data() {
		return Arrays.asList(new String[][] {
			{""}, {"f"}, {"-3.0"}, {"5.3"}, {"0"}, {"5"}  
		});
	}

	public UtilsTest(String s) {
		this.s = s;
	}

	@Test
	public void testIsPositiveInteger() {
		if (s.equals("5") || s.equals("0")) {
			assertTrue(Utils.isPositiveInteger(s, 10));
		} else {
			assertFalse(Utils.isPositiveInteger(s, 10));
		}
	}

	@Test
	public void testIsPositiveDouble() {
		if (s.equals("5.3") || s.equals("0") || s.equals("5")) {
			assertTrue(Utils.isPositiveDouble(s));
		} else {
			assertFalse(Utils.isPositiveDouble(s));
		}
	}

}

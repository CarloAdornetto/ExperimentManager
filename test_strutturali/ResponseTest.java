package test_strutturali;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.Response;

public class ResponseTest {

	Response r;
	String id = "r1";

	@Before
	public void setUp() throws Exception {
		r = new Response(id);
	}

	@Test
	public void testResponse() {
		assertNotNull(r);
	}

	@Test
	public void testGetResponseId() {
		assertEquals(id, r.getResponseId());
	}

}

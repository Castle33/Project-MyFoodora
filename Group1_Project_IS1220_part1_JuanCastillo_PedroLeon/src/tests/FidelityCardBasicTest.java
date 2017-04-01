package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import system.FidelityCardBasic;

public class FidelityCardBasicTest {

	@Test
	public void testApplyFidelityPlan() {
		FidelityCardBasic fc = new FidelityCardBasic();
		assertTrue(fc.applyFidelityPlan() == 1);
	}

}

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import system.FidelityCardPoint;

public class FidelityCardPointTest {

	@Test
	public void testFidelityCardPoint() {
		FidelityCardPoint fcp = new FidelityCardPoint();
		assertTrue(fcp.getDiscount() == 0.1 && fcp.getDiscPoints() == 100 && fcp.getPoints() == 0);
	}

	@Test
	public void testApplyFidelityPlan() {
		FidelityCardPoint fcp = new FidelityCardPoint();
		assertTrue(fcp.applyFidelityPlan() == 1 || fcp.applyFidelityPlan() == 0.9);
	}

}

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import system.FidelityCardLottery;

public class FidelityCardLotteryTest {

	@Test
	public void testFidelityCardLottery() {
		FidelityCardLottery fc = new FidelityCardLottery();
		assertTrue(fc.getLotteryProba() == 0.1);
	}

	@Test
	public void testApplyFidelityPlan() {
		FidelityCardLottery fc = new FidelityCardLottery();
		assertTrue(fc.applyFidelityPlan() == 0 || fc.applyFidelityPlan() == 1);
	}

}

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import users.Manager;

public class ManagerTest {

	@Test
	public void testManager() {
		Manager m = new Manager("Pedro", "pleonpita", "passwordm", "Leon");
		assertTrue(m.getName() == "Pedro" && m.getUsername() == "pleonpita" && m.getPassword() == "passwordm" && m.getSurname() == "Leon");
	}
}

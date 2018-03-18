package com.mastercard.api.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mastercard.api.service.CityCheckerService;
import com.mastercard.api.service.CityCheckerServiceImpl;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {CityController.class, CityCheckerServiceImpl.class, CityCheckerService.class})

public class CityControllerTest {

	
	@Autowired
	CityController codeChallengeController;
	
	@Test
	public void hasConnectionTest() {
		assertEquals("yes", codeChallengeController.hasConnection("New York", "Boston"));
		assertEquals("yes", codeChallengeController.hasConnection("New York", "Newark"));
		assertEquals("yes", codeChallengeController.hasConnection("New York", "Philadelphia"));
		assertEquals("yes", codeChallengeController.hasConnection("Newark", "Boston"));
		assertEquals("yes", codeChallengeController.hasConnection("Boston", "Newark"));
		assertEquals("yes", codeChallengeController.hasConnection("Philadelphia", "Boston"));
		assertEquals("yes", codeChallengeController.hasConnection("Trenton", "Albany"));
		assertEquals("no", codeChallengeController.hasConnection("Philadelphia", "Albany"));
		assertEquals("no", codeChallengeController.hasConnection("New York", "Trenton"));
	}
	
	
	
}

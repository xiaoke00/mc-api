package com.mastercard.api.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CityCheckerService.class, CityCheckerServiceImpl.class})
public class CityCheckerServiceImplTest {

	@Autowired
	private CityCheckerService cityCheckerService;
	
	
	
	@Test
	public void convertAnwserTest() {
		assertEquals("yes", cityCheckerService.convertAnswer(true));
		assertEquals("no", cityCheckerService.convertAnswer(false));
	}
	
	@Test
	public void isConnectedTest() {
		assertEquals(true, cityCheckerService.isConnected("New York", "Boston"));
		assertEquals(true, cityCheckerService.isConnected("New York", "Newark"));
		assertEquals(true, cityCheckerService.isConnected("New York", "Philadelphia"));
		assertEquals(true, cityCheckerService.isConnected("Newark", "Boston"));
		assertEquals(true, cityCheckerService.isConnected("Boston", "Newark"));
		assertEquals(true, cityCheckerService.isConnected("Philadelphia", "Boston"));
		assertEquals(true, cityCheckerService.isConnected("Trenton", "Albany"));
		assertEquals(false, cityCheckerService.isConnected("Philadelphia", "Albany"));
		assertEquals(false, cityCheckerService.isConnected("New York", "Trenton"));
	}
	
	@Test
	public void isSimpleConnectionExistTest() {
		assertEquals(true, cityCheckerService.isSimpleConnectionExist("New York", "Boston"));
		assertEquals(false, cityCheckerService.isSimpleConnectionExist("New York", "Newark"));
		assertEquals(false, cityCheckerService.isSimpleConnectionExist("New York", "Philadelphia"));
		assertEquals(true, cityCheckerService.isSimpleConnectionExist("Newark", "Boston"));
		assertEquals(true, cityCheckerService.isSimpleConnectionExist("Boston", "Newark"));
		assertEquals(false, cityCheckerService.isSimpleConnectionExist("Philadelphia", "Boston"));
		assertEquals(true, cityCheckerService.isSimpleConnectionExist("Trenton", "Albany"));
		assertEquals(false, cityCheckerService.isSimpleConnectionExist("Philadelphia", "Albany"));
		assertEquals(false, cityCheckerService.isSimpleConnectionExist("New York", "Trenton"));
	}
	
	@Test
	public void isCityExist() {
		assertEquals(true, cityCheckerService.isCityExist("New York"));
		assertEquals(true, cityCheckerService.isCityExist("Boston"));
		assertEquals(true, cityCheckerService.isCityExist("Newark"));
		assertEquals(true, cityCheckerService.isCityExist("Trenton"));
		assertEquals(true, cityCheckerService.isCityExist("Albany"));
		assertEquals(true, cityCheckerService.isCityExist("Philadelphia"));
		assertEquals(false, cityCheckerService.isCityExist("New Jersey"));
		assertEquals(false, cityCheckerService.isCityExist("New Haven"));


		
	}
}

package com.mastercard.api.service;

public interface CityCheckerService {

	String convertAnswer(boolean isConnected);
	
	boolean isConnected(String location1, String location2);
	
	boolean isCityExist(String city);
	
	boolean isSimpleConnectionExist(String location1, String location2);
}

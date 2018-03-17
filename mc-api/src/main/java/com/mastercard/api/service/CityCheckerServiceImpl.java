package com.mastercard.api.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.mastercard.api.model.Graph;

@Service
public class CityCheckerServiceImpl implements CityCheckerService{

	private Graph graph = new Graph();
	
	private static Logger LOGGER = LogManager.getLogger(CityCheckerServiceImpl.class);
	
	public String convertAnswer(boolean isConnected) {
		if(isConnected)
			return "yes";
		else
			return "no";
	}
	
	public boolean isConnected(String location1, String location2) {
		Queue<String> queue = new LinkedList<String>(); 
		queue.add(location1);
		
		boolean isConnected = false;
		
		LinkedList<String> hasVisited = new LinkedList<String>(); 
		
		while(queue.size() != 0) {
			String currentCity = queue.poll();
			hasVisited.add(currentCity);
			LOGGER.info(hasVisited.toString());

			LinkedList<String> citySet = graph.connectionNodes(currentCity);
			
			for(String city : citySet) {
				if(city.equals(location2)) {
					isConnected = true;
					break;
				}else {
					if(!city.equals(location1) && !hasVisited.contains(city)) {
						queue.add(city);
					}
				}
			}
		}
		
		return isConnected;
	}
	
	public boolean isCityExist(String city) {
		return graph.containKey(city);
	}
	
	public boolean isSimpleConnectionExist(String location1, String location2) {
		return graph.connectionNodes(location1).contains(location2);
	}
	
	@PostConstruct
	public void init() {
		try {
			File file = new ClassPathResource("city.txt").getFile();
			BufferedReader reader = null;
			
			try {
				reader = new BufferedReader(new FileReader(file));
				String cities; 
				while((cities = reader.readLine())!= null) {
					String city1 = cities.split(",")[0].trim();
					String city2 = cities.split(",")[1].trim();
					
					graph.addRoundConnection(city1, city2);
				}
			} catch (Exception e) {
				LOGGER.error("Cannot read the file: "+e);
			} finally {
				if(reader != null) reader.close();
			}
		} catch (IOException e) {
			LOGGER.error("Cannot find the file: "+e);
		}
	}
}

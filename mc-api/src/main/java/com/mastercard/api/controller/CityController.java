package com.mastercard.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mastercard.api.service.CityCheckerService;

@Controller
public class MasterCardApiController {

	private static Logger LOGGER = LogManager.getLogger(MasterCardApiController.class);
	
	@Autowired
	private CityCheckerService cityCheckerService;
	
	@RequestMapping(value = "/connected", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String hasConnection(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
		boolean isConnected = false;
		
		LOGGER.info("City1 : "+origin+" City2: "+destination);
		
		if(cityCheckerService.isCityExist(origin) && cityCheckerService.isCityExist(destination)) {
			if(cityCheckerService.isSimpleConnectionExist(origin, destination)) {
				isConnected = true;
			}else {
				isConnected = cityCheckerService.isConnected(origin, destination);
			}
		}
		
		return cityCheckerService.convertAnswer(isConnected);
	}
}

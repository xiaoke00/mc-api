package com.mastercard.api.model;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {

	private Map<String, LinkedHashSet<String>> graphMap = new HashMap<String, LinkedHashSet<String>>();

	public void addConnection(String location1, String location2) {
		LinkedHashSet<String> connection = graphMap.get(location1);
		if (connection == null) {
			connection = new LinkedHashSet<String>();
			graphMap.put(location1, connection);
		}
		connection.add(location2);
	}

	public void addRoundConnection(String location1, String location2) {
		addConnection(location1, location2);
		addConnection(location2, location1);
	}

	public boolean isConnected(String location1, String location2) {
		Set<String> connection = graphMap.get(location1);
		if (connection == null) {
			return false;
		}
		return connection.contains(location2);
	}

	public LinkedList<String> connectionNodes(String location) {
		LinkedHashSet<String> connection = graphMap.get(location);
		if (connection == null) {
			return new LinkedList<String>();
		}
		return new LinkedList<String>(connection);
	}

	public boolean containKey(String location) {
		return graphMap.containsKey(location);
	}
}

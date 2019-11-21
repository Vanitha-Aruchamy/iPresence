package com.iPresence.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> readJsonMapObject(String fileName, String requiredfield) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Map<String, Object>> input = null;
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/" + fileName + ".json"), StandardCharsets.UTF_8);
			input = objectMapper.readValue(in,
					Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> endresult = input.get(requiredfield);
		return endresult;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Map<String, Map<String, Object>>> readJsonMapofMap(String fileName,
			String requiredfield) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, List<Map<String, Map<String, Object>>>> input = null;
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/" + fileName + ".json"), StandardCharsets.UTF_8);
			input = objectMapper.readValue(in,
					Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map<String, Map<String, Object>>> endresult = input.get(requiredfield);
		System.err.println("Array List :" + endresult);
		return endresult;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Map<String, Object>> readJsonMapArrayList(String fileName,
			String requiredfield) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, ArrayList<Map<String, Object>>> input = null;
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/" + fileName + ".json"), StandardCharsets.UTF_8);
			input = objectMapper.readValue(in,
					Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<Map<String, Object>> endresult = input.get(requiredfield);
		System.err.println("Array List :" + endresult);
		return endresult;
	}

}

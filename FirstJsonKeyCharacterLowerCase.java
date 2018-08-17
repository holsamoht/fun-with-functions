package com.pdgc.test;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.pdgc.general.util.files.FileUtil;

public class Convenience {
	@Test
	public void convertToCamel() throws IOException {
		byte[] message = FileUtil.readAllBytesFromResource(this.getClass(), "/testMessage/xMenApocalypseTest.json");
		String messageString = new String(message, "UTF-8");
		JSONObject json = new JSONObject(messageString);
		
		JSONObject result = (JSONObject) convertToCamel(json);
		System.out.println(result.toString());
	}
	
	public Object convertToCamel(Object json) {
		if(json instanceof JSONObject) {
			JSONObject result = new JSONObject();
			JSONObject jsonObj = (JSONObject) json;
			for(String key : jsonObj.keySet()) {
				result.put(lowerCaseFirst(key), convertToCamel(jsonObj.get(key)));
			}
			return result;
		}
		else if (json instanceof JSONArray) {
			JSONArray result =  new JSONArray();
			JSONArray jsonArr = (JSONArray) json;
			for(int i = 0; i <  jsonArr.length(); i++) {
				result.put(convertToCamel(jsonArr.getJSONObject(i)));
			}
			return result;
		} else {
			return json;
		}
	}
	
	public String lowerCaseFirst(String key) {
		char[] c = key.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		String camelKey = new String(c);
		return camelKey;
	}
}

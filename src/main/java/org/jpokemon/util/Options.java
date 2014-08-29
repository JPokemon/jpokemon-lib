package org.jpokemon.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Options {
	public static List<String> parseArray(String string) {
		string = string.replaceAll("\\[", "");
		string = string.replaceAll("\\]", "");
		return Arrays.asList(string.split(","));
	}

	public static String serializeArray(List<String> strings) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append('[');

		for (String string : strings) {
			stringBuilder.append(string);
			stringBuilder.append(',');
		}

		stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "}");

		return stringBuilder.toString();
	}

	public static Map<String, String> parseMap(String string) {
		string = string.replaceAll("{", "");
		string = string.replaceAll("}", "");
		String[] entries = string.split(",");
		Map<String, String> map = new HashMap<String, String>();

		for (String s : entries) {
			String[] entry = s.split("=");
			String key = entry[0];
			String value = entry[1];
			map.put(key, value);
		}

		return map;
	}

	public static String serializeMap(Map<String, ?> map) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append('{');

		for (Map.Entry<String, ?> entry : map.entrySet()) {
			stringBuilder.append(entry.getKey());
			stringBuilder.append('=');
			stringBuilder.append(entry.getValue());
			stringBuilder.append(',');
		}

		stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "}");

		return stringBuilder.toString();
	}
}

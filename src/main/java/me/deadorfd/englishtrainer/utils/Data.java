package me.deadorfd.englishtrainer.utils;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @Author DeaDorfd
 * @Project englishtrainer
 * @Package me.deadorfd.englishtrainer.utils
 * @Date 15.01.2023
 * @Time 23:10:34
 */
public class Data {
	public static ArrayList<String> vocabulary = new ArrayList<>();
	public static String programmName = "Englisch Trainer";
	public static Color color = new Color(128, 0, 255);

	public static String getGermanName(int count) {
		int i = 0;
		for (String str : vocabulary) {
			i++;
			if (i == count) return str.split(",")[0];
		}
		return "Error";
	}

	public static String getEnglishName(int count) {
		int i = 0;
		for (String str : vocabulary) {
			i++;
			if (i == count) return str.split(",")[1];
		}
		return "Error";
	}

}
package me.deadorfd.englishtrainer.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @Author DeaDorfd
 * @Project englishtrainer
 * @Package me.deadorfd.englishtrainer.utils
 * @Date 16.01.2023
 * @Time 02:54:23
 */
public class Translator {

	public static String translate(String langFrom, String langTo, String text) {
		// INSERT YOU URL HERE
		String urlStr = null;
		try {
			urlStr = "https://script.google.com/macros/s/AKfycbzvqxEn30BaBCmUX6LNbeus3fKM35c1LlEBkMFNxvpyowjjG72Z4-hM0eyJM6OOCLR0/exec"
					+ "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + langTo + "&source=" + langFrom;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		StringBuilder response = new StringBuilder();
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}
}
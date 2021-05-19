package com.zendesk.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.List;

public class Utils {
	
	private Utils() {
		// private constructor to hide the public one
	}
	
	/**
	 * Calculates the time interval between 2 different times
	 * 
	 * @param sStartTime string value of Unix timestamp (Epoch time)
	 * @param sEndTime	 string value of Unix timestamp (Epoch time)
	 * @param defaultVal integer value will be returned when any error/exception
	 * @return approximate hours
	 */
	public static int calculateHourDifference(String sStartTime, String sEndTime, int defaultVal) {
		int hours = defaultVal;
		try {
			// epoch time could be in seconds or milliseconds
			long lStartTime = Long.parseLong(sStartTime) * (sStartTime.length() <= 10 ? 1000L : 1L);
			long lEndTime = Long.parseLong(sEndTime) * (sEndTime.length() <= 10 ? 1000L : 1L);
			
			// convert to Timestamp
			Timestamp timestamp1 = new Timestamp(lStartTime);
			Timestamp timestamp2 = new Timestamp(lEndTime);
			
			// get difference between 2 timestamps in milliseconds
			long milliseconds = timestamp2.getTime() - timestamp1.getTime();
			
			if (milliseconds > 0) {
				// convert to seconds
				int seconds = (int) (milliseconds / 1000);
				
				// calculate hours minutes
				hours = seconds / 3600;
				int minutes = (seconds % 3600) / 60;
				
				// Round up to nearest hour if the left over is 1 minute upward
				if (minutes >= 1)
					hours++;
			}
		} catch (Exception e) {/* do nothing */}
		
		return hours;
	}
	
	/**
	 * Read all lines from a file. Bytes are decoded using the UTF-8 charset.
	 * 
	 * @param filePath
	 * @return the lines from the file as a List
	 * @throws IOException if an I/O error occurs
	 */
	public static List<String> readFileToList(String filePath) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		return lines;
	}
	
	/**
	 * Read a text file to String
	 * 
	 * @param filePath
	 * @return all file contents in a String
	 * @throws IOException if an I/O error occurs
	 */
	public static String readFileToString(String filePath) throws IOException {
		byte[] content = Files.readAllBytes(Paths.get(filePath));
		return new String(content);
	}
	
	/**
	 * Converts the string representation of a number to its signed decimal integer equivalent
	 * 
	 * @param value		 a string to be parsed
	 * @param defaultVal an integer number will be returned when the conversion failed
	 * @return the integer value represented by the argument in decimal.
	 */
	public static int tryParseInt(String value, int defaultVal) {
	    try {
	        return Integer.parseInt(value);
	    } catch (NumberFormatException e) {
	        return defaultVal;
	    }
	}
	
	/**
	 * Prints out an error message or an exception for debugging purpose
	 */
	public static void printError(Object error) {
		System.out.println(MessageFormat.format("[Error] {0}", error));
	}
}

package com.zendesk;

import org.junit.Test;

import com.zendesk.parser.ArgumentParser;
import com.zendesk.service.ValetParkingService;
import com.zendesk.util.Utils;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

public class ValidParkingAppTest {

	@Test
	public void testApp_01() throws IOException {
		final String inputFile = "src\\test\\resource\\Input_01.txt";
		final String modelFile = "src\\test\\resource\\Output_01.txt";
		
		String expected = Utils.readFileToString(modelFile);
		
		List<String> inputLines = Utils.readFileToList(inputFile);
		ArgumentParser argParser = new ArgumentParser(inputLines);
		ValetParkingService service = new ValetParkingService(argParser);
		String actual = service.manageParking();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testApp_02() throws IOException {
		final String inputFile = "src\\test\\resource\\Input_02.txt";
		final String modelFile = "src\\test\\resource\\Output_02.txt";
		
		String expected = Utils.readFileToString(modelFile);
		
		List<String> inputLines = Utils.readFileToList(inputFile);
		ArgumentParser argParser = new ArgumentParser(inputLines);
		ValetParkingService service = new ValetParkingService(argParser);
		String actual = service.manageParking();
		
		assertEquals(expected, actual);
	}
}

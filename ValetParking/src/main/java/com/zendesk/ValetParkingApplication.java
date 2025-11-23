package com.zendesk;

import java.util.List;

import com.zendesk.enums.ExitCode;
import com.zendesk.parser.ArgumentParser;
import com.zendesk.service.ValetParkingService;
import com.zendesk.util.Utils;

public class ValetParkingApplication {

	public static void main(String[] args) {
		ArgumentParser argParser = null;
		try {
			// get input file path
			String inputFile = args[0].toString();
			// read data from file
			List<String> inputLines = Utils.readFileToList(inputFile);
			// parse and validate all input data
			argParser = new ArgumentParser(inputLines);
		} catch (Exception ex) {
			Utils.printError(ex);
			System.exit(ExitCode.INVALID_ARGUMENT.getCode());
		}
		
		try {
			// call ValetParkingService to generate the outcome
			ValetParkingService service = new ValetParkingService(argParser);
			String output = service.manageParking();
			// print-out the result
			System.out.println(output);
		} catch (Exception ex) {
			Utils.printError(ex);
			System.exit(ExitCode.PROCESS_ERROR.getCode());
		}
	}
}

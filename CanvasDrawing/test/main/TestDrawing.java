package main;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class TestDrawing {

	public static final FileOutputStream outConsole = new FileOutputStream(FileDescriptor.out);
	
	public static void main(String[] args) {
		TestDrawing test = new TestDrawing();
		for (int i=1; i<=12; i++) {
			test.testExecute(i);
		}
	}
	
	public void testExecute(int caseNo) {
		System.out.print(String.format("Test case %d:\t", caseNo));
		try {
			switch (caseNo) {
				case 1:
					testExecute_1();
					break;
				case 2:
					testExecute_2();
					break;
				case 3:
					testExecute_3();
					break;
				case 4:
					testExecute_4();
					break;
				case 5:
					testExecute_5();
					break;
				case 6:
					testExecute_6();
					break;
				case 7:
					testExecute_7();
					break;
				case 8:
					testExecute_8();
					break;
				case 9:
					testExecute_9();
					break;
				case 10:
					testExecute_10();
					break;
				case 11:
					testExecute_11();
					break;
				case 12:
					testExecute_12();
					break;
				default:
					System.out.println("undefined");
					break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void testExecute_1() {
		String[] inputs = { "C 10 5" };
		String expectedResult = "[------------][|          |][|          |][|          |][|          |][|          |][------------]";
		
		Drawing myDrawing = new Drawing();
		for (String cmd : inputs) {
			myDrawing.execute(cmd);
		}
		String output = myDrawing.toString();
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_2() {
		String[] inputs = {	"C 10 5", "L 2 3 9 3" };
		String expectedResult = "[------------][|          |][|          |][| xxxxxxxx |][|          |][|          |][------------]";
		
		Drawing myDrawing = new Drawing();
		for (String cmd : inputs) {
			myDrawing.execute(cmd);
		}
		String output = myDrawing.toString();
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_3() {
		String[] inputs = {	"C 10 5", "L 6 4 6 2" };
		String expectedResult = "[------------][|          |][|     x    |][|     x    |][|     x    |][|          |][------------]";
		
		Drawing myDrawing = new Drawing();
		for (String cmd : inputs) {
			myDrawing.execute(cmd);
		}
		String output = myDrawing.toString();
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_4() {
		String[] inputs = {	"C 10 5", "R 8 1 3 4" };
		String expectedResult = "[------------][|  xxxxxx  |][|  x    x  |][|  x    x  |][|  xxxxxx  |][|          |][------------]";
		
		Drawing myDrawing = new Drawing();
		for (String cmd : inputs) {
			myDrawing.execute(cmd);
		}
		String output = myDrawing.toString();
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_5() {
		String[] inputs = {
				"C 10 5",
				"L 2 3 9 3",
				"L 6 4 6 2",
				"R 8 1 3 4",
				"B 9 5 o"
		};
		String expectedResult = "[------------][|ooxxxxxxoo|][|oox  x xoo|][|oxxxxxxxxo|][|ooxxxxxxoo|][|oooooooooo|][------------]";
		
		Drawing myDrawing = new Drawing();
		for (String cmd : inputs) {
			myDrawing.execute(cmd);
		}
		String output = myDrawing.toString();
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_6() {
		String[] inputs = { " " };
		String expectedResult = "[error] invalid command\r\n";
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(outContent));
		String output = "";
		try {
		    Drawing myDrawing = new Drawing();
			for (String cmd : inputs) {
				myDrawing.execute(cmd);
			}
			output = outContent.toString();
			outContent.flush();
			outContent.close();
		}
		catch (Exception ex) {}
		finally {
			System.setOut(new PrintStream(outConsole));
		    System.setErr(new PrintStream(outConsole));
		}
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_7() {
		String[] inputs = { "C 0 6" };
		String expectedResult = "[error] invalid command\r\n";
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(outContent));
		String output = "";
		try {
		    Drawing myDrawing = new Drawing();
			for (String cmd : inputs) {
				myDrawing.execute(cmd);
			}
			output = outContent.toString();
			outContent.flush();
			outContent.close();
		}
		catch (Exception ex) {}
		finally {
			System.setOut(new PrintStream(outConsole));
		    System.setErr(new PrintStream(outConsole));
		}
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_8() {
		String[] inputs = { "C 10 6", "L 9 3 9" };
		String expectedResult = "[error] invalid command\r\n";
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(outContent));
		String output = "";
		try {
		    Drawing myDrawing = new Drawing();
			for (String cmd : inputs) {
				myDrawing.execute(cmd);
			}
			output = outContent.toString();
			outContent.flush();
			outContent.close();
		}
		catch (Exception ex) {}
		finally {
			System.setOut(new PrintStream(outConsole));
		    System.setErr(new PrintStream(outConsole));
		}
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_9() {
		String[] inputs = { "C 10 6", "R 6 -1 4 5" };
		String expectedResult = "[error] invalid command\r\n";
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(outContent));
		String output = "";
		try {
		    Drawing myDrawing = new Drawing();
			for (String cmd : inputs) {
				myDrawing.execute(cmd);
			}
			output = outContent.toString();
			outContent.flush();
			outContent.close();
		}
		catch (Exception ex) {}
		finally {
			System.setOut(new PrintStream(outConsole));
		    System.setErr(new PrintStream(outConsole));
		}
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_10() {
		String[] inputs = { "K 10 4" };
		String expectedResult = "[error] invalid command\r\n";
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(outContent));
		String output = "";
		try {
		    Drawing myDrawing = new Drawing();
			for (String cmd : inputs) {
				myDrawing.execute(cmd);
			}
			output = outContent.toString();
			outContent.flush();
			outContent.close();
		}
		catch (Exception ex) {}
		finally {
			System.setOut(new PrintStream(outConsole));
		    System.setErr(new PrintStream(outConsole));
		}
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_11() {
		String[] inputs = { "L 2 4 2 7" };
		String expectedResult = "[warn] please initialize a canvas first\r\n";
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(outContent));
		String output = "";
		try {
		    Drawing myDrawing = new Drawing();
			for (String cmd : inputs) {
				myDrawing.execute(cmd);
			}
			output = outContent.toString();
			outContent.flush();
			outContent.close();
		}
		catch (Exception ex) {}
		finally {
			System.setOut(new PrintStream(outConsole));
		    System.setErr(new PrintStream(outConsole));
		}
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	private void testExecute_12() {
		String[] inputs = {
				"C 10 5",
				"L 2 3 9 3",
				"L 6 4 6 2",
				"R 8 1 3 4",
				"B 9 5 x"
		};
		String expectedResult = "[info] colour x cannot be used\r\n";
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(outContent));
		String output = "";
		try {
		    Drawing myDrawing = new Drawing();
			for (String cmd : inputs) {
				myDrawing.execute(cmd);
			}
			output = outContent.toString();
			outContent.flush();
			outContent.close();
		}
		catch (Exception ex) {}
		finally {
			System.setOut(new PrintStream(outConsole));
		    System.setErr(new PrintStream(outConsole));
		}
		
		if (output.equals(expectedResult)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
}

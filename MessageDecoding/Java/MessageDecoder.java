import java.io.IOException;

public class MessageDecoder {
	
	public static final char END_OF_FILE = '\u0000';
	private static String inputFile;
	private static int index;
	// encoded array [length][value]
	public static int[][] code = new int[8][1 << 8];
	
	public MessageDecoder(String input) {
		inputFile = input;
		index = 0;
	}
	
	public void decodeMessage() throws IOException {
		while (readCodes() != 0) {
			while (true) {
				// read the code length
				int len = readInt(3);
				// if 0, exit the current encoding loop
				if (len == 0) {
					break;
				}
				while (true) {
					int v = readInt(len);
					// if 1, exit the current section
					if (v == (1 << len) - 1) {
						break;
					}
					System.out.print((char)code[len][v]);
				}
			}
			System.out.print('\n');
		}
	}

	private int readCodes() throws IOException {
		// read the first character of the encoding header
		code[1][0] = readChar();
		// cycle encoding from the second character
		for (int len = 2; len <= 7; len++) {
			for (int i = 0; i < (1 << len) - 1; i++) {
				int ch = getNextChar();
				// end of file, terminate the program
				if (ch == END_OF_FILE) {
					return 0;
				}
				// read a line
				if (ch == '\n' || ch == '\r') {
					return 1;
				}
				code[len][i] = ch;
			}
		}
		return 1;
	}
	
	private int readChar() throws IOException {
		while (true) {
			int ch = getNextChar();
			// read non-newline characters
			if (ch != '\n' && ch != '\r') {
				return ch;
			}
		}
	}

	private int readInt(int c) throws IOException {
		int v = 0;
		// get a decimal integer
		while ((c--) != 0) {
			v = v * 2 + readChar() - '0';
		}
		return v;
	}
	
	private char getNextChar() {
		char[] arr = inputFile.toCharArray();
		int len = arr.length;
		if (index < len) {
			char ch = arr[index];
			index++;
			return ch;
		}
		return END_OF_FILE;
	}
	
	public static void main(String[] args) {
		try {
			String input = "TNM AEIOU\r\n" + 
					"0010101100011\r\n" + 
					"1010001001110110011\r\n" + 
					"11000\r\n" + 
					"$#**\\\r\n" + 
					"0100000101101100011100001000";
			
			System.out.println("Input:\n" + input);
			System.out.print('\n');
			System.out.println("Output:");
			MessageDecoder md = new MessageDecoder(input);
			md.decodeMessage();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

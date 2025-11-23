package main

import (
	"fmt"
)

const endingChar byte = '\000'

// encoded array [length][value]
var code [8][1 << 8]int
var inputFile string
var index int

func getNextChar() byte {
	var charArrs []byte = []byte(inputFile)
	var len int = len(charArrs)
	if index < len {
		ch := charArrs[index]
		index++
		return ch
	}
	return endingChar
}

func readChar() int {
	for {
		ch := getNextChar()
		// read non-newline characters
		if ch != '\n' && ch != '\r' {
			return int(ch)
		}
	}
}

func readInt(c int) int {
	var v int = 0
	// get a decimal integer
	for i := c - 1; i >= 0; i-- {
		v = v*2 + readChar() - '0'
	}
	return v
}

func readCodes() int {
	// read the first character of the encoding header
	code[1][0] = readChar()
	// cycle encoding from the second character
	for len := 2; len <= 7; len++ {
		for i := 0; i < ((1 << len) - 1); i++ {
			ch := getNextChar()
			// end of file, terminate the program
			if ch == endingChar {
				return 0
			}
			// read a line
			if ch == '\n' || ch == '\r' {
				return 1
			}
			code[len][i] = int(ch)
		}
	}
	return 1
}

func main() {
	inputFile = "TNM AEIOU\r\n" +
		"0010101100011\r\n" +
		"1010001001110110011\r\n" +
		"11000\r\n" +
		"$#**\\\r\n" +
		"0100000101101100011100001000"
	fmt.Println("-- input: --")
	fmt.Println(inputFile)
	fmt.Println()
	fmt.Println("-- output: --")

	//start decoding
	index = 0
	for readCodes() != 0 {
		for {
			// read the code length
			len := readInt(3)
			// when 0, exit the current encoding loop
			if len == 0 {
				break
			}
			for {
				v := readInt(len)
				// when 1, exit the current section
				if v == ((1 << len) - 1) {
					break
				}
				fmt.Print(string(code[len][v]))
			}
		}
		fmt.Println()
	}
}

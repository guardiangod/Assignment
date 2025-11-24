## CANVAS DRAWING PROGRAMMER (MAY 2020)

## To Compile and Run the program:
1. Open a command prompt window and go to the directory where you saved the project (assume it is C:\)

```
cd C:\canvasdrawing\src\main
javac Drawing.java
```

2. If there is no error, command prompt will take you to the next line. Then, type the following to run the program:

```
javac Drawing
```

3. You will be able to see the console message printed on the window.

4. The Drawing application is supporting the following commands:

* C w h           : create a new canvas of width w and height h.
* L x1 y1 x2 y2   : create a new line from (x1,y1) to (x2,y2). Currently only horizontal 
					or vertical lines are supported. Horizontal and vertical lines will 
					be drawn using the 'x' character.
* R x1 y1 x2 y2   : create a new rectangle, whose upper left corner is (x1,y1) and 
					lower right corner is (x2,y2). Horizontal and vertical lines 
					will be drawn using the 'x' character.
* B x y c         : fill the entire area connected to (x,y) with "colour" c. 
					The behavior of this is the same as that of the "bucket fill" tool 
					in paint programs.
* Q               : quit the program.

## Tests:
All the test cases are in C:\canvasdrawing\test\mainTestDrawing.java

## Approach
1. I tried to use a TDD approach: creating tests, resolving them, improving solutions, etc.
2. Input validation must be strong, with anything other than a valid command resulting in an error message.
3. I have not specified the maximum size of canvas, the limit may depend on the screen size, but it might be reasonable to set a size.
4. As for the minimum canvas size, I was binding that the size should be a positive integer which is greater than 0. An 1x1 canvas is not really feasible, so maybe a larger minimum size would make sense.

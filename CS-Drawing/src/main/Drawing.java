package main;

import java.util.Scanner;

public class Drawing {
	
	public static final String CMD_CANVAS = "C";
	public static final String CMD_LINE = "L";
	public static final String CMD_RECTANGLE = "R";
	public static final String CMD_FILL = "B";
	public static final String CMD_QUIT = "Q";
	public static final String H_BORDER = "-";
	public static final String V_BORDER = "|";
	public static final String IN_LINE = "x";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			Drawing myDrawing = new Drawing();
			
			while (true) {
		    	System.out.print("enter command:");
		    	String cmd = in.nextLine();
		    	
		    	// exit on Q
		    	if (CMD_QUIT.equalsIgnoreCase(cmd)) {
		    		break;
		    	}
		    	
		    	if (myDrawing.execute(cmd)) {
		    		myDrawing.printCanvas();
		    	}
		    	System.out.println();
		    }
		} finally {
			in.close();
		}
	}
    
	private int width;
    private int height;
    private String[][] canvas = null;

	/**
     * execute command from the user
     */
    public boolean execute(String cmd) {
    	if (cmd != null && cmd.trim() != "") {
    		cmd = cmd.trim();
    	} else {
    		return invalidCommand();
    	}
    	
    	try {
	        String[] inputs = cmd.split("\\s");
	        
	        // new canvas creating command
	        if (CMD_CANVAS.equalsIgnoreCase(inputs[0]) && inputs.length == 3) {
	        	this.width = Integer.parseInt(inputs[1]);
                this.height = Integer.parseInt(inputs[2]);
                
	            // dimensions above minimum allowed    
	            if (this.width > 0 && this.height > 0) {    
	                this.canvas = buildCanvas();
	                return true;
	            } else {
	            	return invalidCommand();
	            }
            }
	        // drawing command
	        else if (inputs.length == 5) {
	        	if (this.canvas == null) {
            		return undefinedCanvas();
            	}
	        	
	        	int x1 = Integer.parseInt(inputs[1]);
                int y1 = Integer.parseInt(inputs[2]);
                int x2 = Integer.parseInt(inputs[3]);
                int y2 = Integer.parseInt(inputs[4]);
                
                if (x1 <= 0 || y1 <= 0 || x2 <= 0 || y2 <= 0 || 
                    x1 > this.width || x2 > this.width || y1 > this.height || y2 > this.height) {
                	return invalidCommand();
                }
                
                switch (inputs[0].toUpperCase()) {
	                case CMD_LINE:
	                	// vertical line
	                    if (x1 == x2) {
	                        drawVerticalLine(x1, y1, y2);
	                    }
	                    // horizontal line
	                    else if (y1 == y2) {
	                        drawHorizontalLine(x1, x2, y1);
	                    }
	                    else {
	                    	return invalidCommand();
	                    }
	                    break;
	                case CMD_RECTANGLE:
	                	drawHorizontalLine(x1, x2, y1);
	                    drawHorizontalLine(x1, x2, y2);
	                    drawVerticalLine(x1, y1, y2);
	                    drawVerticalLine(x2, y1, y2);
	                    break;
	                default:
	                	return invalidCommand();
	            }
                
                return true;
	        }
	        // colour filling command
	        else if (CMD_FILL.equalsIgnoreCase(inputs[0]) && inputs.length == 4) {
	        	if (this.canvas == null) {
            		return undefinedCanvas();
            	}
	        	
	        	int x = Integer.parseInt(inputs[1]);
                int y = Integer.parseInt(inputs[2]);
                String c  = inputs[3];
                
                if (x <= 0 || y <= 0 || x > this.width || y > this.height) {
                	return invalidCommand();
                }
                else if (IN_LINE.equalsIgnoreCase(c) || c.length() != 1) {
                    return rejectedColour(c);
                }
                
                bucketFill(x, y, c);
                return true;
	        }
	        else {
	        	return invalidCommand();
	        }
	    } catch (Exception e) {
	    	return invalidCommand();
	    }
	}
    
    /**
     * print the canvas
     */
    public void printCanvas() {
    	for (int y=0; y<=this.height+1; y++) {
    		for (int x=0; x<=this.width+1; x++) {
    			System.out.print(this.canvas[x][y]);
    		}
    		System.out.println();
    	}
    }
    
    /**
     * build a canvas of specified [width x height]
     */
    private String[][] buildCanvas() {
    	String[][] canvasbuild = new String[this.width+2][this.height+2];
    	// inner area
    	for (int x=1; x<=this.width; x++) {
    		for (int y=1; y<=this.height; y++) {
    			canvasbuild[x][y] = " ";
    		}
    	}
        // horizontal border
    	for (int x=0; x<=this.width+1; x++) {
        	canvasbuild[x][0] = H_BORDER;
        	canvasbuild[x][this.height+1] = H_BORDER;
        }
        // vertical border
        for (int y=1; y<=this.height; y++) {
            canvasbuild[0][y] = V_BORDER;
            canvasbuild[this.width+1][y] = V_BORDER;
        }
        
        return canvasbuild;
    }
    
    /**
     * draw vertical line line from node A(x,yA) to node B(x,yB)
     */
    private void drawVerticalLine(int x, int yA, int yB) {
    	int y1 = (yA <= yB ? yA : yB);
    	int y2 = (yA <= yB ? yB : yA);
    	
    	for (int k=y1; k<=y2; k++) {
    		this.canvas[x][k] = IN_LINE;
    	}
    }
    
    /**
     * draw horizontal line line from node A(xA,y) to node B(xB,y)
     */
    private void drawHorizontalLine(int xA, int xB, int y) {
    	int x1 = (xA <= xB ? xA : xB);
    	int x2 = (xA <= xB ? xB : xA);
    	
		for(int i=x1; i<=x2; i++) {
			this.canvas[i][y] = IN_LINE;
		}
    }
    
    /**
     * fill the entire area connected to node (x,y) with colour c
     */
    private void bucketFill(int x, int y, String c) {
    	try {
    		// node is not within the canvas
    		if (x <= 0 || y <= 0 || x > this.width || y > this.height) {
            	return;
            }
    		// node is in between of a line
    		if (IN_LINE.equalsIgnoreCase(this.canvas[x][y])) {
    			return;
    		}
    		// the colour of node is equal to the replacement colour
    		if (c.equalsIgnoreCase(this.canvas[x][y])) {
    			return;
    		}
    		
    		this.canvas[x][y] = c;
    		
    		// recursive - one step up
    		bucketFill(x, y-1, c);
    		// recursive - one step down
    		bucketFill(x, y+1, c);
    		// recursive - one step to the left
    		bucketFill(x-1, y, c);
    		// recursive - one step to the right
    		bucketFill(x+1, y, c);
    	} catch (Exception ex) {
    		System.out.println(ex);
    	}
    }
    
    /**
     * message on invalid command
     */
    private boolean invalidCommand() {
        System.out.println("[error] invalid command");
        return false;
    }
    
    /**
     * message on undefined canvas
     */
    private boolean undefinedCanvas() {
        System.out.println("[warn] please initialize a canvas first");
        return false;
    }
    
    /**
     * message on rejected colour
     */
    private boolean rejectedColour(String c) {
    	System.out.println("[info] colour " + c + " cannot be used");
        return false;
    }
    
    @Override
    public String toString() {
    	if (this.canvas == null) {
    		return null;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (int y=0; y<=this.height+1; y++) {
    		sb.append("[");
    		for (int x=0; x<=this.width+1; x++) {
    			sb.append(this.canvas[x][y]);
    		}
    		sb.append("]");
    	}
    	
    	return sb.toString();
	}
    
}

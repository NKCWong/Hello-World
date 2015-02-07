// import libraries
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/*
 * Nicole Wong, Period 2, 2/5/15
 * Time spent: 1.5 hours
 * 
 * Reflection: 
 * This was not a challenging lab, but it was helpful
 * in learning how to use the different methods of the
 * Graphics2D class, even though it took a while to get
 * all the drawing proportions correct. The most difficult
 * method to use was the drawArc, which took some API reading
 * practice in estimating the oval required to make the desired arc.
 * I used sin/cos to make the sunshines drawing. I didn't have any
 * other problems, most of the time was spent adjusting the drawing
 * proportions. 
 * 
 */

public class Exercise_2 {

	public static void main(String[] args) {

		// Create a basic Java window frame
		JFrame window = new JFrame("Exercise_2 - Nicole Wong, Period 4");

		// Decide what to do when the user closes the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the window size (see API)
		window.setBounds(200, 200, 640, 480);

		// Prevent users from resizing the window
		window.setResizable(false);

		// Create GUI components.
		// For us, create a custom JPanel to draw on.
		MySketchPad panel = new MySketchPad();

		// Add GUI components to the JFrame (window)
		window.add(panel);

		// Make the window visible
		window.setVisible(true);
	}
}

/*
   A JPanel is like a SketchPad in the sense that you can
   draw on it.  It's more powerful though, because it has
   more capabilities than a SketchPad, such as the ability
   to add buttons and GUI elements.  And you can add
   JPanels to JPanels.
*/
class MySketchPad extends JPanel {

	public void paintComponent(Graphics g) {

		// Set the background color to white (do this yourself)
		setBackground(Color.white);
		
		// Ask our parent to paint itself
		super.paintComponent(g);

		// Next, cast the Graphics parameter back into what
		// it really is - a more powerful Graphics2D object.
		// Or, if you want, you can leave it as a Graphics
		// and only use Graphics class methods.
		Graphics2D g2 = (Graphics2D)g;

		// Finally, draw stuff
		// draw the letters for labeling
		int x = 76; // not 80 to account for text width
		int y = 20;
		g2.setFont(new Font("Serif", Font.PLAIN, 12));
		for(int i = 0; i < 8; i++){
			g2.drawString("(" + (char)(97 + i) + ")", x, y);
			x += 160;
			if(x > 640){
				x = 80;
				y += 240;
			}
		}
		
		// draw first shape - the circle on top of a rect
		g2.drawOval(80 - 40 / 2, 70, 40, 40);
		g2.drawRect(80 - 35, 40 + 70, 70, 50);
		
		int centerX = 80 + 160;
		int topY = 70;
		
		// draw second shape - rounded rect 
		int squareLength = 100;
		g2.fillRoundRect(centerX - squareLength / 2, topY, squareLength, squareLength, squareLength / 2, squareLength / 2);
		g2.setColor(Color.WHITE);
		g2.fillOval(centerX - squareLength / 2, topY, squareLength / 2, squareLength / 2);
		g2.fillOval(centerX, topY, squareLength / 2, squareLength / 2);
		g2.fillOval(centerX - squareLength / 2, topY + squareLength / 2, squareLength / 2, squareLength / 2);
		g2.fillOval(centerX, topY + squareLength / 2, squareLength / 2, squareLength / 2);
		
		// draw third shape
		centerX += 160;
		int diag = 60;
		g2.setColor(Color.BLACK);
		int[] x1 = {centerX, centerX + diag / 2, centerX, centerX - diag / 2};
		int[] y1 = {topY, topY + diag / 2, topY + diag, topY + diag / 2};
		g2.fillPolygon(x1, y1, 4);
		
		// draw fourth shape
		centerX += 120;
		int length = 75; // from center of each circle
		int outerDiameter = 50; // diameter of outer circle
		g2.drawOval(centerX - length / 2, topY, outerDiameter, outerDiameter);
		g2.drawOval(centerX - length / 2 + 10, topY + 10, outerDiameter - 20, outerDiameter - 20);
		g2.drawOval(centerX + length / 2, topY, outerDiameter, outerDiameter);
		g2.drawOval(centerX + length / 2 + 10, topY + 10, outerDiameter - 20, outerDiameter - 20);
		g2.drawLine(centerX - length / 2 + outerDiameter / 2, topY, centerX + length / 2 + outerDiameter / 2, topY);
		g2.drawLine(centerX - length / 2 + outerDiameter / 2, topY+ outerDiameter, centerX + length / 2 + outerDiameter / 2, topY+ outerDiameter);
		
		// draw fifth shape
		centerX = 80;
		topY += 240 + 20;
		int circleRad = 15;
		drawCircle(g2, centerX, topY, circleRad);
		int dist = 23;
		double angle = Math.PI / 2; // 60 deg turns
		for(int i = 0; i < 6; i++){
			drawCircle(g2, (int)(centerX + dist * Math.cos(angle)), (int)(topY + dist * Math.sin(angle)), circleRad);
			angle += Math.PI / 3;
		}
		g2.setFont(new Font("Serif", Font.BOLD, 12));
		g2.drawString("Sunshines", centerX - 25, topY + 70);
		
		// draw the J logo
		centerX += 160;
		topY += 20;
		drawCircle(g2, centerX, topY, 50);
		g2.setColor(Color.GRAY);
		fillCircle(g2, centerX, topY, 35);
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("SansSerif", Font.BOLD, 65));
		g2.drawString("J", centerX - 20, topY + 10);
		
		// draw pie circle
		centerX += 160;
		g2.setColor(Color.BLACK);
		g2.drawArc(centerX - 50, topY - 50, 100, 100, 90, 270);
		g2.drawLine(centerX, topY, centerX, topY - 50);
		g2.drawLine(centerX, topY, centerX + 50, topY);
		
		// draw the smiley face
		centerX += 160;
		drawCircle(g2, centerX, topY, 50);
		drawCircle(g2, centerX - 15, topY - 10, 5);
		drawCircle(g2, centerX + 15, topY - 10, 5);
		g2.drawArc(centerX - 25, topY - 10, 50, 40, 230, 80);
		
	}
	
	// draws a circle with my own parameters
	// centerx and y are of the circle
	public void drawCircle(Graphics2D g, int centerX, int centerY, int radius){
		g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
	}
	
	// fills a circle with my own parameters
	public void fillCircle(Graphics2D g, int centerX, int centerY, int radius){
		g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
	}
}
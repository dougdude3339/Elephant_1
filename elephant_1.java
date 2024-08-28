/* autogenerated by Processing revision 1293 on 2024-08-28 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class elephant_1 extends PApplet {

int numElephants = 10;  // Number of elephants
float[] xPositions = new float[numElephants];
float[] yPositions = new float[numElephants];
float elephantSize = 40;  // Size of the elephants
boolean[] onGround = new boolean[numElephants];  // Track if elephant is on the ground
float holeX, holeY, holeSize;

public void setup() {
  /* size commented out by preprocessor */;
  // Initialize elephants' positions
  for (int i = 0; i < numElephants; i++) {
    xPositions[i] = random(width);
    yPositions[i] = random(-800, -40);  // Start off-screen
    onGround[i] = false;
  }
  // Hole parameters
  holeX = width / 2;
  holeY = height - 50;
  holeSize = 150;
}

public void draw() {
  background(135, 206, 235);  // Sky blue background

  // Draw the hole
  fill(0);
  ellipse(holeX, holeY, holeSize, holeSize / 2);

  // Draw and update elephants
  for (int i = 0; i < numElephants; i++) {
    fill(255, 182, 193);  // Pink elephants
    ellipse(xPositions[i], yPositions[i], elephantSize, elephantSize);

    if (!onGround[i]) {
      // Move elephants downward
      yPositions[i] += 2;

      // Check if elephant hits the ground
      if (yPositions[i] >= height - elephantSize / 2) {
        yPositions[i] = height - elephantSize / 2;
        onGround[i] = true;
      }
    } else {
      // Move elephants horizontally towards the hole
      if (xPositions[i] < holeX) {
        xPositions[i] += 2;
      } else if (xPositions[i] > holeX) {
        xPositions[i] -= 2;
      }

      // Check if elephant falls into the hole
      if (dist(xPositions[i], yPositions[i], holeX, holeY) < holeSize / 2) {
        xPositions[i] = random(width);
        yPositions[i] = random(-800, -40);
        onGround[i] = false;  // Reset elephant for another fall
      }
    }
  }
}


  public void settings() { size(800, 600); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "elephant_1" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

int numElephants = 10;  // Number of elephants
float[] xPositions = new float[numElephants];
float[] yPositions = new float[numElephants];
float elephantSize = 40;  // Size of the elephants
boolean[] onGround = new boolean[numElephants];  // Track if elephant is on the ground
float holeX, holeY, holeSize;

void setup() {
  size(800, 600);
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

void draw() {
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

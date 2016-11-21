package app;

import static org.lwjgl.opengl.GL11.*;


public class MyFloor {
	
	private float m = 50, n = 50; 
	private final float LENGTH = 100;
	

	public void draw() {
		drawFloor();
	}
	
	private void drawFloor() {
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

			for(int i = 0; i<m; i++) {
				for(int j = 0; j < n; j++) {
					
					glBegin(GL_TRIANGLE_STRIP);
						glColor3f(1, 1, 1);
						glVertex3f((LENGTH * (float) (i) / m) - LENGTH / 2,		0, 		(LENGTH * (float) (j) / n) - LENGTH / 2);
						glVertex3f((LENGTH * (float) (i) / m) - LENGTH / 2, 	0,	(LENGTH * (float) (j + 1) / n) - LENGTH / 2);
						glVertex3f((LENGTH * (float) (i + 1) / m) - LENGTH / 2, 0,	(LENGTH * (float) (j) / n) - LENGTH / 2);
						glVertex3f((LENGTH * (float) (i + 1) / m) - LENGTH / 2, 0,	(LENGTH * (float) (j + 1) / n) - LENGTH / 2);
					glEnd();
				}
			}
	}

}

package entities;

import static org.lwjgl.opengl.GL11.*;

public class Ground {
	private float length = 50, width = 50; 
	

	public void draw() {
		drawFloor();
	}
	
	private void drawFloor() {
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

			for(int z = 0; z<length; z++) {
				glBegin(GL_TRIANGLE_STRIP);
				for(int x = 0; x < width; x++) {
					glColor3f(1, 1, 1);
					glNormal3f(0, 1, 0);
					glVertex3f(x, 0.f, z);
					glNormal3f(0, 1, 0);
					glVertex3f(x, 0, z + 1);
				}
				glEnd();
			}
	}
}

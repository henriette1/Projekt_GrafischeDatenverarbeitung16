package app;

import static org.lwjgl.opengl.GL11.*;

public class MyKoordinatensystem {
	
	public void draw() {
		
		
		
		glBegin(GL_LINES);
			glColor3f(0, 1, 0); //Grün
			glVertex3f(-10, 0, 0);
			glVertex3f(10, 0, 0);
		glEnd();
		glBegin(GL_LINES);
			glColor3f(1, 0, 0); //Rot
			glVertex3f(0, -10, 0);
			glVertex3f(0, 10, 0);
		glEnd();
		glBegin(GL_LINES);
			glColor3f(0, 0, 1); //Blau
			glVertex3f(0, 0, -10);
			glVertex3f(0, 0, 10);
		glEnd();
		
	}

}

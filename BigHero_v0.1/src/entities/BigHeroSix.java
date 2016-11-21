package entities;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class BigHeroSix {
	
	public BigHeroSix() {
		
	}

	public void draw() {
		
		glRotatef(45, 0, 1, 0);
		glColor3f(0.f, 0.7f, 0.7f);
		drawSquare();
		glPushMatrix();
			glColor3f(0.7f, 0.f, 0.f);
			glRotatef(180, 1, 0, 0);
			drawSquare();
		glPopMatrix();
		glPushMatrix();
			glColor3f(0.f, 0.7f, 0.f);
			glRotatef(90, 1, 0, 0);
			drawSquare();
		glPopMatrix();
		glPushMatrix();
			glColor3f(0.f, 0.f, 0.7f);
			glRotatef(-90, 1, 0, 0);
			drawSquare();
		glPopMatrix();
		glPushMatrix();
			glColor3f(0.7f, 0.7f, 0.f);
			glRotatef(90, 0, 1, 0);
			drawSquare();
		glPopMatrix();
		glPushMatrix();
			glColor3f(0.7f, 0.f, 0.7f);
			glRotatef(-90, 0, 1, 0);
			drawSquare();
		glPopMatrix();
		
	}
	
	public void drawSquare() {
		glBegin(GL_TRIANGLE_FAN);
			glVertex3f(0, 0, .5f);
			
			glVertex3f(-.5f, -.5f, .5f);
			glVertex3f(.5f, -.5f, .5f);
			glVertex3f(.5f, .5f, .5f);
			glVertex3f(-.5f, .5f, .5f);
			glVertex3f(-.5f, -.5f, .5f);
		glEnd();
	}

}

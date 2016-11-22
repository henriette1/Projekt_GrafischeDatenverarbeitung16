package entities;

import static org.lwjgl.opengl.GL11.*;
public class PlayerModel {
	
	public PlayerModel() {
		
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
		glBegin(GL_POLYGON);
			glNormal3f(0, 0, 1);
			glVertex3f(.5f, -.5f, .5f);
			glNormal3f(0, 0, 1);
			glVertex3f(.5f, .5f, .5f);
			glNormal3f(0, 0, 1);
			glVertex3f(-.5f, .5f, .5f);
			glNormal3f(0, 0, 1);
			glVertex3f(-.5f, -.5f, .5f);
		glEnd();
	}
}

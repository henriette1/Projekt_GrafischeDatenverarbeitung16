package entities;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;

import engine.Utils;

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
					glColor3f(1.f, 1.f, 1.f);
					Vector3f firstPoint= new Vector3f(x, 0.f, z);
					Vector3f secondPoint= new Vector3f(x, 0.f, z + 1);
					
					Vector3f normalOne = Utils.calculateNormalVector(firstPoint, x, z, 0, 0);
					Vector3f normalTwo = Utils.calculateNormalVector(secondPoint, x, z, 0, 0);
					
					glNormal3f(normalOne.x, normalOne.y, normalOne.z);
					glVertex3f(x, 0.f, z);
					glNormal3f(normalTwo.x, normalTwo.y, normalTwo.z);
					glVertex3f(x, 0, z + 1);
					
				}
				glEnd();
			}
	}
}

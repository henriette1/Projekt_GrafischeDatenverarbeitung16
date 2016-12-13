package engine;

import static org.lwjgl.opengl.GL11.*;

public class Menu {

	public void initMenu() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-5, 5, -5, 5, -5, 5);
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		glEnable(GL_NORMALIZE);
		glShadeModel(GL_SMOOTH);
		
		glEnable(GL_LIGHTING);
	}
	
	public void renderMenu(){
		float []params = {1.f, 1.f, 1.f, 1.f};
		int steps = 10;
		float j_min = -3, i_min = -3, i_max = 2, j_max = i_max-1;
		glLoadIdentity();
		glPushMatrix();
			glPolygonMode(GL_FRONT, GL_LINE);
			glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, params);
			
			for(float i = -steps; i<steps; i++) {
				glBegin(GL_TRIANGLE_STRIP);
				for(float j = -(steps-1); j<steps-1; j++) {
					glNormal3f(0, 0, 1);
					glVertex3f(i/10, j/10, 0);
					if(i>=i_max-1) break;
					glNormal3f(0, 0, 1);
					glVertex3f((i+1), (j+((j+1)-j)/2), 0);
				}
				glEnd();
				i_min -= (i_min - (i_min+1))/2;
				i_max -= (i_max - (i_max-1))/2;
			}
				
		glPopMatrix();
		
	}
}

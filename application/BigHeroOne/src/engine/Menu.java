package engine;

import static org.lwjgl.opengl.GL11.*;

public class Menu {
	
	private Lightning spotlight = new Lightning(GL_LIGHT5);

	public void initMenu() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-5, 5, -5, 5, -5, 5);
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		glEnable(GL_NORMALIZE);
		glShadeModel(GL_SMOOTH);
		
		initLighting();
	}
	
	public void renderMenu(){
		float []params = {1.f, 1.f, 1.f, 1.f};
		float m = 200, n = 201;
		float height = 4, width = 4;
		float y_min = 0, x_min = 0, x_max = width, y_max = height;
		float delta_x = (x_max - x_min)/m;
		float delta_y = (y_max - y_min)/n;
		
		glLoadIdentity();
		glPushMatrix();
		
		for(float i = 0; i < m; i++) {

			glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, params);
			glBegin(GL_TRIANGLE_STRIP);
			
				for(float j = 0; j < n; j++) {
					glPolygonMode(GL_FRONT, GL_LINE);
					glNormal3f(0, 0, 1);
					glVertex3f(i*delta_x/2+2*j*delta_x/2 -width/2, i*delta_y-height/2, 0);
					
					if(j >= n-1) break;
					glVertex3f(i*delta_x/2+((2*j)+1)*delta_x/2 -width/2, (i+1)*delta_y-height/2, 0);
				}
				n-=1;
			glEnd();	
		}
				
		glPopMatrix();
		
	}
	
	private void initLighting() {
	
		float lmodel_ambient[] = { 1f, 1f, 1f, 1.0f };
		// Hintergrundbeleuchtung definieren
		glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
		
		float []spotlight_position = {0,0,-4,1};
		float []spotlight_direction = {0,0,1,1};
		
		float spotlight_diff_spec[] = { 1f, 0.8f, 1f, 1.0f };
		
		spotlight.setDiffAndSpek(spotlight_diff_spec);
		spotlight.setPosition(spotlight_position);
		spotlight.setDirection(spotlight_direction);
		spotlight.setCutoff(20.f);
		
		
		glEnable(GL_LIGHTING);
		spotlight.turnLightOn();
	}
}

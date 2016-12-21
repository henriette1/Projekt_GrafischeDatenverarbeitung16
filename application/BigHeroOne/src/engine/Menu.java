package engine;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;

import engine.Materials;

public class Menu {
	
	private Lightning spotlight = new Lightning(GL_LIGHT5);

	/*
	 * initializes our Menu and therfore the camera position and perspective
	 */
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
	
	/*
	 * renders the objects which our menu contains in every frame/loop
	 */
	public void renderMenu(){
		glPushMatrix();
			drawTriangle();
			if((Math.pow(currentCursorPosX-windowWidth/2., 2) + Math.pow(currentCursorPosY-windowWidth/2., 2)) <= Math.pow(windowWidth/3.9, 2)){
        		increaseWinkel();
        	}
			glRotatef(winkel, 0, 1, -1);
			drawTorus();
			glLoadIdentity();
			glRotatef(winkel, -1, 0, 1);
			drawTorus();
		glPopMatrix();
		
		if((Math.pow(currentCursorPosX-windowWidth/2., 2) + Math.pow(currentCursorPosY-windowWidth/2., 2)) > Math.pow(windowWidth/3.9, 2)){
			winkel = 0;
		}
	}
	
	/*
	 * draws the triangle which lets us start the game
	 */
	private void drawTriangle(){
		float []params = {1.f, 1.f, 1.f, 1.f};
		float m = 200, n = 201;
		float height = 4, width = 4;
		float y_min = 0, x_min = 0, x_max = width, y_max = height;
		float delta_x = (x_max - x_min)/m;
		float delta_y = (y_max - y_min)/n;
		
		glLoadIdentity();
		glPushMatrix();
		glRotatef(-90, 0, 0, 1);
//		glRotatef(30, 1,1,0);
		glTranslatef(0,0.5f,0);

		for(float i = 0; i < m; i++) {

			glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, params);
			glBegin(GL_TRIANGLE_STRIP);
			
				for(float j = 0; j < n; j++) {
					glPolygonMode(GL_FRONT, GL_LINE);
					glNormal3f(0, 0, 1);
					Materials.materialRuby();
					
						glVertex3f(i*delta_x/2+2*j*delta_x/2 -width/2, i*delta_y-height/2, -j);
						
						if(j >= n-1) break;
						glVertex3f(i*delta_x/2+((2*j)+1)*delta_x/2 -width/2, (i+1)*delta_y-height/2, 0);		
				}
				n-=1;
			glEnd();	
		}
				
		glPopMatrix();
	}
	
	float aussenR = 3.f;													//Abstand des Innenkreises zum Ursprung 
	float innenR = .4f;		
	private float winkel = 1.f;
	private int windowWidth;
	private double currentCursorPosX;
	private double currentCursorPosY;
	
	/*
	 * draws the torus surrounding our triangle
	 */
	private void drawTorus()
	{	
		float []params = {1.f, 1.f, 1.f, 1.f};
		int mTorus = 100;														//u-Schritte
		int nTorus = 100;														//v-Schritte
		float u_iTorus , u_i_1Torus , v_jTorus , v_j_1Torus;									//Eckpunkte einer Facette
		float 	uaTorus = -(float) (2*Math.PI), ueTorus = (float) (2*Math.PI),		//Anfang und Ende des u-Bereichs
				vaTorus = -(float) (2*Math.PI), veTorus = (float) (2*Math.PI);		//Anfang und Ende des v-Bereichs
		float deltaUTorus = (float)(ueTorus-uaTorus)/mTorus;									//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
		float deltaVTorus = (float)(veTorus-vaTorus)/nTorus;									//wie gro� ein einzelner Teilschritt sein muss in v-Richtung
		float winkel = 0;
	
		
//		glLoadIdentity();
		glPushMatrix();
		glRotatef(winkel, 1, 0, -1);

		for(int i = 0; i<mTorus; i++){
			glMaterialfv(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, params);

			for(int j = 0; j<nTorus; j++){
				glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
			
			//Eckpunkte einer Facette deklarieren	
			u_iTorus 	= uaTorus + i * deltaUTorus;
			u_i_1Torus 	= u_iTorus + deltaUTorus;
			v_jTorus 	= vaTorus + j * deltaVTorus;
			v_j_1Torus 	= v_jTorus + deltaVTorus;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xTorus(u_iTorus,v_jTorus), yTorus(u_iTorus,v_jTorus), zTorus(u_iTorus, v_jTorus)),
					new Vector3f(xTorus(u_i_1Torus,v_jTorus), yTorus(u_i_1Torus,v_jTorus), zTorus(u_i_1Torus, v_jTorus)),
					new Vector3f(xTorus(u_iTorus,v_j_1Torus), yTorus(u_iTorus,v_j_1Torus), zTorus(u_iTorus, v_j_1Torus))).mul(-1);
			
			//Erstellung einer Facette
			glBegin(GL_POLYGON);
				glNormal3f(normal.x, normal.y, normal.z);
				Materials.materialChrom();

				glVertex3f(xTorus(u_iTorus,v_jTorus),		yTorus(u_iTorus,v_jTorus), 		zTorus(u_iTorus, v_jTorus));
				glVertex3f(xTorus(u_i_1Torus,v_jTorus),		yTorus(u_i_1Torus,v_jTorus), 	zTorus(u_i_1Torus, v_jTorus));
				glVertex3f(xTorus(u_i_1Torus,v_j_1Torus),	yTorus(u_i_1Torus,v_j_1Torus),	zTorus(u_i_1Torus, v_j_1Torus));
				glVertex3f(xTorus(u_iTorus,v_j_1Torus),		yTorus(u_iTorus,v_j_1Torus), 	zTorus(u_iTorus, v_j_1Torus));
			glEnd();
			}
		}
	}
	
	private float xTorus(float u, float v){
			
			return (float)((aussenR + innenR*Math.cos(u))*Math.cos(v));
	}
	
	private float yTorus(float u, float v){
		
			return (float)((aussenR + innenR*Math.cos(u))*Math.sin(v));
	}	
	
	private float zTorus(float u, float v){
		
			return (float)(innenR*Math.sin(u));
	}
	
	private void initLighting() {
	
		float lmodel_ambient[] = { 1.f, 1.f, 1.f, 1.0f };
		// Hintergrundbeleuchtung definieren
		glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
		
		float []spotlight_position = {0,0,10,1};
		float []spotlight_direction = {0,0,-1,1};
		
		float spotlight_diff_spec[] = { 1.f, 0.8f, 1.f, 1.0f };
		
		spotlight.setDiffAndSpek(spotlight_diff_spec);
		spotlight.setPosition(spotlight_position);
		spotlight.setDirection(spotlight_direction);
		spotlight.setCutoff(20.f);
		
		
		glEnable(GL_LIGHTING);
		spotlight.turnLightOn();
	}
	
	public void turnOff(){
		spotlight.turnLightOff();
	}
	
	public void increaseWinkel(){
			winkel=(winkel+20.f)%360;
	}

	public void setRotationParam(int width, double currentXpos, double currentYpos) {
		this.windowWidth = width;
		this.currentCursorPosX = currentXpos;
		this.currentCursorPosY = currentYpos;
	}
}

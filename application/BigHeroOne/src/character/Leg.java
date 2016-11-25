package character;

//Imports
import static org.lwjgl.opengl.GL11.*;

public class Leg {
	
	
	public void drawLeg()
	{	
		glPushMatrix();
			doLeg();
			glTranslatef(0, -intervalLeg, 0);
			doFeet();
		glPopMatrix();
	}
	
	// private Variablen
	int mLeg = 15;															//u-Schritte
	int nLeg = 15;															//v-Schritte
	float intervalLeg = 2;													//u-Abschnitt
	float u_iLeg , u_i_1Leg , v_jLeg , v_j_1Leg;							//Eckpunkte einer Facette
	float 	uaLeg = -intervalLeg, ueLeg = (float) intervalLeg ,				//Anfang und Ende des u-Bereichs
			vaLeg = 0, veLeg = (float)(2*Math.PI);							//Anfang und Ende des v-Bereichs
	float deltaULeg = (float)(ueLeg-uaLeg)/mLeg;							//wie groﬂ ein einzelner Teilschritt sein muss in u-Richtung
	float deltaVLeg = (float)(veLeg-vaLeg)/nLeg;							//wie groﬂ ein einzelner Teilschritt sein muss in v-Richtung
	float rLeg = 1;															//Radius 
	
	private void doLeg()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glColor3f(1,1,1);
		for(int i = 0; i<mLeg; i++){
			for(int j = 0; j<nLeg; j++){
			
			//Eckpunkte einer Facette deklarieren	
			u_iLeg 		= uaLeg + i * deltaULeg;
			u_i_1Leg 	= u_iLeg + deltaULeg;
			v_jLeg 		= vaLeg + j * deltaVLeg;
			v_j_1Leg 	= v_jLeg + deltaVLeg;
			
			//Erstellung einer Facette
			glBegin(GL_TRIANGLE_STRIP);	
				glVertex3f(xLeg(u_iLeg,v_jLeg),				yLeg(u_iLeg,v_jLeg), 			zLeg(u_iLeg, v_jLeg));
				glVertex3f(xLeg(u_i_1Leg,v_jLeg),			yLeg(u_i_1Leg,v_jLeg), 			zLeg(u_i_1Leg, v_jLeg));
				glVertex3f(xLeg(u_iLeg,v_j_1Leg),			yLeg(u_iLeg,v_j_1Leg), 			zLeg(u_iLeg, v_j_1Leg));
				glVertex3f(xLeg(u_i_1Leg,v_j_1Leg),			yLeg(u_i_1Leg,v_j_1Leg),		zLeg(u_i_1Leg, v_j_1Leg));
			glEnd();
			}
		}
	}
	
	private float xLeg(float u, float v){
			
		return (float)(((0.1*u*u)-rLeg)*Math.sin(v)) ;
	
	}
	
	private float yLeg(float u, float v){
		
		return (float)(u);

	}	
	
	private float zLeg(float u, float v){
		
			return (float)(((0.1*u*u)-rLeg)*Math.cos(v)) ;
	}
	
	
	int mFeet = 15;																	//u-Schritte
	int nFeet = 15;																	//v-Schritte
	float u_iFeet , u_i_1Feet , v_jFeet , v_j_1Feet;								//Eckpunkte einer Facette
	float 	uaFeet = (float) 0, ueFeet = (float)(Math.PI),								//Anfang und Ende des u-Bereichs
			vaFeet = 0, veFeet = (float)(Math.PI);									//Anfang und Ende des v-Bereichs
	float deltaUFeet = (float)(ueFeet-uaFeet)/mFeet;								//wie groﬂ ein einzelner Teilschritt sein muss in u-Richtung
	float deltaVFeet = (float)(veFeet-vaFeet)/nFeet;								//wie groﬂ ein einzelner Teilschritt sein muss in v-Richtung
	float rFeet = zLeg(ueLeg, vaLeg);												//Radius 
	
	
	private void doFeet()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glLineWidth(1);
		glColor3f(1,0,1);

		for(int i = 0; i<mFeet; i++){
			for(int j = 0; j<nFeet; j++){
			
			//Eckpunkte einer Facette deklarieren	
			u_iFeet 	= uaFeet + i * deltaUFeet;
			u_i_1Feet 	= u_iFeet + deltaUFeet;
			v_jFeet 	= vaFeet + j * deltaVFeet;
			v_j_1Feet 	= v_jFeet + deltaVFeet;
			
			//Erstellung einer Facette
			glBegin(GL_TRIANGLE_STRIP);
				glVertex3f(xFeet(u_iFeet,v_jFeet),			yFeet(u_iFeet,v_jFeet), 			zFeet(u_iFeet, v_jFeet));
				glVertex3f(xFeet(u_i_1Feet,v_jFeet),			yFeet(u_i_1Feet,v_jFeet), 		zFeet(u_i_1Feet, v_jFeet));
				glVertex3f(xFeet(u_iFeet,v_j_1Feet),			yFeet(u_iFeet,v_j_1Feet), 		zFeet(u_iFeet, v_j_1Feet));
				glVertex3f(xFeet(u_i_1Feet,v_j_1Feet),		yFeet(u_i_1Feet,v_j_1Feet),		zFeet(u_i_1Feet, v_j_1Feet));
			glEnd();
			}
		}
	}
	
	private float xFeet(float u, float v){
		
		return (float)(rFeet*Math.sin(u)*Math.cos(v));
//		return (float)(feetFunction(u)*Math.sin(v));
	}
	
	private float yFeet(float u, float v){
		
		return (float) (rFeet*Math.sin(u)*Math.sin(v));
//		return (float) feetFunction(u);
	}	
	
	private float zFeet(float u, float v){
		
		return (float)(rFeet*Math.cos(u));
//		return (float)(feetFunction(u)*Math.cos(v));
	}
	
	private float feetFunction(float u){
//		return ((float)(((1./3)*u*u)+ 0.38));
		return ((float)(Math.sqrt(-3*u-1.14)));
	}
	
	public float getHeight(){
		return (2*intervalLeg+rFeet);
	}
	
	
}

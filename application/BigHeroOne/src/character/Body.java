package character;

//Imports
import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;

import engine.Utils;

public class Body {
	
	public void drawBody()
	{	
		glPushMatrix();
			glTranslatef(0, bLower, 0);
			doLowerBody();
			glTranslatef(0, aLower, 0);
			doMiddleBody();
			glTranslatef(0, (ueMiddleBody - uaMiddleBody)/2 , 0);
			doUpperBody();
		glPopMatrix();
	}

	int mLowerBody = 15;																	//u-Schritte
	int nLowerBody = 15;																//v-Schritte
	float aLower = 3.f;
	float bLower = 2.f;
	float cLower = 3.f;
	float u_iLowerBody , u_i_1LowerBody , v_jLowerBody , v_j_1LowerBody;								//Eckpunkte einer Facette
	float 	uaLowerBody = 0, ueLowerBody = (float) (2*Math.PI) ,								//Anfang und Ende des u-Bereichs
			vaLowerBody = (float)(-Math.PI/2), veLowerBody = (float)0;									//Anfang und Ende des v-Bereichs
	float deltaULowerBody = (float)(ueLowerBody-uaLowerBody)/mLowerBody;								//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
	float deltaVLowerBody = (float)(veLowerBody-vaLowerBody)/nLowerBody;								//wie gro� ein einzelner Teilschritt sein muss in v-Richtung
	float rLowerBody = 5.f;																//Radius 
	
	
	private void doLowerBody()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		for(int i = 0; i<mLowerBody; i++){
			for(int j = 0; j<nLowerBody; j++){
			
			//Eckpunkte einer Facette deklarieren	
			u_iLowerBody 		= uaLowerBody + i * deltaULowerBody;
			u_i_1LowerBody 	= u_iLowerBody + deltaULowerBody;
			v_jLowerBody 		= vaLowerBody + j * deltaVLowerBody;
			v_j_1LowerBody 	= v_jLowerBody + deltaVLowerBody;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xLowerBody(u_iLowerBody,v_jLowerBody), yLowerBody(u_iLowerBody,v_jLowerBody), zLowerBody(u_iLowerBody, v_jLowerBody)),
					new Vector3f(xLowerBody(u_i_1LowerBody,v_jLowerBody), yLowerBody(u_i_1LowerBody,v_jLowerBody), zLowerBody(u_i_1LowerBody, v_jLowerBody)),
					new Vector3f(xLowerBody(u_iLowerBody,v_j_1LowerBody), yLowerBody(u_iLowerBody,v_j_1LowerBody), zLowerBody(u_iLowerBody, v_j_1LowerBody))).mul(1);
			
			//Erstellung einer Facette
			glBegin(GL_TRIANGLE_STRIP);
				glNormal3f(normal.x, normal.y, normal.z);
				glVertex3f(xLowerBody(u_iLowerBody,v_jLowerBody),			yLowerBody(u_iLowerBody,v_jLowerBody), 			zLowerBody(u_iLowerBody, v_jLowerBody));
				glVertex3f(xLowerBody(u_i_1LowerBody,v_jLowerBody),			yLowerBody(u_i_1LowerBody,v_jLowerBody), 		zLowerBody(u_i_1LowerBody, v_jLowerBody));
				glVertex3f(xLowerBody(u_iLowerBody,v_j_1LowerBody),			yLowerBody(u_iLowerBody,v_j_1LowerBody), 		zLowerBody(u_iLowerBody, v_j_1LowerBody));
				glVertex3f(xLowerBody(u_i_1LowerBody,v_j_1LowerBody),		yLowerBody(u_i_1LowerBody,v_j_1LowerBody),		zLowerBody(u_i_1LowerBody, v_j_1LowerBody));
			glEnd();
			}
		}
	}
	
	private float xLowerBody(float u, float v){
		
		return (float)(aLower* Math.cos(u)*Math.cos(v));	
	}
	
	private float yLowerBody(float u, float v){
		
		return (float)(bLower*Math.sin(v));
	}	
	
	private float zLowerBody(float u, float v){
		
		return (float)(cLower* Math.sin(u)*Math.cos(v));
	}
		
	
	int mUpperBody = 15;																	//u-Schritte
	int nUpperBody = 15;																//v-Schritte
	float aUpper = 2.f;
	float bUpper = 1.f;
	float cUpper = 2.f;
	float u_iUpperBody , u_i_1UpperBody , v_jUpperBody , v_j_1UpperBody;								//Eckpunkte einer Facette
	float 	uaUpperBody = 0, ueUpperBody = (float) (2*Math.PI) ,								//Anfang und Ende des u-Bereichs
			vaUpperBody = (float)0, veUpperBody = (float)(Math.PI/2);									//Anfang und Ende des v-Bereichs
	float deltaUUpperBody = (float)(ueUpperBody-uaUpperBody)/mUpperBody;								//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
	float deltaVUpperBody = (float)(veUpperBody-vaUpperBody)/nUpperBody;								//wie gro� ein einzelner Teilschritt sein muss in v-Richtung
	float rUpperBody = 4.f;												//Radius 
	
	
	private void doUpperBody()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		
		for(int i = 0; i<mUpperBody; i++){
			for(int j = 0; j<nUpperBody; j++){
			
			//Eckpunkte einer Facette deklarieren	
			u_iUpperBody 		= uaUpperBody + i * deltaUUpperBody;
			u_i_1UpperBody 	= u_iUpperBody + deltaUUpperBody;
			v_jUpperBody 		= vaUpperBody + j * deltaVUpperBody;
			v_j_1UpperBody 	= v_jUpperBody + deltaVUpperBody;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xUpperBody(u_iUpperBody,v_jUpperBody), yUpperBody(u_iUpperBody,v_jUpperBody), zUpperBody(u_iUpperBody, v_jUpperBody)),
					new Vector3f(xUpperBody(u_i_1UpperBody,v_jUpperBody), yUpperBody(u_i_1UpperBody,v_jUpperBody), zUpperBody(u_i_1UpperBody, v_jUpperBody)),
					new Vector3f(xUpperBody(u_iUpperBody,v_j_1UpperBody), yUpperBody(u_iUpperBody,v_j_1UpperBody), zUpperBody(u_iUpperBody, v_j_1UpperBody))).mul(-1);
			
			//Erstellung einer Facette
			glBegin(GL_TRIANGLE_STRIP);
				glNormal3f(normal.x, normal.y, normal.z);
				glVertex3f(xUpperBody(u_iUpperBody,v_jUpperBody),			yUpperBody(u_iUpperBody,v_jUpperBody), 			zUpperBody(u_iUpperBody, v_jUpperBody));
				glVertex3f(xUpperBody(u_i_1UpperBody,v_jUpperBody),			yUpperBody(u_i_1UpperBody,v_jUpperBody), 		zUpperBody(u_i_1UpperBody, v_jUpperBody));
				glVertex3f(xUpperBody(u_iUpperBody,v_j_1UpperBody),			yUpperBody(u_iUpperBody,v_j_1UpperBody), 		zUpperBody(u_iUpperBody, v_j_1UpperBody));
				glVertex3f(xUpperBody(u_i_1UpperBody,v_j_1UpperBody),		yUpperBody(u_i_1UpperBody,v_j_1UpperBody),		zUpperBody(u_i_1UpperBody, v_j_1UpperBody));
			glEnd();
			}
		}
	}
	
	private float xUpperBody(float u, float v){
		
		return (float)(aUpper* Math.cos(u)*Math.cos(v));	
	}
	
	private float yUpperBody(float u, float v){
		
		return (float)(bUpper*Math.sin(v));
	}	
	
	private float zUpperBody(float u, float v){
		
		return (float)(cUpper* Math.sin(u)*Math.cos(v));
	}
	
	// private Variablen
	int mMiddleBody = 30;															//u-Schritte
	int nMiddleBody = 30;															//v-Schritte
	float intervalMiddleBody = 3;													//u-Abschnitt
	float u_iMiddleBody , u_i_1MiddleBody , v_jMiddleBody , v_j_1MiddleBody;							//Eckpunkte einer Facette
	float 	uaMiddleBody = -intervalMiddleBody, ueMiddleBody = (float) intervalMiddleBody ,				//Anfang und Ende des u-Bereichs
			vaMiddleBody = 0, veMiddleBody = (float)(2*Math.PI);							//Anfang und Ende des v-Bereichs
	float deltaUMiddleBody = (float)(ueMiddleBody-uaMiddleBody)/mMiddleBody;							//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
	float deltaVMiddleBody = (float)(veMiddleBody-vaMiddleBody)/nMiddleBody;							//wie gro� ein einzelner Teilschritt sein muss in v-Richtung
	float rMiddleBody = 2.5f;															//Radius 

	private void doMiddleBody()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		for(int i = 0; i<mMiddleBody; i++){
			for(int j = 0; j<nMiddleBody; j++){
					
			//Eckpunkte einer Facette deklarieren	
			u_iMiddleBody 		= uaMiddleBody + i * deltaUMiddleBody;
			u_i_1MiddleBody 	= u_iMiddleBody + deltaUMiddleBody;
			v_jMiddleBody 		= vaMiddleBody + j * deltaVMiddleBody;
			v_j_1MiddleBody 	= v_jMiddleBody + deltaVMiddleBody;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xMiddleBody(u_iMiddleBody,v_jMiddleBody), yMiddleBody(u_iMiddleBody,v_jMiddleBody), zMiddleBody(u_iMiddleBody, v_jMiddleBody)),
					new Vector3f(xMiddleBody(u_i_1MiddleBody,v_jMiddleBody), yMiddleBody(u_i_1MiddleBody,v_jMiddleBody), zMiddleBody(u_i_1MiddleBody, v_jMiddleBody)),
					new Vector3f(xMiddleBody(u_iMiddleBody,v_j_1MiddleBody), yMiddleBody(u_iMiddleBody,v_j_1MiddleBody), zMiddleBody(u_iMiddleBody, v_j_1MiddleBody))).mul(1);
					
			//Erstellung einer Facette
			glBegin(GL_TRIANGLE_STRIP);	
				glNormal3f(normal.x, normal.y, normal.z);
				glVertex3f(xMiddleBody(u_iMiddleBody,v_jMiddleBody),				yMiddleBody(u_iMiddleBody,v_jMiddleBody), 			zMiddleBody(u_iMiddleBody, v_jMiddleBody));
				glVertex3f(xMiddleBody(u_i_1MiddleBody,v_jMiddleBody),			yMiddleBody(u_i_1MiddleBody,v_jMiddleBody), 			zMiddleBody(u_i_1MiddleBody, v_jMiddleBody));
				glVertex3f(xMiddleBody(u_iMiddleBody,v_j_1MiddleBody),			yMiddleBody(u_iMiddleBody,v_j_1MiddleBody), 			zMiddleBody(u_iMiddleBody, v_j_1MiddleBody));
				glVertex3f(xMiddleBody(u_i_1MiddleBody,v_j_1MiddleBody),			yMiddleBody(u_i_1MiddleBody,v_j_1MiddleBody),		zMiddleBody(u_i_1MiddleBody, v_j_1MiddleBody));
			glEnd();
			}
		}
	}
			
	private float xMiddleBody(float u, float v){
					
		return (float)((middleBodyFunction(u)-rMiddleBody)*Math.sin(v)) ;
			
	}
			
	private float yMiddleBody(float u, float v){
				
		return (float)(u);

	}	
			
	private float zMiddleBody(float u, float v){
				
			return (float)((middleBodyFunction(u)-rMiddleBody)*Math.cos(v)) ;
	}
	
	private float middleBodyFunction(float u){
		return (float)((-0.016*u*u*u) + (0.3*u));		
	}
	
	public float getHeight(){
		return (2*intervalMiddleBody + bUpper + bLower);
	}
}

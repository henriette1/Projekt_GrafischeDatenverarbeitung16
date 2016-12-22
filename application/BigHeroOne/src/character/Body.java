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

	int mLowerBody = 60;																//u-Steps
	int nLowerBody = 60;																//v-Steps
	float aLower = 2.9f;																//length in x-Direction
	float bLower = 2.f;																	//length in y-Direction
	float cLower = 2.9f;																//length in z-Direction
	float u_iLowerBody , u_i_1LowerBody , v_jLowerBody , v_j_1LowerBody;				//corners of a facet
	float 	uaLowerBody = 0, ueLowerBody = (float) (2*Math.PI) ,						//Start and End of the u-Area
			vaLowerBody = (float)(-Math.PI/2), veLowerBody = (float)0;					//Start and End of the v-Area
	float deltaULowerBody = (float)(ueLowerBody-uaLowerBody)/mLowerBody;				//how big a single step is in u-Direction
	float deltaVLowerBody = (float)(veLowerBody-vaLowerBody)/nLowerBody;				//how big a single step is in v-Direction	
	
	private void doLowerBody()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		for(int i = 0; i<mLowerBody; i++){
			for(int j = 0; j<nLowerBody; j++){
			
			//declares corners of a facet	
			u_iLowerBody 		= uaLowerBody + i * deltaULowerBody;
			u_i_1LowerBody 	= u_iLowerBody + deltaULowerBody;
			v_jLowerBody 		= vaLowerBody + j * deltaVLowerBody;
			v_j_1LowerBody 	= v_jLowerBody + deltaVLowerBody;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xLowerBody(u_iLowerBody,v_jLowerBody), yLowerBody(u_iLowerBody,v_jLowerBody), zLowerBody(u_iLowerBody, v_jLowerBody)),
					new Vector3f(xLowerBody(u_i_1LowerBody,v_jLowerBody), yLowerBody(u_i_1LowerBody,v_jLowerBody), zLowerBody(u_i_1LowerBody, v_jLowerBody)),
					new Vector3f(xLowerBody(u_iLowerBody,v_j_1LowerBody), yLowerBody(u_iLowerBody,v_j_1LowerBody), zLowerBody(u_iLowerBody, v_j_1LowerBody)));
			
			//builds a facet
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
		
	
	int mUpperBody = 60;															//u-Steps
	int nUpperBody = 60;															//v-Steps
	float aUpper = 2.f;																//x-Direction
	float bUpper = 1.f;																//y-Direction
	float cUpper = 2.f;																//z-Direction
	float u_iUpperBody , u_i_1UpperBody , v_jUpperBody , v_j_1UpperBody;			//corners of a facet
	float 	uaUpperBody = 0, ueUpperBody = (float) (2*Math.PI) ,					//Start and End of the u-Area
			vaUpperBody = (float)0, veUpperBody = (float)(Math.PI/2);				//Start and End of the v-Area
	float deltaUUpperBody = (float)(ueUpperBody-uaUpperBody)/mUpperBody;			//how big a single step is in u-Direction
	float deltaVUpperBody = (float)(veUpperBody-vaUpperBody)/nUpperBody;			//how big a single step is in v-Direction
	float rUpperBody = 4.f;															//Radius of the ellipse
	
	
	private void doUpperBody()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		
		for(int i = 0; i<mUpperBody; i++){
			for(int j = 0; j<nUpperBody; j++){
			
			//declares corners of a facet
			u_iUpperBody 		= uaUpperBody + i * deltaUUpperBody;
			u_i_1UpperBody 	= u_iUpperBody + deltaUUpperBody;
			v_jUpperBody 		= vaUpperBody + j * deltaVUpperBody;
			v_j_1UpperBody 	= v_jUpperBody + deltaVUpperBody;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xUpperBody(u_iUpperBody,v_jUpperBody), yUpperBody(u_iUpperBody,v_jUpperBody), zUpperBody(u_iUpperBody, v_jUpperBody)),
					new Vector3f(xUpperBody(u_i_1UpperBody,v_jUpperBody), yUpperBody(u_i_1UpperBody,v_jUpperBody), zUpperBody(u_i_1UpperBody, v_jUpperBody)),
					new Vector3f(xUpperBody(u_iUpperBody,v_j_1UpperBody), yUpperBody(u_iUpperBody,v_j_1UpperBody), zUpperBody(u_iUpperBody, v_j_1UpperBody)));
			
			//builds a facet
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
	int mMiddleBody = 60;																	//u-Steps
	int nMiddleBody = 60;																	//v-Steps
	float intervalMiddleBody = 3.f;															//u-Interval
	float u_iMiddleBody , u_i_1MiddleBody , v_jMiddleBody , v_j_1MiddleBody;				//Corners of a facet
	float 	uaMiddleBody = -intervalMiddleBody, ueMiddleBody = (float) intervalMiddleBody,	//Start and End of the u-Area
			vaMiddleBody = 0, veMiddleBody = (float)(2*Math.PI);							//Start and End of the v-Area
	float deltaUMiddleBody = (float)(ueMiddleBody-uaMiddleBody)/mMiddleBody;				//how big a single step is in u-Direction
	float deltaVMiddleBody = (float)(veMiddleBody-vaMiddleBody)/nMiddleBody;				//how big a single step is in v-Direction
	float rMiddleBody = 2.5f;																//Radius for the body of rotation

	private void doMiddleBody()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		for(int i = 0; i<mMiddleBody; i++){
			for(int j = 0; j<nMiddleBody; j++){
					
			//declares corners of a facet	
			u_iMiddleBody 		= uaMiddleBody + i * deltaUMiddleBody;
			u_i_1MiddleBody 	= u_iMiddleBody + deltaUMiddleBody;
			v_jMiddleBody 		= vaMiddleBody + j * deltaVMiddleBody;
			v_j_1MiddleBody 	= v_jMiddleBody + deltaVMiddleBody;
			
			Vector3f normal1 = Utils.normalVector(
					new Vector3f(xMiddleBody(u_iMiddleBody,v_jMiddleBody), yMiddleBody(u_iMiddleBody,v_jMiddleBody), zMiddleBody(u_iMiddleBody, v_jMiddleBody)),
					new Vector3f(xMiddleBody(u_i_1MiddleBody,v_jMiddleBody), yMiddleBody(u_i_1MiddleBody,v_jMiddleBody), zMiddleBody(u_i_1MiddleBody, v_jMiddleBody)),
					new Vector3f(xMiddleBody(u_iMiddleBody,v_j_1MiddleBody), yMiddleBody(u_iMiddleBody,v_j_1MiddleBody), zMiddleBody(u_iMiddleBody, v_j_1MiddleBody)));
			
			//Erstellung einer Facette
			glBegin(GL_TRIANGLE_STRIP);	
				glNormal3f(normal1.x, normal1.y, normal1.z);
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

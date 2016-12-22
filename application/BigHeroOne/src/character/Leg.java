package character;

//Imports
import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;

import engine.Utils;

public class Leg {	
	
	public void drawLeg()
	{	
		glPushMatrix();
			doLeg();
			glTranslatef(0, -intervalLeg, 0);
			doFeet();
		glPopMatrix();
	}
	
	// private Variables
	int mLeg = 60;															//u-Steps
	int nLeg = 60;															//v-Steps
	float intervalLeg = 2;													//u-Interval
	float u_iLeg , u_i_1Leg , v_jLeg , v_j_1Leg;							//corners of a facet
	float 	uaLeg = -intervalLeg, ueLeg = (float) intervalLeg ,				//Start and End of the u-Area
			vaLeg = 0, veLeg = (float)(2*Math.PI);							//Start and End of the v-Area
	float deltaULeg = (float)(ueLeg-uaLeg)/mLeg;							//how big a single step is in u-Direction
	float deltaVLeg = (float)(veLeg-vaLeg)/nLeg;							//how big a single step is in v-Direction
	float rLeg = 1;															//Radius of the body of rotation
	
	private void doLeg()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		for(int i = 0; i<mLeg; i++){
			for(int j = 0; j<nLeg; j++){
			
			//declares corners of a facet	
			u_iLeg 		= uaLeg + i * deltaULeg;
			u_i_1Leg 	= u_iLeg + deltaULeg;
			v_jLeg 		= vaLeg + j * deltaVLeg;
			v_j_1Leg 	= v_jLeg + deltaVLeg;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xLeg(u_iLeg,v_jLeg), yLeg(u_iLeg,v_jLeg), zLeg(u_iLeg, v_jLeg)),
					new Vector3f(xLeg(u_i_1Leg,v_jLeg), yLeg(u_i_1Leg,v_jLeg), zLeg(u_i_1Leg, v_jLeg)),
					new Vector3f(xLeg(u_iLeg,v_j_1Leg), yLeg(u_iLeg,v_j_1Leg), zLeg(u_iLeg, v_j_1Leg)));
			
			//builds a facet
			glBegin(GL_TRIANGLE_STRIP);
				glNormal3f(normal.x, normal.y, normal.z);
				glVertex3f(xLeg(u_iLeg,v_jLeg),				yLeg(u_iLeg,v_jLeg), 			zLeg(u_iLeg, v_jLeg));
				glVertex3f(xLeg(u_i_1Leg,v_jLeg),			yLeg(u_i_1Leg,v_jLeg), 			zLeg(u_i_1Leg, v_jLeg));
				glVertex3f(xLeg(u_iLeg,v_j_1Leg),			yLeg(u_iLeg,v_j_1Leg), 			zLeg(u_iLeg, v_j_1Leg));
				glVertex3f(xLeg(u_i_1Leg,v_j_1Leg),			yLeg(u_i_1Leg,v_j_1Leg),		zLeg(u_i_1Leg, v_j_1Leg));
			glEnd();
			}
		}
	}
	
	private float xLeg(float u, float v){			
		return (float)(((0.1*u*u)-rLeg)*Math.sin(v));	
	}
	
	private float yLeg(float u, float v){		
		return (float)(u);
	}	
	
	private float zLeg(float u, float v){		
			return (float)(((0.1*u*u)-rLeg)*Math.cos(v));
	}
	
	
	int mFeet = 60;																	//u-Steps
	int nFeet = 60;																	//v-Steps
	float u_iFeet , u_i_1Feet , v_jFeet , v_j_1Feet;								//corners of a facet
	float 	uaFeet = (float) 0, ueFeet = (float)(Math.PI),							//Start and End of the u-Area
			vaFeet = 0, veFeet = (float)(Math.PI);									//Start and End of the v-Area
	float deltaUFeet = (float)(ueFeet-uaFeet)/mFeet;								//how big a single step is in u-Direction
	float deltaVFeet = (float)(veFeet-vaFeet)/nFeet;								//how big a single step is in u-Direction
	float rFeet = zLeg(ueLeg, vaLeg);												//Radius of the hemisphere
	
	
	private void doFeet()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		for(int i = 0; i<mFeet; i++){
			for(int j = 0; j<nFeet; j++){
			
			//declares corners of a facet		
			u_iFeet 	= uaFeet + i * deltaUFeet;
			u_i_1Feet 	= u_iFeet + deltaUFeet;
			v_jFeet 	= vaFeet + j * deltaVFeet;
			v_j_1Feet 	= v_jFeet + deltaVFeet;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xFeet(u_iFeet,v_jFeet), yFeet(u_iFeet,v_jFeet), zFeet(u_iFeet, v_jFeet)),
					new Vector3f(xFeet(u_i_1Feet,v_jFeet), yFeet(u_i_1Feet,v_jFeet), zFeet(u_i_1Feet, v_jFeet)),
					new Vector3f(xFeet(u_iFeet,v_j_1Feet), yFeet(u_iFeet,v_j_1Feet), zFeet(u_iFeet, v_j_1Feet)));
			
			//builds a facet
			glBegin(GL_TRIANGLE_STRIP);
				glNormal3f(normal.x, normal.y, normal.z);
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
	}
	
	private float yFeet(float u, float v){		
		return (float) (rFeet*Math.sin(u)*Math.sin(v));
	}	
	
	private float zFeet(float u, float v){		
		return (float)(rFeet*Math.cos(u));
	}

	public float getHeight(){
		return (2*intervalLeg+rFeet);
	}
	
	
}

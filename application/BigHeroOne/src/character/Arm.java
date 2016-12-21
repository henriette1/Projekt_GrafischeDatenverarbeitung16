package character;

//Imports
import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;

import engine.Utils;

public class Arm{

	public void drawArm()
	{	
		glPushMatrix();
			glPushMatrix();
				glTranslatef(0, intervalArm, 0);
				doUpperJoint();
			glPopMatrix();
			doUpperArm();			
//			doJoint();
			doForeArm();
			glTranslatef(0, -intervalArm, 0);
			doHand();
		glPopMatrix();
	}
	
	// private Variablen
		int mArm = 60;															//u-Steps
		int nArm = 60;															//v-Steps
		float intervalArm = 3;													//Interval where teh function is calculated
		float u_iUpperArm , u_i_1UpperArm , v_jUpperArm , v_j_1UpperArm;		//Corners of a facet
		float 	uaUpperArm = 0.f, ueUpperArm = (float) intervalArm ,			//Start and End of the u-Area
				vaUpperArm = 0.f, veUpperArm = (float)(2*Math.PI);				//Start and End of the v-Area
		float deltaUUpperArm = (float)(ueUpperArm-uaUpperArm)/mArm;				//how big a single step is in u-Direction
		float deltaVUpperArm = (float)(veUpperArm-vaUpperArm)/nArm;				//how big a single step is in v-Direction
		float rArm = 0.75f;														//Radius for the body of rotation
	
	private void doUpperArm()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		for(int i = 0; i<mArm; i++){
			for(int j = 0; j<nArm; j++){
			
			//declares the corners of a facet	
			u_iUpperArm 		= uaUpperArm + i * deltaUUpperArm;
			u_i_1UpperArm 	= u_iUpperArm + deltaUUpperArm;
			v_jUpperArm 		= vaUpperArm + j * deltaVUpperArm;
			v_j_1UpperArm 	= v_jUpperArm + deltaVUpperArm;
			
			Vector3f normal = Utils.normalVector(
								new Vector3f(xArm(u_iUpperArm,v_jUpperArm), yArm(u_iUpperArm,v_jUpperArm), zArm(u_iUpperArm, v_jUpperArm)),
								new Vector3f(xArm(u_i_1UpperArm,v_jUpperArm), yArm(u_i_1UpperArm,v_jUpperArm), zArm(u_i_1UpperArm, v_jUpperArm)),
								new Vector3f(xArm(u_iUpperArm,v_j_1UpperArm), yArm(u_iUpperArm,v_j_1UpperArm), zArm(u_iUpperArm, v_j_1UpperArm)));
			
			//builds a facet
			glBegin(GL_TRIANGLE_STRIP);	
				glNormal3f(normal.x, normal.y, normal.z);				
				glVertex3f(xArm(u_iUpperArm,v_jUpperArm),				yArm(u_iUpperArm,v_jUpperArm), 			zArm(u_iUpperArm, v_jUpperArm));
				glVertex3f(xArm(u_i_1UpperArm,v_jUpperArm),			yArm(u_i_1UpperArm,v_jUpperArm), 			zArm(u_i_1UpperArm, v_jUpperArm));
				glVertex3f(xArm(u_iUpperArm,v_j_1UpperArm),			yArm(u_iUpperArm,v_j_1UpperArm), 			zArm(u_iUpperArm, v_j_1UpperArm));
				glVertex3f(xArm(u_i_1UpperArm,v_j_1UpperArm),			yArm(u_i_1UpperArm,v_j_1UpperArm),			zArm(u_i_1UpperArm, v_j_1UpperArm));
			glEnd();
			}
		}
	}

	
	float u_iForeArm , u_i_1ForeArm , v_jForeArm , v_j_1ForeArm;				//corners of a facet
	float 	uaForeArm = -intervalArm, ueForeArm = 0.f,							//Start and End of the u-Area
			vaForeArm = 0, veForeArm = (float)(2*Math.PI);						//Start and End of the v-Area
	float deltaUForeArm = (float)(ueForeArm-uaForeArm)/mArm;					//how big a single step is in u-Direction
	float deltaVForeArm = (float)(veForeArm-vaForeArm)/nArm;					//how big a single step is in v-Direction
	
	private void doForeArm()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		
		for(int i = 0; i<mArm; i++){
			for(int j = 0; j<nArm; j++){
			
			//declares corners of a facet	
			u_iForeArm 		= uaForeArm + i * deltaUForeArm;
			u_i_1ForeArm 	= u_iForeArm + deltaUForeArm;
			v_jForeArm 		= vaForeArm + j * deltaVForeArm;
			v_j_1ForeArm 	= v_jForeArm + deltaVForeArm;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xArm(u_iForeArm,v_jForeArm), yArm(u_iForeArm,v_jForeArm), zArm(u_iForeArm, v_jForeArm)),
					new Vector3f(xArm(u_i_1ForeArm,v_jForeArm), yArm(u_i_1ForeArm,v_jForeArm), zArm(u_i_1ForeArm, v_jForeArm)),
					new Vector3f(xArm(u_iForeArm,v_j_1ForeArm), yArm(u_iForeArm,v_j_1ForeArm), zArm(u_iForeArm, v_j_1ForeArm)));
			
			//builds a facet
			glBegin(GL_TRIANGLE_STRIP);
				glNormal3f(normal.x, normal.y, normal.z);	
				glVertex3f(xArm(u_iForeArm,v_jForeArm),				yArm(u_iForeArm,v_jForeArm), 			zArm(u_iForeArm, v_jForeArm));
				glVertex3f(xArm(u_i_1ForeArm,v_jForeArm),			yArm(u_i_1ForeArm,v_jForeArm), 			zArm(u_i_1ForeArm, v_jForeArm));
				glVertex3f(xArm(u_iForeArm,v_j_1ForeArm),			yArm(u_iForeArm,v_j_1ForeArm), 			zArm(u_iForeArm, v_j_1ForeArm));
				glVertex3f(xArm(u_i_1ForeArm,v_j_1ForeArm),			yArm(u_i_1ForeArm,v_j_1ForeArm),		zArm(u_i_1ForeArm, v_j_1ForeArm));
			glEnd();
			}
		}
	}
	
	private float xArm(float u, float v){
			
		return (float)((armFunction(u)-rArm)*Math.sin(v)) ;
	
	}
	
	private float yArm(float u, float v){
		
		return (float)(u);

	}	
	
	private float zArm(float u, float v){
		
			return (float)((armFunction(u)-rArm)*Math.cos(v)) ;
	}
	
	private float armFunction(float u){
		
		return (float)((1./54)*u*u);
	}
	
	
	int mHand = 30;																//u-Steps
	int nHand = 30;																//v-Steps
	float u_iHand , u_i_1Hand , v_jHand , v_j_1Hand;							//corners of a facet
	float 	uaHand = 0, ueHand = (float) (Math.PI) ,							//Start and End of the u-Area
			vaHand = 0, veHand = (float)(Math.PI);								//Start and End of the v-Area
	float deltaUHand = (float)(ueHand-uaHand)/mHand;							//how big a single step is in u-Direction
	float deltaVHand = (float)(veHand-vaHand)/nHand;							//how big a single step is in v-Direction
	float rHand = zArm(ueUpperArm, vaUpperArm);									//Radius for the hemisphere
	
	
	private void doHand()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		
		for(int i = 0; i<mHand; i++){
			for(int j = 0; j<nHand; j++){
			
			//declares corners of a facet	
			u_iHand 		= uaHand + i * deltaUHand;
			u_i_1Hand 	= u_iHand + deltaUHand;
			v_jHand 		= vaHand + j * deltaVHand;
			v_j_1Hand 	= v_jHand + deltaVHand;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xHand(u_iHand,v_jHand), yHand(u_iHand,v_jHand), zHand(u_iHand, v_jHand)),
					new Vector3f(xHand(u_i_1Hand,v_jHand), yHand(u_i_1Hand,v_jHand), zHand(u_i_1Hand, v_jHand)),
					new Vector3f(xHand(u_iHand,v_j_1Hand), yHand(u_iHand,v_j_1Hand), zHand(u_iHand, v_j_1Hand)));
			
			//builds a facet
			glBegin(GL_TRIANGLE_STRIP);
				glNormal3f(normal.x, normal.y, normal.z);		
				glVertex3f(xHand(u_iHand,v_jHand),			yHand(u_iHand,v_jHand), 			zHand(u_iHand, v_jHand));
				glVertex3f(xHand(u_i_1Hand,v_jHand),			yHand(u_i_1Hand,v_jHand), 		zHand(u_i_1Hand, v_jHand));
				glVertex3f(xHand(u_iHand,v_j_1Hand),			yHand(u_iHand,v_j_1Hand), 		zHand(u_iHand, v_j_1Hand));
				glVertex3f(xHand(u_i_1Hand,v_j_1Hand),		yHand(u_i_1Hand,v_j_1Hand),		zHand(u_i_1Hand, v_j_1Hand));
			glEnd();
			}
		}
	}
	
	private float xHand(float u, float v){
		
		return (float)(rHand*Math.sin(u)*Math.cos(v));
	
	}
	
	private float yHand(float u, float v){
		
		return (float)(rHand*Math.sin(u)*Math.sin(v));

	}	
	
	private float zHand(float u, float v){
		
		return (float)(rHand*Math.cos(u));
	}
	
	int mJoint = 30;																//u-Steps
	int nJoint = 30;																//v-Steps
	float u_iJoint , u_i_1Joint , v_jJoint , v_j_1Joint;							//corners of a facet
	float 	uaJoint = 0, ueJoint = (float) (Math.PI) ,								//Start and End of the u-Area
			vaJoint = 0, veJoint = (float) (2*Math.PI);								//Start and End of the v-Area
	float deltaUJoint = (float)(ueJoint-uaJoint)/mJoint;							//how big a single step is in u-Direction
	float deltaVJoint = (float)(veJoint-vaJoint)/nJoint;							//how big a single step is in v-Direction
	float rJoint = rArm;															//Radius of the sphere 
	
	
	private void doJoint()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	
		for(int i = 0; i<mJoint; i++){
			for(int j = 0; j<nJoint; j++){
			
			//declares corners of a facet
			u_iJoint 		= uaJoint + i * deltaUJoint;
			u_i_1Joint 	= u_iJoint + deltaUJoint;
			v_jJoint 		= vaJoint + j * deltaVJoint;
			v_j_1Joint 	= v_jJoint + deltaVJoint;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xJoint(u_iJoint,v_jJoint), yJoint(u_iJoint,v_jJoint), zJoint(u_iJoint, v_jJoint)),
					new Vector3f(xJoint(u_i_1Joint,v_jJoint), yJoint(u_i_1Joint,v_jJoint), zJoint(u_i_1Joint, v_jJoint)),
					new Vector3f(xJoint(u_iJoint,v_j_1Joint), yJoint(u_iJoint,v_j_1Joint), zJoint(u_iJoint, v_j_1Joint)));
			
			//builds a facet
			glBegin(GL_TRIANGLE_STRIP);
				glNormal3f(normal.x, normal.y, normal.z);	
				glVertex3f(xJoint(u_iJoint,v_jJoint),			yJoint(u_iJoint,v_jJoint), 			zJoint(u_iJoint, v_jJoint));
				glVertex3f(xJoint(u_i_1Joint,v_jJoint),			yJoint(u_i_1Joint,v_jJoint), 		zJoint(u_i_1Joint, v_jJoint));
				glVertex3f(xJoint(u_iJoint,v_j_1Joint),			yJoint(u_iJoint,v_j_1Joint), 		zJoint(u_iJoint, v_j_1Joint));
				glVertex3f(xJoint(u_i_1Joint,v_j_1Joint),		yJoint(u_i_1Joint,v_j_1Joint),		zJoint(u_i_1Joint, v_j_1Joint));
			glEnd();
			}
		}
	}
	
	private float xJoint(float u, float v){
		
		return (float)(rJoint*Math.sin(u)*Math.cos(v));
	
	}
	
	private float yJoint(float u, float v){
		
		return (float)(rJoint*Math.sin(u)*Math.sin(v));

	}	
	
	private float zJoint(float u, float v){
		
		return (float)(rJoint*Math.cos(u));
	}
	
	float rUpperJoint = armFunction(intervalArm)-rArm;										//Radius 
	
	private void doUpperJoint()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		
		for(int i = 0; i<mJoint; i++){
			for(int j = 0; j<nJoint; j++){
			
			//declares corners of facet	
			u_iJoint 		= uaJoint + i * deltaUJoint;
			u_i_1Joint 		= u_iJoint + deltaUJoint;
			v_jJoint 		= vaJoint + j * deltaVJoint;
			v_j_1Joint 		= v_jJoint + deltaVJoint;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xUpperJoint(u_iJoint,v_jJoint), yUpperJoint(u_iJoint,v_jJoint), zUpperJoint(u_iJoint, v_jJoint)),
					new Vector3f(xUpperJoint(u_i_1Joint,v_jJoint), yUpperJoint(u_i_1Joint,v_jJoint), zUpperJoint(u_i_1Joint, v_jJoint)),
					new Vector3f(xUpperJoint(u_iJoint,v_j_1Joint), yUpperJoint(u_iJoint,v_j_1Joint), zUpperJoint(u_iJoint, v_j_1Joint)));
			
			////builds a facet
			glBegin(GL_TRIANGLE_STRIP);
				glNormal3f(normal.x, normal.y, normal.z);	
				glVertex3f(xUpperJoint(u_iJoint,v_jJoint),			yUpperJoint(u_iJoint,v_jJoint), 			zUpperJoint(u_iJoint, v_jJoint));
				glVertex3f(xUpperJoint(u_i_1Joint,v_jJoint),			yUpperJoint(u_i_1Joint,v_jJoint), 		zUpperJoint(u_i_1Joint, v_jJoint));
				glVertex3f(xUpperJoint(u_iJoint,v_j_1Joint),			yUpperJoint(u_iJoint,v_j_1Joint), 		zUpperJoint(u_iJoint, v_j_1Joint));
				glVertex3f(xUpperJoint(u_i_1Joint,v_j_1Joint),		yUpperJoint(u_i_1Joint,v_j_1Joint),		zUpperJoint(u_i_1Joint, v_j_1Joint));
			glEnd();
			}
		}
	}
	
	private float xUpperJoint(float u, float v){
		
		return (float)(rUpperJoint* Math.sin(u)*Math.cos(v));	
	}
	
	private float yUpperJoint(float u, float v){
		
		return (float)(rUpperJoint*Math.sin(u)*Math.sin(v));
	}	
	
	private float zUpperJoint(float u, float v){
		
		return (float)(rUpperJoint*Math.cos(u));
	}
	
	public float getHeight(){
		return (float)(2*intervalArm + rHand);
	}
}
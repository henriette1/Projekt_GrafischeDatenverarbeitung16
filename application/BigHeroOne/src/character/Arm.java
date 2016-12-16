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
		int mArm = 60;															//u-Schritte
		int nArm = 60;															//v-Schritte
		float intervalArm = 3;													//u-Abschnitt
		float u_iUpperArm , u_i_1UpperArm , v_jUpperArm , v_j_1UpperArm;							//Eckpunkte einer Facette
		float 	uaUpperArm = 0.f, ueUpperArm = (float) intervalArm ,				//Anfang und Ende des u-Bereichs
				vaUpperArm = 0.f, veUpperArm = (float)(2*Math.PI);							//Anfang und Ende des v-Bereichs
		float deltaUUpperArm = (float)(ueUpperArm-uaUpperArm)/mArm;							//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
		float deltaVUpperArm = (float)(veUpperArm-vaUpperArm)/nArm;							//wie gro� ein einzelner Teilschritt sein muss in v-Richtung
		float rArm = 0.75f;																//Radius 
	
	private void doUpperArm()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		for(int i = 0; i<mArm; i++){
			for(int j = 0; j<nArm; j++){
			
			//Eckpunkte einer Facette deklarieren	
			u_iUpperArm 		= uaUpperArm + i * deltaUUpperArm;
			u_i_1UpperArm 	= u_iUpperArm + deltaUUpperArm;
			v_jUpperArm 		= vaUpperArm + j * deltaVUpperArm;
			v_j_1UpperArm 	= v_jUpperArm + deltaVUpperArm;
			
			Vector3f normal = Utils.normalVector(
								new Vector3f(xArm(u_iUpperArm,v_jUpperArm), yArm(u_iUpperArm,v_jUpperArm), zArm(u_iUpperArm, v_jUpperArm)),
								new Vector3f(xArm(u_i_1UpperArm,v_jUpperArm), yArm(u_i_1UpperArm,v_jUpperArm), zArm(u_i_1UpperArm, v_jUpperArm)),
								new Vector3f(xArm(u_iUpperArm,v_j_1UpperArm), yArm(u_iUpperArm,v_j_1UpperArm), zArm(u_iUpperArm, v_j_1UpperArm)));
			
			//Erstellung einer Facette
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

	
	float u_iForeArm , u_i_1ForeArm , v_jForeArm , v_j_1ForeArm;							//Eckpunkte einer Facette
	float 	uaForeArm = -intervalArm, ueForeArm = 0.f,				//Anfang und Ende des u-Bereichs
			vaForeArm = 0, veForeArm = (float)(2*Math.PI);							//Anfang und Ende des v-Bereichs
	float deltaUForeArm = (float)(ueForeArm-uaForeArm)/mArm;							//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
	float deltaVForeArm = (float)(veForeArm-vaForeArm)/nArm;							//wie gro� ein einzelner Teilschritt sein muss in v-Richtung
	
	private void doForeArm()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		
		for(int i = 0; i<mArm; i++){
			for(int j = 0; j<nArm; j++){
			
			//Eckpunkte einer Facette deklarieren	
			u_iForeArm 		= uaForeArm + i * deltaUForeArm;
			u_i_1ForeArm 	= u_iForeArm + deltaUForeArm;
			v_jForeArm 		= vaForeArm + j * deltaVForeArm;
			v_j_1ForeArm 	= v_jForeArm + deltaVForeArm;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xArm(u_iForeArm,v_jForeArm), yArm(u_iForeArm,v_jForeArm), zArm(u_iForeArm, v_jForeArm)),
					new Vector3f(xArm(u_i_1ForeArm,v_jForeArm), yArm(u_i_1ForeArm,v_jForeArm), zArm(u_i_1ForeArm, v_jForeArm)),
					new Vector3f(xArm(u_iForeArm,v_j_1ForeArm), yArm(u_iForeArm,v_j_1ForeArm), zArm(u_iForeArm, v_j_1ForeArm)));
			
			//Erstellung einer Facette
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
	
	
	int mHand = 30;																	//u-Schritte
	int nHand = 30;																	//v-Schritte
	float u_iHand , u_i_1Hand , v_jHand , v_j_1Hand;								//Eckpunkte einer Facette
	float 	uaHand = 0, ueHand = (float) (Math.PI) ,								//Anfang und Ende des u-Bereichs
			vaHand = 0, veHand = (float)(Math.PI);									//Anfang und Ende des v-Bereichs
	float deltaUHand = (float)(ueHand-uaHand)/mHand;								//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
	float deltaVHand = (float)(veHand-vaHand)/nHand;								//wie gro� ein einzelner Teilschritt sein muss in v-Richtung
	float rHand = zArm(ueUpperArm, vaUpperArm);												//Radius 
	
	
	private void doHand()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		
		for(int i = 0; i<mHand; i++){
			for(int j = 0; j<nHand; j++){
			
			//Eckpunkte einer Facette deklarieren	
			u_iHand 		= uaHand + i * deltaUHand;
			u_i_1Hand 	= u_iHand + deltaUHand;
			v_jHand 		= vaHand + j * deltaVHand;
			v_j_1Hand 	= v_jHand + deltaVHand;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xHand(u_iHand,v_jHand), yHand(u_iHand,v_jHand), zHand(u_iHand, v_jHand)),
					new Vector3f(xHand(u_i_1Hand,v_jHand), yHand(u_i_1Hand,v_jHand), zHand(u_i_1Hand, v_jHand)),
					new Vector3f(xHand(u_iHand,v_j_1Hand), yHand(u_iHand,v_j_1Hand), zHand(u_iHand, v_j_1Hand)));
			
			//Erstellung einer Facette
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
	
	int mJoint = 30;																	//u-Schritte
	int nJoint = 30;																	//v-Schritte
	float u_iJoint , u_i_1Joint , v_jJoint , v_j_1Joint;								//Eckpunkte einer Facette
	float 	uaJoint = 0, ueJoint = (float) (Math.PI) ,								//Anfang und Ende des u-Bereichs
			vaJoint = 0, veJoint = (float) (2*Math.PI);									//Anfang und Ende des v-Bereichs
	float deltaUJoint = (float)(ueJoint-uaJoint)/mJoint;								//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
	float deltaVJoint = (float)(veJoint-vaJoint)/nJoint;								//wie gro� ein einzelner Teilschritt sein muss in v-Richtung
	float rJoint = rArm;														//Radius 
	
	
	private void doJoint()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	
		for(int i = 0; i<mJoint; i++){
			for(int j = 0; j<nJoint; j++){
			
			//Eckpunkte einer Facette deklarieren	
			u_iJoint 		= uaJoint + i * deltaUJoint;
			u_i_1Joint 	= u_iJoint + deltaUJoint;
			v_jJoint 		= vaJoint + j * deltaVJoint;
			v_j_1Joint 	= v_jJoint + deltaVJoint;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xJoint(u_iJoint,v_jJoint), yJoint(u_iJoint,v_jJoint), zJoint(u_iJoint, v_jJoint)),
					new Vector3f(xJoint(u_i_1Joint,v_jJoint), yJoint(u_i_1Joint,v_jJoint), zJoint(u_i_1Joint, v_jJoint)),
					new Vector3f(xJoint(u_iJoint,v_j_1Joint), yJoint(u_iJoint,v_j_1Joint), zJoint(u_iJoint, v_j_1Joint)));
			
			//Erstellung einer Facette
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
	
	float rUpperJoint = armFunction(intervalArm)-rArm;																//Radius 
	
	private void doUpperJoint()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		
		for(int i = 0; i<mJoint; i++){
			for(int j = 0; j<nJoint; j++){
			
			//Eckpunkte einer Facette deklarieren	
			u_iJoint 		= uaJoint + i * deltaUJoint;
			u_i_1Joint 		= u_iJoint + deltaUJoint;
			v_jJoint 		= vaJoint + j * deltaVJoint;
			v_j_1Joint 		= v_jJoint + deltaVJoint;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xUpperJoint(u_iJoint,v_jJoint), yUpperJoint(u_iJoint,v_jJoint), zUpperJoint(u_iJoint, v_jJoint)),
					new Vector3f(xUpperJoint(u_i_1Joint,v_jJoint), yUpperJoint(u_i_1Joint,v_jJoint), zUpperJoint(u_i_1Joint, v_jJoint)),
					new Vector3f(xUpperJoint(u_iJoint,v_j_1Joint), yUpperJoint(u_iJoint,v_j_1Joint), zUpperJoint(u_iJoint, v_j_1Joint)));
			
			//Erstellung einer Facette
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
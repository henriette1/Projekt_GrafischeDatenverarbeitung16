package character;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;

import engine.Utils;

public class Finger {
	
	public void drawFinger(){
		glPushMatrix();
			doFinger();
			glTranslatef(2*rFinger,0.f,0.f);
			doFinger();
			glTranslatef(2*rFinger,0.f,0.f);
			doFinger();
			glTranslatef(2*rFinger,2*intervalFinger,0.f);
			glRotatef(90, 0, 0, 1);
			doThumb();
		glPopMatrix();
	}
	
	private void doFinger(){
		glPushMatrix();
			doUpperFinger();			
			doJoint();
			doLowerFinger();
			glTranslatef(0, -intervalFinger, 0);
			doPeak();
		glPopMatrix();
		
	}
	
	private void doThumb(){
		glPushMatrix();
			doLowerFinger();
			glTranslatef(0, -intervalFinger, 0);
			doPeak();
		glPopMatrix();
	}
	
	// private Variablen
			int mFinger = 5;															//u-Schritte
			int nFinger = 5;															//v-Schritte
			float intervalFinger = 0.25f;													//u-Abschnitt
			float u_iUpperFinger , u_i_1UpperFinger , v_jUpperFinger , v_j_1UpperFinger;							//Eckpunkte einer Facette
			float 	uaUpperFinger = 0.f, ueUpperFinger = (float) intervalFinger ,				//Anfang und Ende des u-Bereichs
					vaUpperFinger = 0.f, veUpperFinger = (float)(2*Math.PI);							//Anfang und Ende des v-Bereichs
			float deltaUUpperFinger = (float)(ueUpperFinger-uaUpperFinger)/mFinger;							//wie groﬂ ein einzelner Teilschritt sein muss in u-Richtung
			float deltaVUpperFinger = (float)(veUpperFinger-vaUpperFinger)/nFinger;							//wie groﬂ ein einzelner Teilschritt sein muss in v-Richtung
			float rFinger = 0.125f;																//Radius 
		
		private void doUpperFinger()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

			for(int i = 0; i<mFinger; i++){
				for(int j = 0; j<nFinger; j++){
				
				//Eckpunkte einer Facette deklarieren	
				u_iUpperFinger 		= uaUpperFinger + i * deltaUUpperFinger;
				u_i_1UpperFinger 	= u_iUpperFinger + deltaUUpperFinger;
				v_jUpperFinger 		= vaUpperFinger + j * deltaVUpperFinger;
				v_j_1UpperFinger 	= v_jUpperFinger + deltaVUpperFinger;
				
				Vector3f normal = Utils.normalVector(
						new Vector3f(xFinger(u_iUpperFinger,v_jUpperFinger), yFinger(u_iUpperFinger,v_jUpperFinger), zFinger(u_iUpperFinger, v_jUpperFinger)),
						new Vector3f(xFinger(u_i_1UpperFinger,v_jUpperFinger), yFinger(u_i_1UpperFinger,v_jUpperFinger), zFinger(u_i_1UpperFinger, v_jUpperFinger)),
						new Vector3f(xFinger(u_iUpperFinger,v_j_1UpperFinger), yFinger(u_iUpperFinger,v_j_1UpperFinger), zFinger(u_iUpperFinger, v_j_1UpperFinger)));
				
				//Erstellung einer Facette
				glBegin(GL_TRIANGLE_STRIP);	
					glNormal3f(normal.x, normal.y, normal.z);
					glVertex3f(xFinger(u_iUpperFinger,v_jUpperFinger),				yFinger(u_iUpperFinger,v_jUpperFinger), 			zFinger(u_iUpperFinger, v_jUpperFinger));
					glVertex3f(xFinger(u_i_1UpperFinger,v_jUpperFinger),			yFinger(u_i_1UpperFinger,v_jUpperFinger), 			zFinger(u_i_1UpperFinger, v_jUpperFinger));
					glVertex3f(xFinger(u_iUpperFinger,v_j_1UpperFinger),			yFinger(u_iUpperFinger,v_j_1UpperFinger), 			zFinger(u_iUpperFinger, v_j_1UpperFinger));
					glVertex3f(xFinger(u_i_1UpperFinger,v_j_1UpperFinger),			yFinger(u_i_1UpperFinger,v_j_1UpperFinger),			zFinger(u_i_1UpperFinger, v_j_1UpperFinger));
				glEnd();
				}
			}
		}
		
		
		float u_iLowerFinger , u_i_1LowerFinger , v_jLowerFinger , v_j_1LowerFinger;							//Eckpunkte einer Facette
		float 	uaLowerFinger = -intervalFinger, ueLowerFinger = 0.f,				//Anfang und Ende des u-Bereichs
				vaLowerFinger = 0, veLowerFinger = (float)(2*Math.PI);							//Anfang und Ende des v-Bereichs
		float deltaULowerFinger = (float)(ueLowerFinger-uaLowerFinger)/mFinger;							//wie groﬂ ein einzelner Teilschritt sein muss in u-Richtung
		float deltaVLowerFinger = (float)(veLowerFinger-vaLowerFinger)/nFinger;							//wie groﬂ ein einzelner Teilschritt sein muss in v-Richtung

		
		private void doLowerFinger()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

			for(int i = 0; i<mFinger; i++){
				for(int j = 0; j<nFinger; j++){
				
				//Eckpunkte einer Facette deklarieren	
				u_iLowerFinger 		= uaLowerFinger + i * deltaULowerFinger;
				u_i_1LowerFinger 	= u_iLowerFinger + deltaULowerFinger;
				v_jLowerFinger 		= vaLowerFinger + j * deltaVLowerFinger;
				v_j_1LowerFinger 	= v_jLowerFinger + deltaVLowerFinger;
				
				Vector3f normal = Utils.normalVector(
						new Vector3f(xFinger(u_iLowerFinger,v_jLowerFinger), yFinger(u_iLowerFinger,v_jLowerFinger), zFinger(u_iLowerFinger, v_jLowerFinger)),
						new Vector3f(xFinger(u_i_1LowerFinger,v_jLowerFinger), yFinger(u_i_1LowerFinger,v_jLowerFinger), zFinger(u_i_1LowerFinger, v_jLowerFinger)),
						new Vector3f(xFinger(u_iLowerFinger,v_j_1LowerFinger), yFinger(u_iLowerFinger,v_j_1LowerFinger), zFinger(u_iLowerFinger, v_j_1LowerFinger)));
				
				//Erstellung einer Facette
				glBegin(GL_TRIANGLE_STRIP);	
					glNormal3f(normal.x, normal.y, normal.z);
					glVertex3f(xFinger(u_iLowerFinger,v_jLowerFinger),				yFinger(u_iLowerFinger,v_jLowerFinger), 			zFinger(u_iLowerFinger, v_jLowerFinger));
					glVertex3f(xFinger(u_i_1LowerFinger,v_jLowerFinger),			yFinger(u_i_1LowerFinger,v_jLowerFinger), 			zFinger(u_i_1LowerFinger, v_jLowerFinger));
					glVertex3f(xFinger(u_iLowerFinger,v_j_1LowerFinger),			yFinger(u_iLowerFinger,v_j_1LowerFinger), 			zFinger(u_iLowerFinger, v_j_1LowerFinger));
					glVertex3f(xFinger(u_i_1LowerFinger,v_j_1LowerFinger),			yFinger(u_i_1LowerFinger,v_j_1LowerFinger),		zFinger(u_i_1LowerFinger, v_j_1LowerFinger));
				glEnd();
				}
			}
		}
		
		private float xFinger(float u, float v){
				
			return (float)(((u*u)-rFinger)*Math.sin(v)) ;
		
		}
		
		private float yFinger(float u, float v){
			
			return (float)(u);

		}	
		
		private float zFinger(float u, float v){
			
				return (float)(((u*u)-rFinger)*Math.cos(v)) ;
		}
		
		
		int mPeak = 5;																	//u-Schritte
		int nPeak = 5;																	//v-Schritte
		float u_iPeak , u_i_1Peak , v_jPeak , v_j_1Peak;								//Eckpunkte einer Facette
		float 	uaPeak = 0, uePeak = (float) (Math.PI) ,								//Anfang und Ende des u-Bereichs
				vaPeak = 0, vePeak = (float)(Math.PI);									//Anfang und Ende des v-Bereichs
		float deltaUPeak = (float)(uePeak-uaPeak)/mPeak;								//wie groﬂ ein einzelner Teilschritt sein muss in u-Richtung
		float deltaVPeak = (float)(vePeak-vaPeak)/nPeak;								//wie groﬂ ein einzelner Teilschritt sein muss in v-Richtung
		float rPeak = zFinger(ueUpperFinger, vaUpperFinger);												//Radius 
		
		
		private void doPeak()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

			for(int i = 0; i<mPeak; i++){
				for(int j = 0; j<nPeak; j++){
				
				//Eckpunkte einer Facette deklarieren	
				u_iPeak 		= uaPeak + i * deltaUPeak;
				u_i_1Peak 	= u_iPeak + deltaUPeak;
				v_jPeak 		= vaPeak + j * deltaVPeak;
				v_j_1Peak 	= v_jPeak + deltaVPeak;
				
				Vector3f normal = Utils.normalVector(
						new Vector3f(xPeak(u_iPeak,v_jPeak), yPeak(u_iPeak,v_jPeak), zPeak(u_iPeak, v_jPeak)),
						new Vector3f(xPeak(u_i_1Peak,v_jPeak), yPeak(u_i_1Peak,v_jPeak), zPeak(u_i_1Peak, v_jPeak)),
						new Vector3f(xPeak(u_iPeak,v_j_1Peak), yPeak(u_iPeak,v_j_1Peak), zPeak(u_iPeak, v_j_1Peak)));
				
				//Erstellung einer Facette
				glBegin(GL_TRIANGLE_STRIP);
					glNormal3f(normal.x, normal.y, normal.z);
					glVertex3f(xPeak(u_iPeak,v_jPeak),			yPeak(u_iPeak,v_jPeak), 			zPeak(u_iPeak, v_jPeak));
					glVertex3f(xPeak(u_i_1Peak,v_jPeak),			yPeak(u_i_1Peak,v_jPeak), 		zPeak(u_i_1Peak, v_jPeak));
					glVertex3f(xPeak(u_iPeak,v_j_1Peak),			yPeak(u_iPeak,v_j_1Peak), 		zPeak(u_iPeak, v_j_1Peak));
					glVertex3f(xPeak(u_i_1Peak,v_j_1Peak),		yPeak(u_i_1Peak,v_j_1Peak),		zPeak(u_i_1Peak, v_j_1Peak));
				glEnd();
				}
			}
		}
		
		private float xPeak(float u, float v){
			
			return (float)(rPeak*Math.sin(u)*Math.cos(v));
		
		}
		
		private float yPeak(float u, float v){
			
			return (float)(rPeak*Math.sin(u)*Math.sin(v));

		}	
		
		private float zPeak(float u, float v){
			
			return (float)(rPeak*Math.cos(u));
		}
		
		int mJoint = 5;																	//u-Schritte
		int nJoint = 5;																	//v-Schritte
		float u_iJoint , u_i_1Joint , v_jJoint , v_j_1Joint;								//Eckpunkte einer Facette
		float 	uaJoint = 0, ueJoint = (float) (Math.PI) ,								//Anfang und Ende des u-Bereichs
				vaJoint = 0, veJoint = (float) (2*Math.PI);									//Anfang und Ende des v-Bereichs
		float deltaUJoint = (float)(ueJoint-uaJoint)/mJoint;								//wie groﬂ ein einzelner Teilschritt sein muss in u-Richtung
		float deltaVJoint = (float)(veJoint-vaJoint)/nJoint;								//wie groﬂ ein einzelner Teilschritt sein muss in v-Richtung
		float rJoint = rFinger;														//Radius 
		
		
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

}

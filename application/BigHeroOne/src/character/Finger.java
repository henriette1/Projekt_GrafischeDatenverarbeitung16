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
	
	public void drawOrthogonalFinger(){
		glPushMatrix();
			glRotatef(-90, 1, 0, 0);
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
	
	// private Variables
			int mFinger = 10;																//u-Steps
			int nFinger = 10;																//v-Steps
			float intervalFinger = 0.25f;													//u-Interval
			float u_iUpperFinger , u_i_1UpperFinger , v_jUpperFinger , v_j_1UpperFinger;	//corners of a facet
			float 	uaUpperFinger = 0.f, ueUpperFinger = (float) intervalFinger ,			//Start an End of the u-Area
					vaUpperFinger = 0.f, veUpperFinger = (float)(2*Math.PI);				//Start an End of the v-Area
			float deltaUUpperFinger = (float)(ueUpperFinger-uaUpperFinger)/mFinger;			//how big a single step is in u-Direction
			float deltaVUpperFinger = (float)(veUpperFinger-vaUpperFinger)/nFinger;			//how big a single step is in v-Direction
			float rFinger = 0.125f;															//Radius of the body of rotation
		
		private void doUpperFinger()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

			for(int i = 0; i<mFinger; i++){
				for(int j = 0; j<nFinger; j++){
				
				//declares corners of a facet	
				u_iUpperFinger 		= uaUpperFinger + i * deltaUUpperFinger;
				u_i_1UpperFinger 	= u_iUpperFinger + deltaUUpperFinger;
				v_jUpperFinger 		= vaUpperFinger + j * deltaVUpperFinger;
				v_j_1UpperFinger 	= v_jUpperFinger + deltaVUpperFinger;
				
				Vector3f normal = Utils.normalVector(
						new Vector3f(xFinger(u_iUpperFinger,v_jUpperFinger), yFinger(u_iUpperFinger,v_jUpperFinger), zFinger(u_iUpperFinger, v_jUpperFinger)),
						new Vector3f(xFinger(u_i_1UpperFinger,v_jUpperFinger), yFinger(u_i_1UpperFinger,v_jUpperFinger), zFinger(u_i_1UpperFinger, v_jUpperFinger)),
						new Vector3f(xFinger(u_iUpperFinger,v_j_1UpperFinger), yFinger(u_iUpperFinger,v_j_1UpperFinger), zFinger(u_iUpperFinger, v_j_1UpperFinger)));
				
				//builds a facet
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
		
		
		float u_iLowerFinger , u_i_1LowerFinger , v_jLowerFinger , v_j_1LowerFinger;	//corners of a facet
		float 	uaLowerFinger = -intervalFinger, ueLowerFinger = 0.f,					//Start an End of the u-Area
				vaLowerFinger = 0, veLowerFinger = (float)(2*Math.PI);					//Start an End of the v-Area
		float deltaULowerFinger = (float)(ueLowerFinger-uaLowerFinger)/mFinger;			//how big a single step is in u-Direction
		float deltaVLowerFinger = (float)(veLowerFinger-vaLowerFinger)/nFinger;			//how big a single step is in v-Direction

		
		private void doLowerFinger()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

			for(int i = 0; i<mFinger; i++){
				for(int j = 0; j<nFinger; j++){
				
				//declares corners of a facet	
				u_iLowerFinger 		= uaLowerFinger + i * deltaULowerFinger;
				u_i_1LowerFinger 	= u_iLowerFinger + deltaULowerFinger;
				v_jLowerFinger 		= vaLowerFinger + j * deltaVLowerFinger;
				v_j_1LowerFinger 	= v_jLowerFinger + deltaVLowerFinger;
				
				Vector3f normal = Utils.normalVector(
						new Vector3f(xFinger(u_iLowerFinger,v_jLowerFinger), yFinger(u_iLowerFinger,v_jLowerFinger), zFinger(u_iLowerFinger, v_jLowerFinger)),
						new Vector3f(xFinger(u_i_1LowerFinger,v_jLowerFinger), yFinger(u_i_1LowerFinger,v_jLowerFinger), zFinger(u_i_1LowerFinger, v_jLowerFinger)),
						new Vector3f(xFinger(u_iLowerFinger,v_j_1LowerFinger), yFinger(u_iLowerFinger,v_j_1LowerFinger), zFinger(u_iLowerFinger, v_j_1LowerFinger)));
				
				//builds a facet
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
		
		
		int mPeak = 10;																	//u-Steps
		int nPeak = 10;																	//v-Steps
		float u_iPeak , u_i_1Peak , v_jPeak , v_j_1Peak;								//corners of a facet
		float 	uaPeak = 0, uePeak = (float) (Math.PI) ,								//Start an End of the u-Area
				vaPeak = 0, vePeak = (float)(Math.PI);									//Start an End of the v-Area
		float deltaUPeak = (float)(uePeak-uaPeak)/mPeak;								//how big a single step is in u-Direction
		float deltaVPeak = (float)(vePeak-vaPeak)/nPeak;								//how big a single step is in v-Direction
		float rPeak = zFinger(ueUpperFinger, vaUpperFinger);							//Radius of the sphere
		
		
		private void doPeak()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

			for(int i = 0; i<mPeak; i++){
				for(int j = 0; j<nPeak; j++){
				
				//declares corners of a facet	
				u_iPeak 		= uaPeak + i * deltaUPeak;
				u_i_1Peak 	= u_iPeak + deltaUPeak;
				v_jPeak 		= vaPeak + j * deltaVPeak;
				v_j_1Peak 	= v_jPeak + deltaVPeak;
				
				Vector3f normal = Utils.normalVector(
						new Vector3f(xPeak(u_iPeak,v_jPeak), yPeak(u_iPeak,v_jPeak), zPeak(u_iPeak, v_jPeak)),
						new Vector3f(xPeak(u_i_1Peak,v_jPeak), yPeak(u_i_1Peak,v_jPeak), zPeak(u_i_1Peak, v_jPeak)),
						new Vector3f(xPeak(u_iPeak,v_j_1Peak), yPeak(u_iPeak,v_j_1Peak), zPeak(u_iPeak, v_j_1Peak)));
				
				//builds a facet
				glBegin(GL_TRIANGLE_STRIP);
					glNormal3f(normal.x, normal.y, normal.z);
					glVertex3f(xPeak(u_iPeak,v_jPeak),			yPeak(u_iPeak,v_jPeak), 		zPeak(u_iPeak, v_jPeak));
					glVertex3f(xPeak(u_i_1Peak,v_jPeak),		yPeak(u_i_1Peak,v_jPeak), 		zPeak(u_i_1Peak, v_jPeak));
					glVertex3f(xPeak(u_iPeak,v_j_1Peak),		yPeak(u_iPeak,v_j_1Peak), 		zPeak(u_iPeak, v_j_1Peak));
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
		
		int mJoint = 10;																//u-Steps
		int nJoint = 10;																//v-Steps
		float u_iJoint , u_i_1Joint , v_jJoint , v_j_1Joint;							//corners of a facet
		float 	uaJoint = 0, ueJoint = (float) (Math.PI) ,								//Start an End of the u-Area
				vaJoint = 0, veJoint = (float) (2*Math.PI);								//Start an End of the v-Area
		float deltaUJoint = (float)(ueJoint-uaJoint)/mJoint;							//how big a single step is in u-Direction
		float deltaVJoint = (float)(veJoint-vaJoint)/nJoint;							//how big a single step is in v-Direction
		float rJoint = rFinger;															//Radius of the sphere 
		
		
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

}

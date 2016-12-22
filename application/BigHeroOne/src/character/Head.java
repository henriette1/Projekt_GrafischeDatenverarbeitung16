package character;

//Imports
import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;

import engine.Materials;
import engine.Utils;

public class Head {		
		
	public void drawHead()
	{	
		glPushMatrix();
			Materials.materialBigHeroOne();
			doHead();
			glPushMatrix();
				glTranslatef(0, 0, 0.005f);
				Materials.materialEmerald();
				doEye((float) (5*Math.PI/8), (float) (6*Math.PI/8));
			glPopMatrix();
			glPushMatrix();
				glTranslatef(0, 0, 0.005f);
				Materials.materialEmerald();
				doEye((float) (2*Math.PI/8), (float) (3*Math.PI/8));
			glPopMatrix();
			glPushMatrix();
				glTranslatef(0, 0, 0.001f);
				Materials.materialEmerald();
				doConnection();
				glPopMatrix();
		glPopMatrix();
	}
	
	// private Variables
	int mHead = 30;															//u-Steps
	int nHead = 30;															//v-Steps
	float aHead = 1.5f;														//x-Direction
	float bHead = 1.0f;														//y-Direction
	float cHead = 1.2f;														//z-Direction
	float u_iHead , u_i_1Head , v_jHead , v_j_1Head;						//corners of a facet
	float 	uaHead = (float) 0, ueHead = (float)(2*Math.PI),				//Start and End of the u-Area
			vaHead = (float) -(Math.PI/2.), veHead = (float)(Math.PI/2.);	//Start and End of the v-Area
	float deltaUHead = (float)(ueHead-uaHead)/mHead;						//how big a single step is in u-Direction
	float deltaVHead = (float)(veHead-vaHead)/nHead;						//how big a single step is in v-Direction
		
	private void doHead()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
			for(int i = 0; i<mHead; i++){
			for(int j = 0; j<nHead; j++){
			
			//declares corners of a facet	
			u_iHead 	= uaHead + i * deltaUHead;
			u_i_1Head 	= u_iHead + deltaUHead;
			v_jHead 	= vaHead + j * deltaVHead;
			v_j_1Head 	= v_jHead + deltaVHead;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xHead(u_iHead,v_jHead), yHead(u_iHead,v_jHead), zHead(u_iHead, v_jHead)),
					new Vector3f(xHead(u_i_1Head,v_jHead), yHead(u_i_1Head,v_jHead), zHead(u_i_1Head, v_jHead)),
					new Vector3f(xHead(u_iHead,v_j_1Head), yHead(u_iHead,v_j_1Head), zHead(u_iHead, v_j_1Head)));
			
			//builds a facet
			glBegin(GL_TRIANGLE_STRIP);
				glNormal3f(normal.x, normal.y, normal.z);
				glVertex3f(xHead(u_iHead,v_jHead),			yHead(u_iHead,v_jHead), 		zHead(u_iHead, v_jHead));
				glVertex3f(xHead(u_i_1Head,v_jHead),		yHead(u_i_1Head,v_jHead), 		zHead(u_i_1Head, v_jHead));
				glVertex3f(xHead(u_iHead,v_j_1Head),		yHead(u_iHead,v_j_1Head), 		zHead(u_iHead, v_j_1Head));
				glVertex3f(xHead(u_i_1Head,v_j_1Head),		yHead(u_i_1Head,v_j_1Head),		zHead(u_i_1Head, v_j_1Head));
			glEnd();
			}
		}
	}
	
	private float xHead(float u, float v){			
		return (float)(aHead* Math.cos(u)*Math.cos(v));	
	}
	
	private float yHead(float u, float v){		
		return (float)(bHead*Math.sin(v));
	}	
	
	private float zHead(float u, float v){		
		return (float)(cHead* Math.sin(u)*Math.cos(v));
	}	
	
	int mConnection = 5;																				//u-Steps
	int nConnection = 2;																				//v-Steps
	float u_iConnection , u_i_1Connection , v_jConnection , v_j_1Connection;							//corners of a facet
	float 	uaConnection = (float) (3*Math.PI/8), ueConnection = (float) (5*Math.PI/8) ,				//Start and End of the u-Area
			vaConnection =  (float) (-Math.PI/100)+0.25f, veConnection = (float)(Math.PI/100)+0.25f;	//Start and End of the v-Area
	float deltaUConnection = (float)(ueConnection-uaConnection)/mConnection;							//how big a single step is in u-Direction
	float deltaVConnection = (float)(veConnection-vaConnection)/nConnection;							//how big a single step is in v-Direction
			
	private void doConnection()
	{
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
			for(int i = 0; i<mConnection; i++){
			for(int j = 0; j<nConnection; j++){
			
			//declares corners of a facet
			u_iConnection 		= uaConnection + i * deltaUConnection;
			u_i_1Connection 	= u_iConnection + deltaUConnection;
			v_jConnection 		= vaConnection + j * deltaVConnection;
			v_j_1Connection 	= v_jConnection + deltaVConnection;
			
			Vector3f normal = Utils.normalVector(
					new Vector3f(xConnection(u_iConnection,v_jConnection), yConnection(u_iConnection,v_jConnection), zConnection(u_iConnection, v_jConnection)),
					new Vector3f(xConnection(u_i_1Connection,v_jConnection), yConnection(u_i_1Connection,v_jConnection), zConnection(u_i_1Connection, v_jConnection)),
					new Vector3f(xConnection(u_iConnection,v_j_1Connection), yConnection(u_iConnection,v_j_1Connection), zConnection(u_iConnection, v_j_1Connection)));
			
			//builds a facet
			glBegin(GL_TRIANGLE_STRIP);
				glNormal3f(normal.x, normal.y, normal.z);
				glVertex3f(xConnection(u_iConnection,v_jConnection),				yConnection(u_iConnection,v_jConnection), 			zConnection(u_iConnection, v_jConnection));
				glVertex3f(xConnection(u_i_1Connection,v_jConnection),			yConnection(u_i_1Connection,v_jConnection), 			zConnection(u_i_1Connection, v_jConnection));
				glVertex3f(xConnection(u_iConnection,v_j_1Connection),			yConnection(u_iConnection,v_j_1Connection), 			zConnection(u_iConnection, v_j_1Connection));
				glVertex3f(xConnection(u_i_1Connection,v_j_1Connection),			yConnection(u_i_1Connection,v_j_1Connection),			zConnection(u_i_1Connection, v_j_1Connection));
			glEnd();
			}
		}
	}
	
	private float xConnection(float u, float v){		
		return (float)(aHead* Math.cos(u)*Math.cos(v));		
	}
	
	private float yConnection(float u, float v){	
		return (float)(bHead*Math.sin(v));
	}	
	
	private float zConnection(float u, float v){		
		return (float)(cHead* Math.sin(u)*Math.cos(v));
	}
	
	int mEye = 5;																		//u-Steps
	int nEye = 2;																		//v-Steps
	float u_iEye , u_i_1Eye , v_jEye , v_j_1Eye;										//corners of a facet
	float 	uaEye, ueEye,																//Start and End of the u-Area
			vaEye = (float) (-Math.PI/25)+0.25f, veEye = (float)(Math.PI/25)+0.25f;		//Start and End of the v-Area
		
		
	private void doEye(float a, float b)
	{
		setUaEye(a);
		setUeEye(b);
		float deltaUEye = (float)(ueEye-uaEye)/mEye;									//how big a single step is in u-Direction
		float deltaVEye = (float)(veEye-vaEye)/nEye;									//how big a single step is in v-Direction
		
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
			for(int i = 0; i<mEye; i++){
			for(int j = 0; j<nEye; j++){
			
			//declares corners of a facet	
			u_iEye 		= uaEye + i * deltaUEye;
			u_i_1Eye 	= u_iEye + deltaUEye;
			v_jEye 		= vaEye + j * deltaVEye;
			v_j_1Eye 	= v_jEye + deltaVEye;
				Vector3f normal = Utils.normalVector(
					new Vector3f(xEye(u_iEye,v_jEye), yEye(u_iEye,v_jEye), zEye(u_iEye, v_jEye)),
					new Vector3f(xEye(u_i_1Eye,v_jEye), yEye(u_i_1Eye,v_jEye), zEye(u_i_1Eye, v_jEye)),
					new Vector3f(xEye(u_iEye,v_j_1Eye), yEye(u_iEye,v_j_1Eye), zEye(u_iEye, v_j_1Eye)));
			
			//builds a facet
			glBegin(GL_POLYGON);
				glNormal3f(normal.x, normal.y, normal.z);
				glVertex3f(xEye(u_iEye,v_jEye),			yEye(u_iEye,v_jEye), 		zEye(u_iEye, v_jEye));
				glVertex3f(xEye(u_i_1Eye,v_jEye),		yEye(u_i_1Eye,v_jEye), 		zEye(u_i_1Eye, v_jEye));
				glVertex3f(xEye(u_i_1Eye,v_j_1Eye),		yEye(u_i_1Eye,v_j_1Eye),	zEye(u_i_1Eye, v_j_1Eye));
				glVertex3f(xEye(u_iEye,v_j_1Eye),		yEye(u_iEye,v_j_1Eye), 		zEye(u_iEye, v_j_1Eye));					
			glEnd();
			
			}
		}
	}
	
	private float xEye(float u, float v){			
		return (float)(aHead* Math.cos(u)*Math.cos(v));		
	}
	
	private float yEye(float u, float v){			
		return (float)(bHead*Math.sin(v));
	}	
	
	private float zEye(float u, float v){			
		return (float)(cHead* Math.sin(u)*Math.cos(v));
	}
	
	private void setUaEye(float a){
		this.uaEye = a;
	}
	
	private void setUeEye(float b) {
		this.ueEye = b;		
	}
}




package character;

//Imports
import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector3f;

import engine.Materials;
import engine.Utils;

public class Head {
		
		// private Variablen
			int mHead = 30;															//u-Schritte
			int nHead = 30;															//v-Schritte
			float aHead = 1.5f;														//x-Achsenabschnitt
			float bHead = 1.0f;														//y-Achsenabschnitt
			float cHead = 1.2f;														//z-Achsenabschnitt
			float u_iHead , u_i_1Head , v_jHead , v_j_1Head;									//Eckpunkte einer Facette
			float 	uaHead = (float) 0, ueHead = (float)(2*Math.PI),					//Anfang und Ende des u-Bereichs
					vaHead = (float) -(Math.PI/2.), veHead = (float)(Math.PI/2.);		//Anfang und Ende des v-Bereichs
			float deltaUHead = (float)(ueHead-uaHead)/mHead;									//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
			float deltaVHead = (float)(veHead-vaHead)/nHead;									//wie gro� ein einzelner Teilschritt sein muss in v-Richtung

		public void drawHead()
		{	
			glPushMatrix();
				Materials.materialBigHeroOne();
				doHead();
				glPushMatrix();
//					glTranslatef(-a/2.f, 0.2f, c/2.f+0.25f);
//					doEye();
				glPopMatrix();
				glPushMatrix();
					glTranslatef(xConnection(ueConnection, veConnection), yConnection(ueConnection, veConnection), zConnection(ueConnection, veConnection));
					glRotatef(10, 0, 1, 1);
					Materials.materialEmerald();
					doEye();
				glPopMatrix();
				glPushMatrix();
					glTranslatef(0, 0, 0.001f);
					Materials.materialEmerald();
					doConnection();
				glPopMatrix();
			glPopMatrix();
		}
		
		private void doHead()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

			for(int i = 0; i<mHead; i++){
				for(int j = 0; j<nHead; j++){
				
				//Eckpunkte einer Facette deklarieren	
				u_iHead 	= uaHead + i * deltaUHead;
				u_i_1Head 	= u_iHead + deltaUHead;
				v_jHead 	= vaHead + j * deltaVHead;
				v_j_1Head 	= v_jHead + deltaVHead;
				
				Vector3f normal = Utils.normalVector(
						new Vector3f(xHead(u_iHead,v_jHead), yHead(u_iHead,v_jHead), zHead(u_iHead, v_jHead)),
						new Vector3f(xHead(u_i_1Head,v_jHead), yHead(u_i_1Head,v_jHead), zHead(u_i_1Head, v_jHead)),
						new Vector3f(xHead(u_iHead,v_j_1Head), yHead(u_iHead,v_j_1Head), zHead(u_iHead, v_j_1Head))).mul(-1);
				
				//Erstellung einer Facette
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
		
		
		int mConnection = 5;															//u-Schritte
		int nConnection = 2;															//v-Schritte
		float u_iConnection , u_i_1Connection , v_jConnection , v_j_1Connection;							//Eckpunkte einer Facette
		float 	uaConnection = (float) (3*Math.PI/8), ueConnection = (float) (5*Math.PI/8) ,				//Anfang und Ende des u-Bereichs
				vaConnection =  (float) (-Math.PI/100)+0.25f, veConnection = (float)(Math.PI/100)+0.25f;							//Anfang und Ende des v-Bereichs
		float deltaUConnection = (float)(ueConnection-uaConnection)/mConnection;							//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
		float deltaVConnection = (float)(veConnection-vaConnection)/nConnection;							//wie gro� ein einzelner Teilschritt sein muss in v-Richtung
				
		private void doConnection()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

			for(int i = 0; i<mConnection; i++){
				for(int j = 0; j<nConnection; j++){
				
				//Eckpunkte einer Facette deklarieren	
				u_iConnection 		= uaConnection + i * deltaUConnection;
				u_i_1Connection 	= u_iConnection + deltaUConnection;
				v_jConnection 		= vaConnection + j * deltaVConnection;
				v_j_1Connection 	= v_jConnection + deltaVConnection;
				
				Vector3f normal = Utils.normalVector(
						new Vector3f(xConnection(u_iConnection,v_jConnection), yConnection(u_iConnection,v_jConnection), zConnection(u_iConnection, v_jConnection)),
						new Vector3f(xConnection(u_i_1Connection,v_jConnection), yConnection(u_i_1Connection,v_jConnection), zConnection(u_i_1Connection, v_jConnection)),
						new Vector3f(xConnection(u_iConnection,v_j_1Connection), yConnection(u_iConnection,v_j_1Connection), zConnection(u_iConnection, v_j_1Connection)));
				
				//Erstellung einer Facette
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
		
		int mEye = 30;														//u-Schritte
		int nEye = 30;														//v-Schritte
		float u_iEye , u_i_1Eye , v_jEye , v_j_1Eye;						//Eckpunkte einer Facette
		float 	uaEye = (float) (0), ueEye = (float) (2*Math.PI) ,						//Anfang und Ende des u-Bereichs
				vaEye = (float)(0), veEye = (float)1;						//Anfang und Ende des v-Bereichs
		float deltaUEye = (float)(ueEye-uaEye)/mEye;						//wie gro� ein einzelner Teilschritt sein muss in u-Richtung
		float deltaVEye = (float)(veEye-vaEye)/nEye;						//wie gro� ein einzelner Teilschritt sein muss in v-Richtung
		float rEye = 0.001f;													//Radius 
		float sEye = 0.15f;
		float hEye = (float)(Math.sqrt(xConnection(ueConnection, veConnection)*xConnection(ueConnection, veConnection)
					+ yConnection(ueConnection, veConnection)*yConnection(ueConnection, veConnection)
					+ zConnection(ueConnection, veConnection)*zConnection(ueConnection, veConnection)));
		
		
		private void doEye()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

			for(int i = 0; i<mEye; i++){
				for(int j = 0; j<nEye; j++){
				
				//Eckpunkte einer Facette deklarieren	
				u_iEye 		= uaEye + i * deltaUEye;
				u_i_1Eye 	= u_iEye + deltaUEye;
				v_jEye 		= vaEye + j * deltaVEye;
				v_j_1Eye 	= v_jEye + deltaVEye;
				
				u_iHead 	= uaHead + i * deltaUHead;
				u_i_1Head 	= u_iHead + deltaUHead;
				v_jHead 	= vaHead + j * deltaVHead;
				v_j_1Head 	= v_jHead + deltaVHead;
				
				Vector3f normal = Utils.normalVector(
						new Vector3f(xEye(u_iEye,v_jEye), yEye(u_iEye,v_jEye), zEye(u_iEye, v_jEye)),
						new Vector3f(xEye(u_i_1Eye,v_jEye), yEye(u_i_1Eye,v_jEye), zEye(u_i_1Eye, v_jEye)),
						new Vector3f(xEye(u_iEye,v_j_1Eye), yEye(u_iEye,v_j_1Eye), zEye(u_iEye, v_j_1Eye)));
				
				//Erstellung einer Facette
				glBegin(GL_POLYGON);
					glNormal3f(normal.x, normal.y, normal.z);
					glVertex3f(xEye(u_iEye,v_jEye),			yEye(u_iEye,v_jEye), 		zEye(u_iEye, v_jEye));
					glVertex3f(xEye(u_i_1Eye,v_jEye),		yEye(u_i_1Eye,v_jEye), 		zEye(u_i_1Eye, v_jEye));
					glVertex3f(xEye(u_iEye,v_j_1Eye),		yEye(u_iEye,v_j_1Eye), 		zEye(u_iEye, v_j_1Eye));
					glVertex3f(xEye(u_i_1Eye,v_j_1Eye),		yEye(u_i_1Eye,v_j_1Eye),	zEye(u_i_1Eye, v_j_1Eye));
				glEnd();
				
//				glBegin(GL_POLYGON);
//					for(int i = 0; i < mEye; i++){
//						glVertex2f((float)(sEye*Math.cos(i*2*Math.PI/mEye)), (float)(sEye*Math.sin(i*2*Math.PI/mEye)));
//					}
//				glEnd();
				}
			}
		}
		
		private float xEye(float u, float v){
			
//			return (float)(rEye* Math.cos(u) + v*(sEye - rEye)*Math.cos(u));
//			return (float)(a* Math.cos(u)*Math.cos(v));
			return (float)(sEye * Math.cos(u));

		
		}
		
		private float yEye(float u, float v){
			
//			return (float)(rEye*Math.sin(u) + v*(sEye - rEye)*Math.sin(u));
//			return (float)(b*Math.sin(v));
			return (float)(sEye * Math.sin(u));


		}	
		
		private float zEye(float u, float v){
			
//			return (float)(Math.sqrt((x(u,v)*x(u,v)) + (y(u,v)*y(u,v)) + (z(u,v)*z(u,v))));
			return hEye;
//			return (float)(0.05* Math.sin(u)*Math.cos(v));

		}
}




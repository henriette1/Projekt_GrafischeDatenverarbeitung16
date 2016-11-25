package character;

//Imports
import static org.lwjgl.opengl.GL11.*;

public class Head {
		
		// private Variablen
			int m = 30;															//u-Schritte
			int n = 30;															//v-Schritte
			float a = 1.5f;														//x-Achsenabschnitt
			float b = 0.75f;														//y-Achsenabschnitt
			float c = 1.2f;														//z-Achsenabschnitt
			float u_i , u_i_1 , v_j , v_j_1;									//Eckpunkte einer Facette
			float 	ua = (float) 0, ue = (float)(2*Math.PI),					//Anfang und Ende des u-Bereichs
					va = (float) -(Math.PI/2.), ve = (float)(Math.PI/2.);		//Anfang und Ende des v-Bereichs
			float deltaU = (float)(ue-ua)/m;									//wie groﬂ ein einzelner Teilschritt sein muss in u-Richtung
			float deltaV = (float)(ve-va)/n;									//wie groﬂ ein einzelner Teilschritt sein muss in v-Richtung
			float r = 1;														//Radius 
			

		public void drawHead()
		{	
			glPushMatrix();
				doHead();
				glPushMatrix();
//					glTranslatef(-a/2.f, 0.2f, c/2.f+0.25f);
//					doEye();
				glPopMatrix();
				glPushMatrix();
					glTranslatef(xConnection(ueConnection, veConnection), yConnection(ueConnection, veConnection), zConnection(ueConnection, veConnection));
					glRotatef(10, 0, 1, 1);
					doEye();
				glPopMatrix();
				glPushMatrix();
					glTranslatef(0, 0, 0.001f);
					doConnection();
				glPopMatrix();
			glPopMatrix();
		}
		
		private void doHead()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			glColor3f(1,1,1);
			for(int i = 0; i<m; i++){
				for(int j = 0; j<n; j++){
				
				//Eckpunkte einer Facette deklarieren	
				u_i 	= ua + i * deltaU;
				u_i_1 	= u_i + deltaU;
				v_j 	= va + j * deltaV;
				v_j_1 	= v_j + deltaV;
				
				//Erstellung einer Facette
				glBegin(GL_TRIANGLE_STRIP);
					glVertex3f(x(u_i,v_j),			y(u_i,v_j), 		z(u_i, v_j));
					glVertex3f(x(u_i_1,v_j),		y(u_i_1,v_j), 		z(u_i_1, v_j));
					glVertex3f(x(u_i,v_j_1),		y(u_i,v_j_1), 		z(u_i, v_j_1));
					glVertex3f(x(u_i_1,v_j_1),		y(u_i_1,v_j_1),		z(u_i_1, v_j_1));
				glEnd();
				}
			}
		}
		
		private float x(float u, float v){
				
			return (float)(a* Math.cos(u)*Math.cos(v));
		
		}
		
		private float y(float u, float v){
			
			return (float)(b*Math.sin(v));
		}	
		
		private float z(float u, float v){
			
			return (float)(c* Math.sin(u)*Math.cos(v));
		}
		
		
		int mEye = 15;														//u-Schritte
		int nEye = 15;														//v-Schritte
		float u_iEye , u_i_1Eye , v_jEye , v_j_1Eye;						//Eckpunkte einer Facette
		float 	uaEye = (float) (0), ueEye = (float) (2*Math.PI) ,						//Anfang und Ende des u-Bereichs
				vaEye = (float)(-Math.PI/2.), veEye = (float)(Math.PI/2.);						//Anfang und Ende des v-Bereichs
		float deltaUEye = (float)(ueEye-uaEye)/mEye;						//wie groﬂ ein einzelner Teilschritt sein muss in u-Richtung
		float deltaVEye = (float)(veEye-vaEye)/nEye;						//wie groﬂ ein einzelner Teilschritt sein muss in v-Richtung
		float rEye = 0.15f;													//Radius 
		
		
		private void doEye()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			glColor3f(0,1,1);

			for(int i = 0; i<mEye; i++){
				for(int j = 0; j<nEye; j++){
				
				//Eckpunkte einer Facette deklarieren	
				u_iEye 		= uaEye + i * deltaUEye;
				u_i_1Eye 	= u_iEye + deltaUEye;
				v_jEye 		= vaEye + j * deltaVEye;
				v_j_1Eye 	= v_jEye + deltaVEye;
				
				u_i 	= ua + i * deltaU;
				u_i_1 	= u_i + deltaU;
				v_j 	= va + j * deltaV;
				v_j_1 	= v_j + deltaV;
				
				//Erstellung einer Facette
				glBegin(GL_TRIANGLE_STRIP);
					glVertex3f(i*xEye(u_iEye,v_jEye)/(nEye),			j*yEye(u_iEye,v_jEye)/(nEye), 		zEye(u_i, v_j));
					glVertex3f(i*xEye(u_i_1Eye,v_jEye)/(nEye),		(j+1)*yEye(u_i_1Eye,v_jEye)/(nEye), 		zEye(u_i_1, v_j));
					glVertex3f((i+1)*xEye(u_i_1Eye,v_j_1Eye)/(nEye),		(j+1)*yEye(u_i_1Eye,v_j_1Eye)/(nEye),	zEye(u_i_1, v_j_1));
					glVertex3f((i+1)*xEye(u_iEye,v_j_1Eye)/(nEye),		j*yEye(u_iEye,v_j_1Eye)/(nEye), 		zEye(u_i, v_j_1));
				glEnd();
				}
			}
		}
		
		private float xEye(float u, float v){
			
			return (float)(rEye* Math.cos(u));
//			return (float)(a* Math.cos(u)*Math.cos(v));

		
		}
		
		private float yEye(float u, float v){
			
			return (float)(rEye*Math.sin(u));
//			return (float)(b*Math.sin(v));


		}	
		
		private float zEye(float u, float v){
			
//			return (float)(Math.sqrt((x(u,v)*x(u,v)) + (y(u,v)*y(u,v)) + (z(u,v)*z(u,v))));
			return .25f;
//			return (float)(0.05* Math.sin(u)*Math.cos(v));

		}
		
		int mConnection = 5;															//u-Schritte
		int nConnection = 2;															//v-Schritte
		float u_iConnection , u_i_1Connection , v_jConnection , v_j_1Connection;							//Eckpunkte einer Facette
		float 	uaConnection = (float) (3*Math.PI/8), ueConnection = (float) (5*Math.PI/8) ,				//Anfang und Ende des u-Bereichs
				vaConnection =  (float) (-Math.PI/100)+0.25f, veConnection = (float)(Math.PI/100)+0.25f;							//Anfang und Ende des v-Bereichs
		float deltaUConnection = (float)(ueConnection-uaConnection)/mConnection;							//wie groﬂ ein einzelner Teilschritt sein muss in u-Richtung
		float deltaVConnection = (float)(veConnection-vaConnection)/nConnection;							//wie groﬂ ein einzelner Teilschritt sein muss in v-Richtung
				
		private void doConnection()
		{
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			glColor3f(1,0,0);
			for(int i = 0; i<mConnection; i++){
				for(int j = 0; j<nConnection; j++){
				
				//Eckpunkte einer Facette deklarieren	
				u_iConnection 		= uaConnection + i * deltaUConnection;
				u_i_1Connection 	= u_iConnection + deltaUConnection;
				v_jConnection 		= vaConnection + j * deltaVConnection;
				v_j_1Connection 	= v_jConnection + deltaVConnection;
				
				//Erstellung einer Facette
				glBegin(GL_TRIANGLE_STRIP);	
					glVertex3f(xConnection(u_iConnection,v_jConnection),				yConnection(u_iConnection,v_jConnection), 			zConnection(u_iConnection, v_jConnection));
					glVertex3f(xConnection(u_i_1Connection,v_jConnection),			yConnection(u_i_1Connection,v_jConnection), 			zConnection(u_i_1Connection, v_jConnection));
					glVertex3f(xConnection(u_iConnection,v_j_1Connection),			yConnection(u_iConnection,v_j_1Connection), 			zConnection(u_iConnection, v_j_1Connection));
					glVertex3f(xConnection(u_i_1Connection,v_j_1Connection),			yConnection(u_i_1Connection,v_j_1Connection),			zConnection(u_i_1Connection, v_j_1Connection));
				glEnd();
				}
			}
		}
		
		private float xConnection(float u, float v){
			
			return (float)(a* Math.cos(u)*Math.cos(v));		
		}
		
		private float yConnection(float u, float v){
			
			return (float)(b*Math.sin(v));

		}	
		
		private float zConnection(float u, float v){
			
			return (float)(c* Math.sin(u)*Math.cos(v));
		}
}




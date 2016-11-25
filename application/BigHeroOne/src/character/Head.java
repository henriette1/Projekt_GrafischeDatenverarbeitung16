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
					glTranslatef(-a/2.f, 0, c/2.f+0.4f);
					doEye();
				glPopMatrix();
				glPushMatrix();
					glTranslatef(a/2.f, 0, c/2.f+0.4f);
					doEye();
				glPopMatrix();
				glPushMatrix();
					glTranslatef(0, 0, c/2.f+1f);
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
		float 	uaEye = (float) (-Math.PI/2.), ueEye = (float) (Math.PI/2.) ,						//Anfang und Ende des u-Bereichs
				vaEye = 0, veEye = (float)(Math.PI);						//Anfang und Ende des v-Bereichs
		float deltaUEye = (float)(ueEye-uaEye)/mEye;						//wie groﬂ ein einzelner Teilschritt sein muss in u-Richtung
		float deltaVEye = (float)(veEye-vaEye)/nEye;						//wie groﬂ ein einzelner Teilschritt sein muss in v-Richtung
		float rEye = 0.4f;													//Radius 
		
		
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
				
				//Erstellung einer Facette
				glBegin(GL_TRIANGLE_STRIP);
					glVertex3f(xEye(u_iEye,v_jEye),			yEye(u_iEye,v_jEye), 		zEye(u_iEye, v_jEye));
					glVertex3f(xEye(u_i_1Eye,v_jEye),		yEye(u_i_1Eye,v_jEye), 		zEye(u_i_1Eye, v_jEye));
					glVertex3f(xEye(u_iEye,v_j_1Eye),		yEye(u_iEye,v_j_1Eye), 		zEye(u_iEye, v_j_1Eye));
					glVertex3f(xEye(u_i_1Eye,v_j_1Eye),		yEye(u_i_1Eye,v_j_1Eye),	zEye(u_i_1Eye, v_j_1Eye));
				glEnd();
				}
			}
		}
		
		private float xEye(float u, float v){
			
			return (float)(rEye*Math.sin(u)*Math.cos(v));
		
		}
		
		private float yEye(float u, float v){
			
			return (float)(rEye*Math.sin(u)*Math.sin(v));

		}	
		
		private float zEye(float u, float v){
			
			return (float)(rEye*Math.cos(u));
		}
		
		
		private void doConnection()
		{
			
			glLineWidth(3);
			glBegin(GL_LINES);
				
				glColor3f(0,0,1);
				glVertex3f(-a/2.f+rEye, 0, 0);
				glVertex3f(a/2.f-rEye, 0, 0);
			glEnd();
			glLineWidth(1);
		}
}




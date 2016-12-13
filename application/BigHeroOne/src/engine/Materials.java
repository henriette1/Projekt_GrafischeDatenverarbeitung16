package engine;

import static org.lwjgl.opengl.GL11.*;

public class Materials {
	
	public static void materialCave() {
		float mat_ambient[] = {.35f, .35f, .35f, 1.f};
		float mat_diffuse[] = {.27f, .25f, .25f, 1.f};
		float mat_specluar[] = {.13f, .13f, .13f, 1.f};
		float shininess = 10.2f;
		glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, mat_ambient);
		glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, mat_diffuse);
		glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, mat_specluar);
		
		glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess);
	}
	
	public static void materialGround() {
		float mat_ambient[] = {0.25f, .25f, .25f, 1.f};
		float mat_diffuse[] = {.25f, .25f, .25f, 1.f};
		float mat_specluar[] = {.13f, .13f, .13f, 1.f};
		float shininess = 10.2f;
		glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, mat_ambient);
		glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
		glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specluar);
		
		glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess);
	}
	
	public static void materialBigHeroOne() {
		float mat_ambient[] = {0.05f, 0.05f, 0.05f, 1.f};
		float mat_diffuse[] = {.5f, .5f, 5.f, 1.f};
		float mat_specluar[] = {.7f, .7f, .7f, 1.f};
		float shininess = 50.078125f;
		glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, mat_ambient);
		glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, mat_diffuse);
		glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, mat_specluar);
		
		glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess);
	}

}

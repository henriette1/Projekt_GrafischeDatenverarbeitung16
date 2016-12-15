package engine;

import static org.lwjgl.opengl.GL11.*;

public class Materials {
	
	/*
	 * sets materialattributes for our Cave
	 */
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
	
	/*
	 * sets materialattributes for our Ground
	 */
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
	
	/*
	 * sets materialattributes for our PlayerMOdel
	 */
	public static void materialBigHeroOne() {
		float mat_ambient[] = {0.5f, 0.5f, 0.5f, 1.f};
		float mat_diffuse[] = {.5f, .5f, .5f, 1.f};
		float mat_specluar[] = {.7f, .7f, .7f, 1.f};
		float shininess = 0.25f * 128;
		glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, mat_ambient);
		glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, mat_diffuse);
		glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, mat_specluar);
		
		glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess);
	}
	
	public static void materialChrom() {
		float mat_ambient[] = {0.25f, 0.25f, 0.25f, 1.f};
		float mat_diffuse[] = {.4f, .4f, .4f, 1.f};
		float mat_specluar[] = {.77f, .77f, .77f, 1.f};
		float shininess = 76.8f;
		glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, mat_ambient);
		glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, mat_diffuse);
		glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, mat_specluar);
		
		glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess);
	}
	
	public static void materialEmerald() {
		float mat_ambient[] = {0.02f, 0.17f, 0.02f, .5f};
		float mat_diffuse[] = {.08f, .61f, .08f, .5f};
		float mat_specluar[] = {.63f, .73f, .63f, .5f};
		float shininess = 76.8f;
		glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, mat_ambient);
		glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, mat_diffuse);
		glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, mat_specluar);
		
		glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, shininess);
	}

}

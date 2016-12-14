package entities;

import character.MyCharacter;
import static org.lwjgl.opengl.GL11.*;
import engine.Materials;

public class Model {

private MyCharacter bigHeroSix = new MyCharacter();
	
	public Model() {
		
	}
	/*
	 * draws player model
	 */
	public void draw(float winkel) {
		glPushMatrix();
			Materials.materialBigHeroOne();
			bigHeroSix.draw(winkel);
		glPopMatrix();
	}
}

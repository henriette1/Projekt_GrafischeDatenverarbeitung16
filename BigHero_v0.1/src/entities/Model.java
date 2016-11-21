package entities;

import org.lwjgl.opengl.GL11;

public class Model {
	
	private BigHeroSix bigHeroSix = new BigHeroSix();
	
	public Model() {
		
	}
	/*
	 * draws player model
	 */
	public void draw() {
		bigHeroSix.draw();
	}
}

package entities;

import character.MyCharacter;

public class Model {

private MyCharacter bigHeroSix = new MyCharacter();
	
	public Model() {
		
	}
	/*
	 * draws player model
	 */
	public void draw(float winkel) {
		bigHeroSix.draw(winkel);
	}
}

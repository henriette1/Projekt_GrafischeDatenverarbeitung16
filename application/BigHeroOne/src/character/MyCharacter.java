package character;

//Imports
import static org.lwjgl.opengl.GL11.*;

import engine.Materials;

//import java.nio.FloatBuffer;
//import java.nio.IntBuffer;
//import org.lwjgl.BufferUtils; // to create a float buffer in place of the glfloat array


public class MyCharacter
{
	Head head = new Head();
	Body body = new Body();
	Leg rightLeg = new Leg();
	Leg leftLeg = new Leg();
	CompleteArm rightArm = new CompleteArm();
	CompleteArm leftArm = new CompleteArm();
	Finger rightHand = new Finger();
	

	
	public void draw(float winkel)
	{	
		glPushMatrix();
			glTranslatef(0,body.getHeight() + leftLeg.getHeight(),0);
			glRotatef(180, 0, 1, 0);
			head.drawHead();
		glPopMatrix();
		glPushMatrix();
			Materials.materialBigHeroOne();
			glTranslatef(0, leftLeg.getHeight(), 0);
			body.drawBody();	
		glPopMatrix();
		glPushMatrix();
			Materials.materialBigHeroOne();
			glTranslatef(-1.5f,2.6f,0);
			glTranslatef(0, 2.6f, 0);
			glRotatef(winkel, 1,0,0);
			glTranslatef(0, -2.6f, 0);
			leftLeg.drawLeg();
		glPopMatrix();
		
		glPushMatrix();
			Materials.materialBigHeroOne();
			glTranslatef(1.5f,2.6f,0);
			glTranslatef(0, 2.6f, 0);
			glRotatef(-winkel, 1,0,0);
			glTranslatef(0, -2.6f, 0);
			rightLeg.drawLeg();
		glPopMatrix();
		
		glPushMatrix();
			Materials.materialBigHeroOne();
			glTranslatef(-(body.rMiddleBody-0.49225f)-rightArm.getIntervalArm(), leftLeg.getHeight() + body.getHeight() - 0.75f - rightArm.getRadiusArm(), 0);
			glRotatef(180, 1, 0, 0);
			glRotatef(-90, 0, 0, 1);
			rightArm.drawCompleteArm();
		glPopMatrix();
		
		glPushMatrix();
			Materials.materialBigHeroOne();
			glTranslatef((body.rMiddleBody-0.49225f)+leftArm.getIntervalArm(), leftLeg.getHeight() + body.getHeight() - 0.75f - leftArm.getRadiusArm(), 0);
			glRotatef(90, 0, 0, 1);
			leftArm.drawCompleteArm();
		glPopMatrix();
	}
	
	public float getSize(){
		return rightLeg.getHeight() + body.getHeight() + head.bHead;
	}
	
	
}
package character;

//Imports
import static org.lwjgl.opengl.GL11.*;

//import java.nio.FloatBuffer;
//import java.nio.IntBuffer;
//import org.lwjgl.BufferUtils; // to create a float buffer in place of the glfloat array


public class MyCharacter
{
	Head head = new Head();
	Body body = new Body();
	Leg rightLeg = new Leg();
	Leg leftLeg = new Leg();
//	Arm rightArm = new Arm();
	CompleteArm rightArm = new CompleteArm();
	Arm leftArm = new Arm();
	Finger rightHand = new Finger();
	

	
	public void draw()
	{	
		
		glPushMatrix();
			glTranslatef(0,body.getHeight() + leftLeg.getHeight(),0);
//			head.drawHead();
		glPopMatrix();
		glPushMatrix();
			glTranslatef(0, leftLeg.getHeight(), 0);
//			body.drawBody();	
		glPopMatrix();
		glPushMatrix();
			glTranslatef(-1.5f,2.6f,0);
			glTranslatef(0, 2.6f, 0);
//			glRotatef(winkel, 1,0,0);
			glTranslatef(0, -2.6f, 0);
//			leftLeg.drawLeg();
		glPopMatrix();
		
		glPushMatrix();
			glTranslatef(1.5f,2.6f,0);
			glTranslatef(0, 2.6f, 0);
//			glRotatef(-winkel, 1,0,0);
			glTranslatef(0, -2.6f, 0);
//			rightLeg.drawLeg();
		glPopMatrix();
		
		glPushMatrix();
//			glTranslatef(-(body.rMiddleBody-0.49225f)-rightArm.intervalArm, leftLeg.getHeight() + body.getHeight() - 0.75f - rightArm.rArm, 0);
//			glRotatef(-90, 0, 0, 1);
//			rightArm.drawArm();
//			rightHand.drawFinger();
			glTranslatef(0,8,0);
			rightArm.drawCompleteArm();
		glPopMatrix();
		
		glPushMatrix();
			glTranslatef((body.rMiddleBody-0.49225f)+leftArm.intervalArm, leftLeg.getHeight() + body.getHeight() - 0.75f - leftArm.rArm, 0);
			glRotatef(90, 0, 0, 1);
//			leftArm.drawArm();
		glPopMatrix();
	}
	
	
}
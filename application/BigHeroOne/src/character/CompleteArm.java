package character;

import static org.lwjgl.opengl.GL11.*;

public class CompleteArm {
	
	Arm arm = new Arm();
	Finger finger  = new Finger();
	
	public void drawCompleteArm(){
		
		glPushMatrix();
			glTranslatef(0, arm.intervalArm, 0);
			glRotatef(-65, 0,0,1);
			glTranslatef(0, -arm.intervalArm, 0);
			glRotatef(90, 0,1,0);
			arm.drawArm();
			glTranslatef(-2*finger.rFinger, -(finger.intervalFinger/2+arm.intervalArm-arm.rHand), 0);
			finger.drawFinger();
		glPopMatrix();
	}
	
	public float getIntervalArm(){
		return arm.intervalArm;		
	}
	
	public float getRadiusArm(){
		return arm.rArm;
	}

}

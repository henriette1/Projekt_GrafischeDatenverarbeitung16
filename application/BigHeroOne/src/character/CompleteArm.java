package character;

import static org.lwjgl.opengl.GL11.*;

public class CompleteArm {
	
	Arm arm = new Arm();
	Finger finger  = new Finger();
	
	public void drawCompleteArm(){
		
		glPushMatrix();
			arm.drawArm();
			glTranslatef(-2*finger.rFinger, -(finger.intervalFinger/2+arm.intervalArm-arm.rHand), 0);
			finger.drawFinger();
		
	}

}

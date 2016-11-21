package entities;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_REPEAT;

import org.joml.Vector3f;

import engine.DisplayManager;


public class Player extends Entity{

	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed =0;
	

	public Player(Model model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	public void move() {
		super.increaseRotation(0, this.currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = this.currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
	}
	
	public void checkInputs(int key, int action) {
		this.currentSpeed = 0;
		this.currentTurnSpeed = 0;
		
		if ( key == GLFW_KEY_W && (action == GLFW_REPEAT || action == GLFW_PRESS)) {
			this.currentSpeed = -RUN_SPEED;
        }
		if ( key == GLFW_KEY_S && (action == GLFW_REPEAT || action == GLFW_PRESS) ) {	
        	this.currentSpeed = RUN_SPEED;
        }
		
        if ( key == GLFW_KEY_A && (action == GLFW_REPEAT || action == GLFW_PRESS) ) {
        	this.currentTurnSpeed = -TURN_SPEED;
        }
        if ( key == GLFW_KEY_D && (action == GLFW_REPEAT || action == GLFW_PRESS) ) {
        	this.currentTurnSpeed = TURN_SPEED;
        }        
        
	}
}

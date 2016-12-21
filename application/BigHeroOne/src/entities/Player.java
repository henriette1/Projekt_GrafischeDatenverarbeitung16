package entities;

import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

import engine.DisplayManager;

import entities.Terrain;

public class Player extends Entity {

	private static final float RUN_SPEED = 50; //speed of our player while walking
	private static final float TURN_SPEED = 190; // how fast can our player turn
	
	private float currentSpeed = 0;
	private float currentTurnSpeed =0;
	
	private float moveAngle = 0; // contains the angle for the walking direction
	int direction = 1; //1 for forwards, 0 for backwards
	private Terrain terrain; // entity object for our terrain
	
	private float[][] HEIGHT_MAP;		
	private float[][] COLLISION_MAP;		// Eintrag 1, falls Feld durch Objekt belegt, 0 sonst

	Vector3f movementDirection;
	
	/*
	 * Constructor calls the inherited Constructor of entities 
	 */
	public Player(Model model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	/*
	 * Method which calls all of our calculation methods to set our new position for each frame
	 */
	public void move(Vector3f movementDirection) {
		super.increaseRotation(0, this.currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = this.currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);
		this.movementDirection = movementDirection.normalize().mul(7.f);
	}
	
	/*
	 * method checks keyboard inputs, which enables us to walk and turn
	 */
	public void checkInputs(int key, int action) {
		HEIGHT_MAP = setHeightMap();
		COLLISION_MAP = initiateCollision();		
		this.currentSpeed = 0;
		this.currentTurnSpeed = 0;
		
		
		if ( key == GLFW_KEY_W && (action == GLFW_REPEAT)) {
			if(COLLISION_MAP[(int)(getPosition().x + movementDirection.x)][(int)(getPosition().z + movementDirection.z)] == 0){
				if(getMoveAngle() >= 30 && direction == 1)
				{
					direction = -1;
				}
				else if(getMoveAngle() <= -30 && direction == -1)
				{
					direction = 1;
				}			
				setMoveAngle(getMoveAngle()+(direction*5));
				this.currentSpeed = -RUN_SPEED;
			}
        }
		
		if ( key == GLFW_KEY_S && (action == GLFW_REPEAT) ) {	
			if(COLLISION_MAP[(int)(getPosition().x - movementDirection.x)][(int)(getPosition().z - movementDirection.z)] == 0){
				if(getMoveAngle() >= 30 && direction == 1)
				{
					direction = -1;
				}
				else if(getMoveAngle() <= -30 && direction == -1)
				{
					direction = 1;
				}			
				setMoveAngle(getMoveAngle()+(direction*5));
	        	this.currentSpeed = RUN_SPEED;
			}
        }
		
        if ( key == GLFW_KEY_A && (action == GLFW_REPEAT || action == GLFW_PRESS) ) {
        	this.currentTurnSpeed = -TURN_SPEED;
        }
        if ( key == GLFW_KEY_D && (action == GLFW_REPEAT || action == GLFW_PRESS) ) {
        	this.currentTurnSpeed = TURN_SPEED;
        }        
        
	}

	public float getMoveAngle() {
		return moveAngle;
	}

	public void setMoveAngle(float moveAngle) {
		this.moveAngle = moveAngle;
	}
	
	/*
	 * creates a matrix which contains boarders of our cave. (We can't walk into 1)
	 */
	private float[][] initiateCollision(){
		float[][] helpMap = new float[513][513];
	 	for(int j= 1; j<helpMap.length-1; j++){
	 		for(int i=1; i<helpMap[1].length-1; i++){
	 			if(HEIGHT_MAP[i][j] > -15 && HEIGHT_MAP[i][j] < -4){
	 				helpMap[i][j]=1;
	 			}
	 		}
	 	}
	 	return helpMap;
	}
	
	private float[][] setHeightMap(){
		return terrain.getHeightMapCoords();
	}


	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
		
	}
}

package entities;

import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.DisplayManager;

public class Camera {
	
	private static final float TERRAIN_HEIGHT = 0;

	public static float dxRotate; //contains value of left mousebutton Callback
	public static float dyPitch; //contains the value of right mousebutton callback
	public static float dyZoom; // contains the value of ScrollCallback
	private Vector2f mousePosStart = new Vector2f(0,0); //starting position of our cursor
	private float distanceFromPlayer = 20; // distance between camera and player
	private float angleAroundPlayer = 0; //angle around player (free camera movement on a circle around the Player

	private Vector3f position = new Vector3f(0, 0, 0); //default Position
	private float pitch =50; // view angle (y)
	private float yaw = 0; // view angle (x/y)

	private Player player; //Player Object

	/*
	 * generates a player object
	 */
	public Camera(Player player) {
		this.player = player;
	}
	
	/*
	 * sets the current Player Object
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/*
	 * calls the calculations for the cameraposition
	 */
	public void move() {
		calculateZoom();
		calculateAngleAroundPlayer();
		calculatePitch();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotY() * this.angleAroundPlayer);
	}

	/*
	 * returns the current Player position
	 */
	public Vector3f getPosition() {
		return position;
	}
	
	/*
	 * returns the current Pitch, which is the Angle between the floor and the camera direction (y)
	 */
	public float getPitch() {
		return pitch;
	}

	/*
	 * returns the yaw which is the angle between the player and the camera direction (x-z)
	 */
	public float getYaw() {
		return yaw;
	}

	/*
	 * calculates the new cameraposition after changing the angle, the distance or the height
	 */
	private void calculateCameraPosition(float horizDistance, float verticDistance) {
		float phi = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(phi)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(phi)));
		position.x = player.getPosition().x + offsetX;
		position.z = player.getPosition().z + offsetZ;
		position.y = player.getPosition().y + verticDistance;
		if (position.y < TERRAIN_HEIGHT) {
			position.y = TERRAIN_HEIGHT;
		}
	}

	/*
	 * calculates the horizontal distance between player and Camera
	 */
	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}

	/*
	 * calculates the vertical distance between player and Camera
	 */
	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}

	/*
	 * calculates the length of the Vector between camera and player
	 */
	private void calculateZoom() {
		if (distanceFromPlayer < 2f) {
			distanceFromPlayer = 2f;
		}
		else if (distanceFromPlayer > 100f) {
			distanceFromPlayer = 100f;
		}
		else {
			float zoomLevel = dyZoom * 0.5f;
			distanceFromPlayer -= zoomLevel;
			dyZoom = 0;
		}
	}

	/*
	 * calculates the camera's height
	 */
	public void calculatePitch() {
		float yPos;
		float yOffset;
		yPos = (float) dyPitch;
		yOffset = (float) yPos - mousePosStart.y;
		if(DisplayManager.isMouseRight()) {
			float pitchChange = (float) (yOffset * 0.1f);
			pitch -= pitchChange;
		}
		
		mousePosStart.y = yPos;
	}

	/*
	 * calculates the angle of the Vector between Camera and Player on the x-y
	 */
	public void calculateAngleAroundPlayer() {
		float xPos;  // current mouse position
	    float xOffset; // mouse movement
	    xPos = (float)dxRotate;
	    xOffset = xPos - mousePosStart.x;
	    if(DisplayManager.isMouseLeft()) {
	    	float angleChange = (float)(xOffset * 0.3f);
	    	angleAroundPlayer -= angleChange;
	    }
		mousePosStart.x = xPos;
	}

}

package entities;

import org.joml.Vector2f;
import org.joml.Vector3f;

import engine.DisplayManager;

public class Camera {

	private static final float TERRAIN_HEIGHT = 0;

	public static float dxRotate;
	public static float dyPitch;
	public static float dyZoom;
	private Vector2f mousePosStart = new Vector2f(0,0);
	private float distanceFromPlayer = 20;
	private float angleAroundPlayer = 0;

	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch =50; // view angle
	private float yaw = 0;
	private float roll;

	private Player player;

	public Camera(Player player) {
		this.player = player;

	}
	
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
		float horizontalDistance = calculateHorizonatalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotY() * this.angleAroundPlayer);
	}

	public Vector3f getPosition() {
		return position;
	}
	

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
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

	private float calculateHorizonatalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}

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
	 * calculates the camera height
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
	 * calculates the angle of the Vector between Camera ant Player on the x-y
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

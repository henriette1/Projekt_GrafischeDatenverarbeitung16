package entities;

import org.joml.Vector3f;

public class Entity {

	private Model model; // model object which later contains our drawn entity 
	public Vector3f position; // position of our entity model
	private float rotX, rotY, rotZ; // rotation around different axes 
	private float scale; //scale of the player model
	
	/*
	 * Constructor which is inheritanced to all childclasses.
	 */
	public Entity(Model model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super();
		this.setModel(model);
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}
	
	/*
	 * method which increases or decreases the entities current position
	 */
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	/*
	 * method which increases or decreases the entities current rotation
	 */
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}

	/*
	 * Method which returns the current entities position
	 */
	public Vector3f getPosition() {
		return position;
	}

	/*
	 * Method which sets the current entities position
	 */
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}

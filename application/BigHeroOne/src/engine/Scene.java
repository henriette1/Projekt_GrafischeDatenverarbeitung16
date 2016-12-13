package engine;

import java.nio.FloatBuffer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

import entities.Camera;
import entities.Terrain;
import entities.Player;

public class Scene {
	// private Variablen

	private float aspect = 1;
	private float fovy = 90;
	private float zNear = .1f;
	private float zFar = Float.POSITIVE_INFINITY;

	private Lightning spotLight = new Lightning(GL_LIGHT0);
	private Lightning pointLight = new Lightning(GL_LIGHT1);

	private Vector3f secondOrthogonalVector = new Vector3f(0, 1, 0);
	private Matrix4f m = new Matrix4f();
	private FloatBuffer fb = BufferUtils.createFloatBuffer(16);

	private Player player;
	private Camera camera = new Camera(player);
	private Terrain terrain;

	/*
	 * sets up our scene
	 */
	public void initGLState() {

		m.setPerspective(fovy, aspect, zNear, zFar);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		m.get(fb);

		glLoadMatrixf(fb);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		/* Smooth-Shading soll benutzt werden */
		glEnable(GL_NORMALIZE);
		glShadeModel(GL_SMOOTH);
		initLighting();
	}

	/*
	 * renders our models and defines the cameraposition
	 */
	public void renderLoop() {
		player.move(calculateVectorDirectionBetweenEyeAndCenter());
		camera.move();
		m.setLookAt(camera.getPosition(), player.getPosition(),
				calculateUpVectorOfCameraPosition(secondOrthogonalVector));
		m.get(fb);

		glLoadMatrixf(fb);
		
		glPushMatrix();
//			glLoadIdentity();
			updateSpotLight();
			updatePointLight();
		glPopMatrix();
		
		glPushMatrix();
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
			terrain.generateCave();
			terrain.generateGround();
		glPopMatrix();

		glPushMatrix();
			glTranslatef(player.getPosition().x, player.getPosition().y, player.getPosition().z);
			glRotatef(player.getRotY(), 0, 1, 0);
			player.getModel().draw(player.getMoveAngle());
		glPopMatrix();
	}

	private void initLighting() {
		
		
		float lmodel_ambient[] = { 1f, 1f, 1f, 1.0f };
		// Hintergrundbeleuchtung definieren
		glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);

		// Eine weitere Lichtquellen definieren
		
		glEnable(GL_LIGHTING);

		spotLight.turnLightOn();
		pointLight.turnLightOn();
	}
	

	private void updateSpotLight() {
		float light0_position[] = { player.getPosition().x, 16.0f, player.getPosition().z, 1.0f }; // x,y,z,1
		float light0_diff_spec[] = { 1f, 1f, 1f, 1.0f };
		float light0_direction[] = {calculateVectorDirectionBetweenEyeAndCenter().x,calculateVectorDirectionBetweenEyeAndCenter().y,calculateVectorDirectionBetweenEyeAndCenter().z, 1f};
		
		// Eine weitere Lichtquellen definieren
		spotLight.setPosition(light0_position);
		spotLight.setCutoff(45.f);
		spotLight.setDirection(light0_direction);
		spotLight.setDiffAndSpek(light0_diff_spec);
		
		glEnable(GL_LIGHTING);
	}
	private void updatePointLight() {
		float light1_position[] = { player.getPosition().x, 16.0f, player.getPosition().z, 1.0f }; // x,y,z,1
		float light1_diff_spec[] = { 1f, 1f, 1f, 1.0f };
		
		// Eine weitere Lichtquellen definieren
		pointLight.setPosition(light1_position);
		pointLight.setDiffAndSpek(light1_diff_spec);
		
		glEnable(GL_LIGHTING);
	}
	
	/*
	 * calculates the vector showing to the center from cameraposition
	 */
	private Vector3f calculateVectorDirectionBetweenEyeAndCenter() {
		Vector3f br = new Vector3f();

		br.x = player.getPosition().x - camera.getPosition().x;
		br.y = player.getPosition().y - camera.getPosition().y;
		br.z = player.getPosition().z - camera.getPosition().z;

		return br;
	}

	/*
	 * calculates the vectorproduct which delivers the up vector for our camera
	 * (always in y-direction
	 */
	private Vector3f calculateUpVectorOfCameraPosition(Vector3f orthVector) {

		Vector3f interimResult = new Vector3f();
		interimResult.x = calculateVectorDirectionBetweenEyeAndCenter().y * orthVector.z
				- calculateVectorDirectionBetweenEyeAndCenter().z * orthVector.y;
		interimResult.y = calculateVectorDirectionBetweenEyeAndCenter().z * orthVector.x
				- calculateVectorDirectionBetweenEyeAndCenter().x * orthVector.z;
		interimResult.z = calculateVectorDirectionBetweenEyeAndCenter().x * orthVector.y
				- calculateVectorDirectionBetweenEyeAndCenter().y * orthVector.x;

		Vector3f normalVector = new Vector3f();
		normalVector.x = calculateVectorDirectionBetweenEyeAndCenter().z * interimResult.y
				- calculateVectorDirectionBetweenEyeAndCenter().y * interimResult.z;
		normalVector.y = calculateVectorDirectionBetweenEyeAndCenter().x * interimResult.z
				- calculateVectorDirectionBetweenEyeAndCenter().z * interimResult.x;
		normalVector.z = calculateVectorDirectionBetweenEyeAndCenter().y * interimResult.x
				- calculateVectorDirectionBetweenEyeAndCenter().x * interimResult.y;

		normalVector = normalVector.normalize();

		return normalVector;
	}
	
	public void disableLightning() {
		pointLight.turnLightOff();
		spotLight.turnLightOff();
	}
	
	public void enableLightning() {
		pointLight.turnLightOn();
		spotLight.turnLightOn();
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public void setTerrain(Terrain cave) {
		this.terrain = cave;
	}

	public Terrain getTerrain() {
		return this.terrain;
	}
}

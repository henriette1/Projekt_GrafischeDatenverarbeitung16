package engine;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.*;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

import entities.Camera;
import entities.Cave;
import entities.Ground;
import entities.Player;

public class Scene {
	// private Variablen
    
    private float aspect = 1;
	private float fovy = 90;
	private float zNear = .1f;
	private float zFar = 40;
	private float zLight = 5;
	private float shini = 200;
	private float spotExpo = 67;
	
	private Lightning l1 = new Lightning(GL_LIGHT1);
	
	private Vector3f secondOrthogonalVector = new Vector3f(0, 1, 0);
	private Matrix4f m = new Matrix4f();
	private FloatBuffer fb = BufferUtils.createFloatBuffer(16);

	
	private Player player;
	private Camera camera = new Camera(player);
	private Ground floor = new Ground();
//	private Cave cave = new Cave(0, 0, "heightMap");
	
    /*
     * sets up our scene
     */
    public void initGLState() {
    	

    	m.setPerspective(fovy, aspect, zNear, zFar);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        
        m.get(fb);
        
        glLoadMatrixf(fb);
//        glFrustum(-2.0, 2.0, -2.0, 2.0, 0, 2.0);
        
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        
        /* Smooth-Shading soll benutzt werden */
	    glShadeModel( GL_FLAT );
	    
//	    initLighting();
    }
    
    /*
     * renders our models and defines the cameraposition
     */
    public void renderLoop()
    {    
    	player.move();
    	camera.move();    
    	
    	m.setLookAt(camera.getPosition(), 
    				player.getPosition(),
    				calculateUpVectorOfCameraPosition(secondOrthogonalVector));
    	m.get(fb);
    	
    	glLoadMatrixf(fb);
    	
    	glPushMatrix();
//    		cave.generateTerrain();
    	glPopMatrix();
    	
    	glPushMatrix();
			GL11.glTranslatef(player.getPosition().x, player.getPosition().y, player.getPosition().z);
			GL11.glRotatef(player.getRotY(), 0, 1, 0);
    		player.getModel().draw();
    	glPopMatrix();
    	glPushMatrix();
            floor.draw();
        glPopMatrix();
        
    }
    
    private void initLighting()
	{
	    float light1_position [] = { 0.0f, 0.0f, zLight, 1.0f };
	    float light1_diff_spec [] = { 0.714f, 0.714f, 0.714f, 1.0f };
	    float light1_direction [] = { 0, 0, -1, 1};
	    float light1_ambient [] = {0, 0, 0, 1};

	    float lmodel_ambient [] = { 0.6f, 0.6f, 0.6f, 1.0f };

	    // Hintergrundbeleuchtung definieren
	    glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
	    
	    // Eine weitere Lichtquellen definieren
	    l1.setPosition(light1_position);
	    l1.setDirection(light1_direction);
	    l1.setDiffAndSpek(light1_diff_spec);
	    l1.setAmbient(light1_ambient);
	    l1.setCutoff(14f);
	    l1.setExponent(spotExpo);
	    
	    glEnable(GL_LIGHTING);
	    
	    l1.turnLightOn();
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
	 * calculates the vectorproduct which delivers the up vector for our camera (always in y-direction
	 */
	private Vector3f calculateUpVectorOfCameraPosition(Vector3f orthVector) {
		
		Vector3f interimResult = new Vector3f();
		interimResult.x = calculateVectorDirectionBetweenEyeAndCenter().y * orthVector.z - calculateVectorDirectionBetweenEyeAndCenter().z * orthVector.y;
		interimResult.y = calculateVectorDirectionBetweenEyeAndCenter().z * orthVector.x - calculateVectorDirectionBetweenEyeAndCenter().x * orthVector.z;
		interimResult.z = calculateVectorDirectionBetweenEyeAndCenter().x * orthVector.y - calculateVectorDirectionBetweenEyeAndCenter().y * orthVector.x;
		
		Vector3f normalVector = new Vector3f();
		normalVector.x = calculateVectorDirectionBetweenEyeAndCenter().z * interimResult.y - calculateVectorDirectionBetweenEyeAndCenter().y * interimResult.z;
		normalVector.y = calculateVectorDirectionBetweenEyeAndCenter().x * interimResult.z - calculateVectorDirectionBetweenEyeAndCenter().z * interimResult.x;
		normalVector.z = calculateVectorDirectionBetweenEyeAndCenter().y * interimResult.x - calculateVectorDirectionBetweenEyeAndCenter().x * interimResult.y;
		
		normalVector = normalVector.normalize();
		
		return normalVector;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
}

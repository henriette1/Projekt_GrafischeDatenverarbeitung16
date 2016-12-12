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
	private float zFar = 150;

	private Lightning pointLght = new Lightning(GL_LIGHT1);
	
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
	    glShadeModel( GL_FLAT );
	    glEnable(GL_NORMALIZE);
	    initLighting();
	    

    }
    
    /*
     * renders our models and defines the cameraposition
     */
    public void renderLoop()
    {   
    	player.move(calculateVectorDirectionBetweenEyeAndCenter());
    	camera.move();  

    	m.setLookAt(camera.getPosition(), 
    				player.getPosition(),
    				calculateUpVectorOfCameraPosition(secondOrthogonalVector));
    	m.get(fb);
    	
    	glLoadMatrixf(fb);
    	
    	updatePointLight();
		
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
    
    private void initLighting()
	{
    	float light1_position [] = { player.getPosition().x, 1.0f, player.getPosition().z, 1.0f }; //x,y,z,1
	    float light1_diff_spec [] = { 0.714f, 0.714f, 0.714f, 1.0f };
	    float light1_direction [] = { 0, 0.0f, 0, 1};
	    float light1_ambient [] = {0, 0, 0, 1};

	    float lmodel_ambient [] = { .5f, .5f, .5f, 1.0f };

	    // Hintergrundbeleuchtung definieren
	    glLightModelfv(GL_LIGHT_MODEL_AMBIENT, lmodel_ambient);
	    
	    // Eine weitere Lichtquellen definieren
	    pointLght.setPosition(light1_position);
	    pointLght.setDirection(light1_direction);
	    pointLght.setDiffAndSpek(light1_diff_spec);
	    pointLght.setAmbient(light1_ambient);
	    
	    glEnable(GL_LIGHTING);
	    
	    pointLght.turnLightOn();
	}
    
    private void updatePointLight() {
    	float light1_position [] = { player.getPosition().x, 1.0f, player.getPosition().z, 1.0f }; //x,y,z,1
	    
    	
	    // Eine weitere Lichtquellen definieren
	    pointLght.setPosition(light1_position);
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

	public void setTerrain(Terrain cave) {
		this.terrain = cave;	
	}

	public Terrain getTerrain() {
		return this.terrain;
	}
}

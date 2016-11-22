package entities;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import org.joml.Vector3f;
import engine.Utils;
import static org.lwjgl.opengl.GL11.*;

public class Cave {
	
	private static final float MAX_HEIGHT = 40;
	private static final float MAX_PIXEL_COLOR = 256 * 256 * 256;
	private static final float SIZE = 100;
	
	BufferedImage HeightMapMesh;
	
	public Cave(String imageName) {
		try {
			HeightMapMesh = ImageIO.read(new File(imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(int vertexCount) {
		
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		float mat_ambient[] = {0.224f, 0.090f, 0.031f, 1.0f};		      
		float mat_diffuse[] = {0.549f, 0.212f, 0.071f, 1.0f};		      
		float mat_specular[] = {0.580f, 0.220f, 0.071f, 1.0f};		      
		

	     /* Materialeigenschaften festlegen */
	     glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient);
	     glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse);
	     glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular);
	     
	     float shini = 200;
		glMaterialf(GL_FRONT, GL_SHININESS, shini );
		
		// TODO: Normale nur für 1 und 4 berechnen
		
		for(int i = 0; i < vertexCount; i++) {
			for(int j = 0; j < vertexCount; j++) {
				
				glBegin(GL_TRIANGLE_STRIP);
					Vector3f vec1 = new Vector3f((SIZE * (float) (i) / vertexCount) - SIZE / 2, getHeight(i, j, HeightMapMesh) + MAX_HEIGHT / 2, (SIZE * (float) (j) / vertexCount) - SIZE / 2);
					Vector3f vec2 = new Vector3f((SIZE * (float) (i) / vertexCount) - SIZE / 2, 		getHeight(i, j + 1, HeightMapMesh) + MAX_HEIGHT / 2,		(SIZE * (float) (j + 1) / vertexCount) - SIZE / 2);
					Vector3f vec3 = new Vector3f((SIZE * (float) (i + 1) / vertexCount) - SIZE / 2, 	getHeight(i + 1, j, HeightMapMesh) + MAX_HEIGHT / 2,		(SIZE * (float) (j) / vertexCount) - SIZE / 2);
					Vector3f vec4 = new Vector3f((SIZE * (float) (i + 1) / vertexCount) - SIZE / 2, 	getHeight(i + 1, j + 1, HeightMapMesh) + MAX_HEIGHT / 2,		(SIZE * (float) (j + 1) / vertexCount) - SIZE / 2);

					Vector3f normal1 = Utils.normalizeVector(vec1, vec3, vec2);
					Vector3f normal4 = Utils.normalizeVector(vec4, vec2, vec3);
					
					glNormal3f(normal1.x, normal1.y, normal1.z);
					glVertex3f(vec1.x,		vec1.y, 	vec1.z);
					
					
					glVertex3f(vec2.x,		vec2.y, 	vec2.z);
					
					
					glVertex3f(vec3.x,		vec3.y, 	vec3.z);
					
					glNormal3f(normal4.x, normal4.y, normal4.z);
					glVertex3f(vec4.x,		vec4.y, 	vec4.z);
				glEnd();
				
//				glBegin(GL_LINES);
//					glColor3f(0.5f, 0f, 0.5f);
//					glVertex3f(vec1.x,		vec1.y, 	vec1.z);
//					glVertex3f(vec1.x + normal1.x, vec1.y+ normal1.y, vec1.z+normal1.z);
//				glEnd();

				for(int z = 0; z < HeightMapMesh.getHeight(); z++) {
					glBegin(GL_TRIANGLE_STRIP);
					for(int x = 0; x < HeightMapMesh.getWidth(); x++) {
						
						glVertex3f(x, getHeight(x, z, HeightMapMesh), z);
						
						glVertex3f(x, getHeight(x, z, HeightMapMesh), z + 1);
					}
					glEnd();
				}
			}
		}
		
	}
	
	public void generateTerrain() {
		
		int VERTEX_COUNT = HeightMapMesh.getHeight();

		draw(VERTEX_COUNT);
	}
	
	private float getHeight(int x, int z, BufferedImage image) {
		if(x < 0 || x >= image.getHeight() || z < 0 || z >= image.getHeight()) {
			return 0;
		}
		float height = image.getRGB(x, z);
		height += MAX_PIXEL_COLOR / 2f;
		height /= MAX_PIXEL_COLOR / 2f;
		height *= MAX_HEIGHT/2f;
		return height;
	}
}

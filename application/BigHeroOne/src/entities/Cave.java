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
	
	private BufferedImage HeightMapMesh;
	
	private int index;
	
	public Cave(int index) {
		loadHeightMap();
		this.index = index;
		compileList();
	}
	
	private void loadHeightMap() {
		try {
			HeightMapMesh = ImageIO.read(getClass().getResource("/images/heightMap.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void compileList() {
		
		int WIDTH = HeightMapMesh.getWidth();
		int HEIGHT = HeightMapMesh.getHeight();
		
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glNewList(this.index, GL_COMPILE);
			for(int z = 0; z < HEIGHT; z++) {
				glBegin(GL_TRIANGLE_STRIP);
				for(int x = 0; x < WIDTH; x++) {
					glColor3f(1.f, 1.f, 1.f);
					Vector3f drawnVecOne = new Vector3f();
					drawnVecOne.x = (SIZE * (float) (x) / HeightMapMesh.getWidth()) - SIZE / 2;
					drawnVecOne.y = getHeight(x, z, HeightMapMesh) + MAX_HEIGHT / 2;
					drawnVecOne.z = (SIZE + (float)(z) / HeightMapMesh.getHeight()) - SIZE / 2;
					
					Vector3f drawnVecTwo = new Vector3f();
					drawnVecTwo.x = (SIZE * (float) (x) / HeightMapMesh.getWidth()) - SIZE / 2;
					drawnVecTwo.y = getHeight(x, z + 1, HeightMapMesh) + MAX_HEIGHT / 2;
					drawnVecTwo.z = (SIZE + (float)(z + 1) / HeightMapMesh.getHeight()) - SIZE / 2;
					
					Vector3f normalOne = Utils.calculateNormalVector(drawnVecOne, z, x, getHeight(x+1, z, HeightMapMesh), getHeight(x, z+1, HeightMapMesh));
//					Vector3f normalTwo = Utils.calculateNormalVector(drawnVecTwo, z, x, getHeight(x+1, z, HeightMapMesh), getHeight(x, z+1, HeightMapMesh));
					
					glNormal3f(normalOne.x, normalOne.y, normalOne.z);
					glVertex3f(x, 
							getHeight(x, z, HeightMapMesh) + MAX_HEIGHT / 2, 
							z);
	//				glNormal3f(normalTwo.x, normalTwo.y, normalTwo.z);
					glVertex3f(x, 
							getHeight(x, z + 1, HeightMapMesh) + MAX_HEIGHT / 2, 
							z+1);
				}
				glEnd();
			}
		glEndList();
	}
	
	public void generateTerrain() {
		glCallList(this.index);
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

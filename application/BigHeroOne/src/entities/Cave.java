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
			HeightMapMesh = ImageIO.read(new File ("heightMap.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void compileList() {
//		int WIDTH = HeightMapMesh.getWidth();
		int HEIGHT = 1;
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glNewList(index, GL_COMPILE);
			glColor3f(0.f, 1.f, 1.f);
			for(int z = 0; z < SIZE; z++) {
				glBegin(GL_TRIANGLE_STRIP);
				for(int x = 0; x < SIZE; x++) {
//					Vector3f drawnVecOne = new Vector3f();
//					drawnVecOne.x = (SIZE * (float) (x) / HeightMapMesh.getWidth()) - SIZE / 2;
//					drawnVecOne.y = getHeight(x, z, HeightMapMesh) + MAX_HEIGHT / 2;
//					drawnVecOne.z = (SIZE + (float)(z) / HeightMapMesh.getHeight()) - SIZE / 2;
					
//					Vector3f drawnVecTwo = new Vector3f();
//					drawnVecTwo.x = (SIZE * (float) (x) / HeightMapMesh.getWidth()) - SIZE / 2;
//					drawnVecTwo.y = getHeight(x, z + 1, HeightMapMesh) + MAX_HEIGHT / 2;
//					drawnVecTwo.z = (SIZE + (float)(z + 1) / HeightMapMesh.getHeight()) - SIZE / 2;
					
	//				Vector3f normalOne = Utils.calculateNormalVector(drawnVecOne, x, z, getHeight(x+1, z, HeightMapMesh), getHeight(x, z+1, HeightMapMesh));
	//				Vector3f normalTwo = Utils.calculateNormalVector(drawnVecTwo, x, z, getHeight(x+1, z, HeightMapMesh), getHeight(x, z+1, HeightMapMesh));
					
					float heightMapHeightVertex1 = 1;
					float heightMapHeightVertex2 = 1;
//					float heightMapHeightVertex1 = getHeight(x, z, HeightMapMesh);
//					float heightMapHeightVertex2 = getHeight(x, z + 1, HeightMapMesh);
					
	//				glNormal3f(normalOne.x, normalOne.y, normalOne.z);
					glVertex3f((SIZE * (float) (x) / HEIGHT) - SIZE / 2, 
							heightMapHeightVertex1 + MAX_HEIGHT / 2, 
							(SIZE + (float)(z) / HEIGHT) - SIZE / 2);
	//				glNormal3f(normalTwo.x, normalTwo.y, normalTwo.z);
					glVertex3f((SIZE * (float) (x) / HEIGHT) - SIZE / 2, 
							heightMapHeightVertex2 + MAX_HEIGHT / 2, 
							(SIZE + (float)(z + 1) / HEIGHT) - SIZE / 2);
				}
				glEnd();
			}
		glEndList();
	}
	
	public void generateTerrain() {
		glCallList(index);
	}
	
	private float getHeight(int x, int z, BufferedImage image) {
		if(x < 0 || x >= image.getHeight() || z < 0 || z >= image.getHeight()) {
			return 0;
		}
		float height = image.getRGB(x, z);
		height += MAX_PIXEL_COLOR / 2f;
		height /= MAX_PIXEL_COLOR / 2f;
		height *= MAX_HEIGHT/2f;
		return 1;
	}
}

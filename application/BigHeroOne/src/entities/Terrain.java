package entities;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

import org.joml.Vector3f;

import engine.Materials;
import engine.Utils;
import static org.lwjgl.opengl.GL11.*;

public class Terrain {
	
	private static final float MAX_HEIGHT = 40;
	private static final float MAX_PIXEL_COLOR = 256 * 256 * 256;
	
	public static float [][] heightMapCoords; 
	
	private BufferedImage HeightMapMesh;
	
	private int index;
	private int WIDTH;
	private int HEIGHT;
	
	public Terrain(int index) {
		loadLevelOne();
		this.index = index;
		compileCaveList();
		compileGroundList();
		compileNormalsCaveList();
		compileNormalsGroundList();
	}
	
	public void generateCave() {
		glPushMatrix();
			Materials.materialCave();
			// call list containing the cave coordinates
			glCallList(this.index);
		glPopMatrix();
	}
	
	public void generateGround() {
		glPushMatrix();
			Materials.materialGround();
			//call list containing the ground
			glCallList(this.index + 1);
		glPopMatrix();
	}
	
	private void loadLevelOne() {
		try {
			HeightMapMesh = ImageIO.read(getClass().getResource("/images/heightMap3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void loadLevelTwo() {
		glDeleteLists(this.index, 1);
		
		try {
			HeightMapMesh = ImageIO.read(getClass().getResource("/images/heightMapLvl2_2.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		compileCaveList();
	}
	
	private void compileCaveList() {
		
		WIDTH = HeightMapMesh.getWidth();
		HEIGHT = HeightMapMesh.getHeight();
		heightMapCoords = new float[WIDTH][HEIGHT];
		int m = 1;
		glNewList(this.index, GL_COMPILE);
			for(int z = 0; z < m*(HEIGHT-1); z++) {
				glBegin(GL_TRIANGLE_STRIP);
				for(int x = 0; x < m*WIDTH; x++) {
					heightMapCoords[x][z] = getHeight(x, z, HeightMapMesh);
					
					Vector3f drawnVecOne = new Vector3f(x/m, 
							getHeight(x/m, z/m, HeightMapMesh) + MAX_HEIGHT / 2, 
							z/m);

					Vector3f normalOne = Utils.calculateNormalVector(
							drawnVecOne, 
							getHeight((x+1)/m, z/m, HeightMapMesh) + MAX_HEIGHT / 2, 
							getHeight(x/m, (z-1)/m, HeightMapMesh) + MAX_HEIGHT / 2);
					
					glNormal3f(-normalOne.x, -normalOne.y, -normalOne.z);
					glVertex3f(x/m, 
							getHeight(x/m, z/m, HeightMapMesh) + MAX_HEIGHT / 2, 
							z/m);
					glVertex3f(x/m, 
							getHeight(x/m, (z + 1)/m, HeightMapMesh) + MAX_HEIGHT / 2, 
							(z+1)/m);
				}
				glEnd();
			}
		glEndList();
	}
	
	private void compileGroundList() {
		glNewList(this.index + 1, GL_COMPILE);
			float m = 4.f;
			for(int z = 0; z < m *HEIGHT; z++) {
				glBegin(GL_TRIANGLE_STRIP);
				for(int x = 0; x < m*WIDTH; x++) {
					Vector3f firstPoint= new Vector3f(x, 0.1f, z);
					
					Vector3f normalGround = Utils.calculateNormalVector(firstPoint, 0, 0);
					
					glNormal3f(normalGround.x, normalGround.y, normalGround.z);
					glVertex3f(x/m, 0.1f, z/m);
					glVertex3f(x/m, 0.1f, (z + 1)/m);
					
					
				}
				glEnd();
			}
		glEndList();
	}
	
	private void compileNormalsCaveList() {
		WIDTH = HeightMapMesh.getWidth();
		HEIGHT = HeightMapMesh.getHeight();
		
		glNewList(this.index+2, GL_COMPILE);
			for(int z = 0; z < HEIGHT-1; z++) {
				for(int x = 0; x < WIDTH; x++) {
					
					Vector3f drawnVecOne = new Vector3f(x, 
							getHeight(x, z, HeightMapMesh) + MAX_HEIGHT / 2, 
							z);

					Vector3f normalOne = Utils.calculateNormalVector(
							drawnVecOne, 
							getHeight(x+1, z, HeightMapMesh) + MAX_HEIGHT / 2, 
							getHeight(x, z+1, HeightMapMesh) + MAX_HEIGHT / 2);
					
					glBegin(GL_LINES);
						glVertex3f(drawnVecOne.x, drawnVecOne.y, drawnVecOne.z);
						glVertex3f(drawnVecOne.x - normalOne.x, 
								drawnVecOne.y - normalOne.y, 
								drawnVecOne.z - normalOne.z);
					glEnd();
				}
			}
		glEndList();
	}
	
	private void compileNormalsGroundList() {
		glNewList(this.index + 3, GL_COMPILE);
		for(int z = 0; z < HEIGHT-1; z++) {
			for(int x = 0; x < WIDTH; x++) {
				Vector3f firstPoint= new Vector3f(x, 0.1f, z);
				
				Vector3f normalGround = Utils.calculateNormalVector(firstPoint, .1f, .1f);
				
				glBegin(GL_LINES);
					glVertex3f(firstPoint.x, firstPoint.y, firstPoint.z);
					glVertex3f(firstPoint.x + normalGround.x, 
							firstPoint.y + normalGround.y, 
							firstPoint.z + normalGround.z);
				glEnd();
				
				
			}
		}
	glEndList();
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
	
	public void deleteLists() {
		glDeleteLists(this.index, 2);
	}
	
	public float[][] getHeightMapCoords(){
		return heightMapCoords;
	}
	
	public float getMaxHeight(){
		return MAX_HEIGHT;
	}
	
	public void drawEnd() {
		int width_begin = 393;
		int width_end = 513;
		int height = 60;
		int xCoord = 22;
		float mat_ambient[] = {1f, 1f, 1f, 1.f};
		float shininess = 100f;
		
		for(int i = 0; i < height; i++) {
		glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, mat_ambient);
		glMaterialf(GL_FRONT_FACE, GL_SHININESS, shininess);
		
		glBegin(GL_TRIANGLE_STRIP);
			for(int j = width_begin; j < width_end; j++) {
				
				glNormal3f(1.f, 0, 0);
				glVertex3f(xCoord, i, j);
				glVertex3f(xCoord, i+1, j);
			}
			glEnd();
		}
		
	}
}

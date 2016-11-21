package engine;

import static org.lwjgl.opengl.GL11.GL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.GL_SPECULAR;
import static org.lwjgl.opengl.GL11.GL_SPOT_CUTOFF;
import static org.lwjgl.opengl.GL11.GL_SPOT_DIRECTION;
import static org.lwjgl.opengl.GL11.GL_SPOT_EXPONENT;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLightf;
import static org.lwjgl.opengl.GL11.glLightfv;

public class Lightning {
		// private Variablen
		int lightNumber;
		
		
		public Lightning(int number){	
			lightNumber = number;
		}
		
		public void setPosition(float[] light_position){
			glLightfv(lightNumber, GL_POSITION, light_position);
		}
		
		public void setPosition(float x, float y, float z){
			float [] pos = {x, y, z, 1};
			glLightfv(lightNumber, GL_POSITION, pos);
		}
		
		public void setDiffAndSpek(float[] light_diff_spec){
			glLightfv(lightNumber, GL_DIFFUSE, light_diff_spec);
		    glLightfv(lightNumber, GL_SPECULAR, light_diff_spec);
		}
		
		public void setAmbient(float[] light_amb){
			glLightfv(lightNumber, GL_AMBIENT, light_amb);
		}
		
		public void setDirection(float[] light_direction){
			glLightfv(lightNumber, GL_SPOT_DIRECTION, light_direction);
		}
		
		public void setCutoff(float cutoff){
			glLightf(lightNumber, GL_SPOT_CUTOFF, cutoff);
		}
		
		public void setExponent(float exponent){
			glLightf(lightNumber, GL_SPOT_EXPONENT, exponent);
		}
		
		public void turnLightOn()
		{
			glEnable(lightNumber);
		}
		
		public void turnLightOff()
		{
			glDisable(lightNumber);
		}
}

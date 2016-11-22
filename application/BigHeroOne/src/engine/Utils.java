package engine;

import org.joml.Vector3f;

public class Utils {

	/*
	 * calculate difference vector for two given vetrtices
	 */
	public static Vector3f calculateDifferenceVector(Vector3f a, Vector3f b) {
		return new Vector3f(b.x - a.x, b.y - a.y, b.z - a.z);
	}
	
	/*
	 * calculates the vector product which delivers the up vector for our camera (always in y-direction
	 */
	public static Vector3f normalizeVector(Vector3f main, Vector3f vec1, Vector3f vec2) {
		
		Vector3f vecX = new Vector3f(vec1.x - main.x, vec1.y - main.y, vec1.z - main.z);
		Vector3f vecZ = new Vector3f(vec2.x - main.x, vec2.y - main.y, vec2.z - main.z);
		
		
		Vector3f normal = new Vector3f();
		normal.x = vecZ.z * vecX.y - vecZ.y * vecX.z;
		normal.y = vecZ.x * vecX.z - vecZ.z * vecX.x;
		normal.z = vecZ.y * vecX.x - vecZ.x * vecX.y;
		
		normal = normal.normalize();
		
		return normal;
	}
}

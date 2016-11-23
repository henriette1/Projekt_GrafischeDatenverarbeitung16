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
	public static Vector3f calculateNormalVector(Vector3f a, int x, int z, float heightXplusOne, float heightZplusOne) {
		
		Vector3f vecX = new Vector3f(a.x + 1f, heightXplusOne, a.z);
		Vector3f vecZ = new Vector3f(a.x, heightZplusOne, a.z  + 1f);
		
		Vector3f diffVecX = Utils.calculateDifferenceVector(a, vecX);
		Vector3f diffVecZ = Utils.calculateDifferenceVector(a, vecZ);
		
		Vector3f normal = new Vector3f();
		normal.x = diffVecX.y * diffVecZ.z - diffVecX.z * diffVecZ.y;
		normal.y = diffVecX.z * diffVecZ.x - diffVecX.x * diffVecZ.z;
		normal.z = diffVecX.x * diffVecZ.y - diffVecX.y * diffVecZ.x;
		
		normal.normalize(normal);
		
		return normal;
	}
}

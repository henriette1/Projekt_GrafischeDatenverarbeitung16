package engine;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwSetCursorEnterCallback;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_2;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import entities.Camera;
import entities.Model;
import entities.Player;

public class DisplayManager {
	  // The window handle
    private long window;

    private MyScene scene = new MyScene();
    private int WIDTH = 600, HEIGHT = 600;
    
    private static final int FPS_CAP = 120;
    private long variableYieldTime, lastTime;
    
    private static long lastFrameTime;
    private static float delta;

	private GLFWScrollCallback scrollCallback;

	private static boolean mouseLeft = false;
	private static boolean mouseRight = false;
	private Model model = new Model();
	private Player player = new Player(model, new Vector3f(0,0,0), 0, 0, 0, 1);
	private Camera camera = new Camera(player);

	private GLFWCursorEnterCallback cursorEnterCallback;

	protected boolean inWindow = false;

    public void run()
    {
        try {
            init();
            loop();

            // Reset all callbacks for the specified GLFW window to NULL and free all previously set callbacks.
            glfwFreeCallbacks(window); 

            // Destroy the specified window and its context.
            glfwDestroyWindow(window); 
        } finally {

            // Destroy all remaining windows, free any allocated resources and set the library to an uninitialized state.
            // Once this is called, you must again call glfwInit successfully before you will be able to use 
            // most GLFW functions.
            glfwTerminate();

            // Free the error callback
            glfwSetErrorCallback(null).free();
        }
    }

    private void init()
    {

        // Setup an error callback. The default implementation will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable


        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Beispiel 2", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
            
            player.checkInputs(key, action);
        });
        // Setup a scroll callback
        glfwSetScrollCallback(window, scrollCallback = GLFWScrollCallback.create((window, xoffset, yoffset) -> {
            int temp = (int) yoffset;
            System.out.println(temp);
        	Camera.dyZoom = (float) temp;
        }));
        
        
        // Setup a mouse callback 
        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
        	if (button== GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS) {
        		setMouseLeft(true);
        	}
        	if (button== GLFW_MOUSE_BUTTON_1 && action == GLFW_RELEASE) {
        		setMouseLeft(false);
        	}
        	if (button== GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS) {
        		setMouseRight(true);
        	}
        	if (button== GLFW_MOUSE_BUTTON_2 && action == GLFW_RELEASE) {
        		setMouseRight(false);
        	}
        });
        
        glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
        	
        		Camera.dxRotate = (float) xpos;
        	
        		Camera.dyPitch = (float) ypos;
    	});
        
        //create Callback to decide if cursor is in or outside the window
		glfwSetCursorEnterCallback(window, cursorEnterCallback = new GLFWCursorEnterCallback() {
			
			@Override
			public void invoke(long window, boolean entered) {
				inWindow  = entered;
			}
		});
		
		
        scene.setPlayer(player);  
        camera.setPlayer(player);
        scene.setCamera(camera);

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Center our window
        glfwSetWindowPos(window, (vidmode.width() - WIDTH) / 2, (vidmode.height() - HEIGHT) / 2);

        // Make the OpenGL context of the specified window current on the calling thread.
        glfwMakeContextCurrent(window);

        // Set the swap interval for the current OpenGL, i.e. the number of screen updates
        // to wait from the time glfwSwapBuffers was called before swapping the buffers and returning
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // Create a new GLCapabilities instance for the OpenGL context that is current in the current thread
        GL.createCapabilities();
    }

    private void loop()
    {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.

        scene.initGLState();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {

            // Clear the color buffers and depth buffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            glEnable(GL_DEPTH_TEST);
            
            scene.renderLoop();

            // Swap the color buffers
            glfwSwapBuffers(window);

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
            IntBuffer IntWindowWidth = BufferUtils.createIntBuffer(1);
            IntBuffer IntWindowHeight = BufferUtils.createIntBuffer(1);

            glfwGetWindowSize(window, IntWindowWidth, IntWindowHeight);
            if (windowSizeChanged(IntWindowWidth, IntWindowHeight))
            {
                glViewport(0, 0, WIDTH, HEIGHT);
            }

            IntWindowWidth.clear();
            IntWindowHeight.clear();
            
            long currentFrameTime = getCurrentTime();
            delta = (float) (currentFrameTime - lastFrameTime) / 1000;
            lastFrameTime = currentFrameTime;
        }
    }
    
    public void closeDisplay() {
    	glfwDestroyWindow(window);
    }
    
    public void updateDisplay() {
    	sync(FPS_CAP);
    	long currentFrameTime = getCurrentTime();
    	delta = (float)(currentFrameTime - lastFrameTime) / 1000f;
    	lastFrameTime = currentFrameTime;
    }

//     An accurate sync method that adapts automatically
//    to the system it runs on to provide reliable results.

    private void sync(int fps) {
        if (fps <= 0) return;
          
        long sleepTime = 1000000000 / fps; // nanoseconds to sleep this frame
        // yieldTime + remainder micro & nano seconds if smaller than sleepTime
        long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000*1000));
        long overSleep = 0; // time the sync goes over by
          
        try {
            while (true) {
                long t = System.nanoTime() - lastTime;
                  
                if (t < sleepTime - yieldTime) {
                    Thread.sleep(1);
                }else if (t < sleepTime) {
                    // burn the last few CPU cycles to ensure accuracy
                    Thread.yield();
                }else {
                    overSleep = t - sleepTime;
                    break; // exit while loop
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lastTime = System.nanoTime() - Math.min(overSleep, sleepTime);
             
            // auto tune the time sync should yield
            if (overSleep > variableYieldTime) {
                // increase by 200 microseconds (1/5 a ms)
                variableYieldTime = Math.min(variableYieldTime + 200*1000, sleepTime);
            }
            else if (overSleep < variableYieldTime - 200*1000) {
                // decrease by 2 microseconds
                variableYieldTime = Math.max(variableYieldTime - 2*1000, 0);
            }
        }
    }
    
    public static float getFrameTimeSeconds() {
    	return delta;
    }

    private boolean windowSizeChanged(IntBuffer w, IntBuffer h)
    {
        if (WIDTH != w.get(0) || HEIGHT != h.get(0))
        {
            WIDTH = w.get(0);
            HEIGHT = h.get(0);
            return true;
        }

        return false;
    }
    
    private long getCurrentTime() {
    	return (long) (GLFW.glfwGetTime() * 1000);
    }

	public static boolean isMouseLeft() {
		return mouseLeft;
	}

	public void setMouseLeft(boolean mouse) {
		this.mouseLeft = mouse;
	}

	public static boolean isMouseRight() {
		return mouseRight;
	}

	public void setMouseRight(boolean mouseRight) {
		this.mouseRight = mouseRight;
	}

}

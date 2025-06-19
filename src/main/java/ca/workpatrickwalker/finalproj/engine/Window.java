package ca.workpatrickwalker.finalproj.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window 
{
    public static final String DEFAULT_TITLE = "finalproj";
    public static final int HD_HEIGHT = 1080;
    public static final int HD_WIDTH = 1920;
    
    private static final long DEFAULT_MONITOR = 0L;
    private static final long DEFAULT_DISPLAY_SHARING = 0L;
    private static final int VSYNC_ENABLED = 1;
    
    private static Window instance = null;
    
    private int height;
    private String title;
    private int width;
    private long window;
    
    private Window()
    {
    }
    
    public static Window get()
    {
        return instance;
    }
    
    public void init()
    {
        // Send GLFW errors to the console
        GLFWErrorCallback.createPrint(System.err).set();
        
        if (!glfwInit())
        {
            throw new IllegalStateException("Failed to initialize GLFW.");
        }
        
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
        
        window = glfwCreateWindow(this.width, this.height, this.title, DEFAULT_MONITOR, DEFAULT_DISPLAY_SHARING);
        glfwMakeContextCurrent(window);
        
        glfwSwapInterval(VSYNC_ENABLED);
        
        glfwShowWindow(window);

        // Have LWJGL find the current context, create the GLCapabilities instance for it and make its bindings available for use
        GL.createCapabilities();
    }
    
    public void loop()
    {
        while (!glfwWindowShouldClose(window))
        {
            glfwPollEvents();
            
            glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
            glClear(GL_COLOR_BUFFER_BIT);
            
            glfwSwapBuffers(window);
        }
    }
    
    public static void set(int width, int height, String title)
    {
        if (instance == null)
        {
            instance = new Window();
            instance.width = width;
            instance.height = height;
            instance.title = title;
        }
    }
}

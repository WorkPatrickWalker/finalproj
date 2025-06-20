package ca.workpatrickwalker.finalproj.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window 
{
    public static final String DEFAULT_TITLE = "finalproj";
    public static final int HD_HEIGHT = 1080;
    public static final int HD_WIDTH = 1920;
    
    private static final float A = 1.0f;
    private static final long DEFAULT_MONITOR = 0L;
    private static final long DEFAULT_DISPLAY_SHARING = 0L;
    private static final int VSYNC_ENABLED = 1;
    
    private static Window instance = null;
    
    private float b;
    private float g;
    private int height;
    private float r;
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
    
    public static float getB()
    {
        return instance.b;
    }
    
    public static float getG()
    {
        return instance.g;
    }
    
    public static float getR()
    {
        return instance.r;
    }
    
    public static void set(int width, int height, String title)
    {
        if (instance == null)
        {
            instance = new Window();
            instance.width = width;
            instance.height = height;
            instance.title = title;
            instance.r = 1.0f;
            instance.g = 1.0f;
            instance.b = 1.0f;
        }
    }
    
    public static void setBg(float r, float g, float b)
    {
        instance.r = r;
        instance.g = g;
        instance.b = b;
    }

    public void destroy()
    {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();

        // When no error callback is passed to glfwSetErrorCallback(GLFWErrorCallbackI), NULL (0L in the GL library)
        // is returned, which satisfies the requireNonNull function, included since glfwSetErrorCallback is @Nullable
        // while free() is not @Nullable.
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
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
        
        glfwSetCursorPosCallback(window, MouseListener::posCallback);
        glfwSetMouseButtonCallback(window, MouseListener::buttonCallback);
        glfwSetScrollCallback(window, MouseListener::scrollCallback);
        glfwSetKeyCallback(window, KeyListener::keyCallback);
        
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

            glClearColor(r, g, b, A);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(window);
        }
    }
}

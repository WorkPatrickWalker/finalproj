package ca.workpatrickwalker.finalproj.engine;

public class MouseListener 
{
    private static final int CENTER = 2;
    private static final int LEFT = 0;
    private static final int PRESSED = 1;
    private static final int RELEASED = 0;
    private static final int RIGHT = 1;
    private static final int STANDARD_MOUSE_BUTTONS = 3;
    
    private static MouseListener instance = null;
    
    private final boolean[] buttonPressed = new boolean[STANDARD_MOUSE_BUTTONS];
    
    private boolean dragging;
    private double lastX;
    private double lastY;
    private double scrollDX;
    private double scrollDY;
    private double x;
    private double y;
    
    private MouseListener()
    {
        this.lastX = 0.0;
        this.lastY = 0.0;
        this.scrollDX = 0.0;
        this.scrollDY = 0.0;
        this.x = 0.0;
        this.y = 0.0;
    }
    
    public static void buttonCallback(@SuppressWarnings("unused") long window, int button, int action, @SuppressWarnings("unused") int mods)
    {
        if (button < STANDARD_MOUSE_BUTTONS)
        {
            if (action == PRESSED)
            {
                get().buttonPressed[button] = true;
            }
            else if (action == RELEASED)
            {
                get().buttonPressed[button] = false;
                get().dragging = false;
            }
        }
    }

    public static MouseListener get()
    {
        if (instance == null)
        {
            instance = new MouseListener();
        }

        return instance;
    }
    
    public static float getDX()
    {
        return (float) (get().x - get().lastX);
    }
    
    public static float getDY()
    {
        return (float) (get().y - get().lastY);
    }
    
    public static float getScrollDX()
    {
        return (float) get().scrollDX;
    }
    
    public static float getScrollDY()
    {
        return (float) get().scrollDY;
    }

    public static float getX()
    {
        return (float) get().x;
    }

    public static float getY()
    {
        return (float) get().y;
    }

    public static boolean isButtonPressed(int button)
    {
        if (button < STANDARD_MOUSE_BUTTONS)
        {
            return get().buttonPressed[button];
        }
        return false;
    }
    
    public static boolean isDragging()
    {
        return get().dragging;
    }

    public static void posCallback(@SuppressWarnings("unused") long window, double x, double y)
    {
        get().lastX = get().x;
        get().lastY = get().y;
        get().x = x;
        get().y = y;
        get().dragging = get().buttonPressed[LEFT] || get().buttonPressed[CENTER] || get().buttonPressed[RIGHT];
    }

    public static void refresh()
    {
        get().scrollDX = 0.0;
        get().scrollDY = 0.0;
        get().lastX = get().x;
        get().lastY = get().y;
    }
    
    public static void scrollCallback(@SuppressWarnings("unused") long window, double xOffset, double yOffset)
    {
        get().scrollDX = xOffset;
        get().scrollDY = yOffset;
    }
}

package ca.workpatrickwalker.finalproj.engine;

public class KeyListener 
{
    private static final int PRESSED = 1;
    private static final int RELEASED = 0;
    private static final int STANDARD_KEYS = 350;
    
    private static KeyListener instance = null;
    
    private final boolean[] keyPressed = new boolean[STANDARD_KEYS];
    
    private KeyListener()
    {
        
    }
    
    public static KeyListener get()
    {
        if (instance == null)
        {
            instance = new KeyListener();
        }
        
        return instance;
    }
    
    public static boolean isPressed(int key)
    {
        if (key < STANDARD_KEYS)
        {
            return get().keyPressed[key];
        }
        return false;
    }
    
    public static void keyCallback(@SuppressWarnings("unused") long window, int key, @SuppressWarnings("unused") int scancode, int action, @SuppressWarnings("unused") int mods)
    {
        if (key < STANDARD_KEYS)
        {
            if (action == PRESSED)
            {
                get().keyPressed[key] = true;
            }
            else if (action == RELEASED)
            {
                get().keyPressed[key] = false;
            }
        }
    }
}

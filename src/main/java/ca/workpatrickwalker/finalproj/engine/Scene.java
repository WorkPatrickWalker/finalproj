package ca.workpatrickwalker.finalproj.engine;

public abstract class Scene 
{
    public static final int LEVEL = 1;
    public static final int LEVEL_EDITOR = 0;
    
    public Scene()
    {
        
    }
    
    public void init()
    {
        
    }
    
    public abstract void update(float frameElapsed);
}

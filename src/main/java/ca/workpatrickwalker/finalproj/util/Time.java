package ca.workpatrickwalker.finalproj.util;

public class Time 
{
    public static float timeAtRun = System.nanoTime();
    
    public static float getElapsed()
    {
        return (float) ((System.nanoTime() - timeAtRun) * 1E-9);
    }
}

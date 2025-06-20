package ca.workpatrickwalker.finalproj;

import ca.workpatrickwalker.finalproj.engine.Scene;
import ca.workpatrickwalker.finalproj.engine.Window;

public class Main 
{
    public static void main(String[] args)
    {
        Window.set(Window.HD_WIDTH, Window.HD_HEIGHT, Window.DEFAULT_TITLE);
        Window.get().init();
        Window.setScene(Scene.LEVEL_EDITOR);
        Window.get().loop();
        Window.get().destroy();
    }
}

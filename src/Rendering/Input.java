package Rendering;

import Rendering.Math.Mathf;
import Rendering.Math.Vec2;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

public class Input {
    private static long Window;

    public static void init(long window) {
        Window = window;
    }

    public static Vec2 getMousePosition() {
        double[] xFetch = new double[1];
        double[] yFetch = new double[1];
        glfwGetCursorPos(Window,xFetch,yFetch);

        float x = (float) xFetch[0];
        float y = (float) yFetch[0];

        return new Vec2(x,y);

        //return new Vec2(
          //      Mathf.remap(0,Config.windowX,0,Config.referenceX,x),
            //    Mathf.remap(0,Config.windowY,0,Config.referenceY,y));
    }
}

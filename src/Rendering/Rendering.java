package Rendering;

import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Rendering {
    private long window;

    public void initGLFW(){
        System.out.println("Initializing GLFW");

        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        window = glfwCreateWindow(896,896, "Tic Tac Toe", NULL, NULL);

        if(window == NULL){
            glfwTerminate();
            throw new RuntimeException("Failed to create window");
        }
    }

    public void mainLoop(){
        while (!glfwWindowShouldClose(window)){
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }
}

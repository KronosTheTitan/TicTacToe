package Rendering;

import org.lwjgl.opengl.GL;

import java.util.Arrays;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Renderer {
    RenderLayer[] renderLayers;
    private long window;

    public void Setup(){
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        window = glfwCreateWindow(Config.windowX,Config.windowY, "Tic Tac Toe (space to restart)", NULL, NULL);

        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create window");
        }

        Input.init(window);

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        renderLayers = new RenderLayer[1];
        renderLayers[0] = new RenderLayer();
        renderLayers[0].allowTransparency = true;

        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

    }

    public void Run(){

        while(!glfwWindowShouldClose(window)){
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            Render();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public void AddGameObject(GameObject gameObject, int layer){
        renderLayers[layer].addGameObject(gameObject);
    }

    public void RemoveGameObject(GameObject gameObject, int layer){
        renderLayers[layer].removeGameObject(gameObject);
    }

    public void Stop(){
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    void Render() {
        for (int i = 0; i < renderLayers.length; i++) {
            renderLayers[i].Render();
        }
    }
}

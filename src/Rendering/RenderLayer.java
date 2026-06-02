package Rendering;

import org.lwjgl.opengl.GL40;

import java.util.ArrayList;
import java.util.List;

public class RenderLayer {
    ArrayList<GameObject> gameObjects;

    public boolean enabled = true;
    public boolean allowTransparency;

    public RenderLayer() {
        gameObjects = new ArrayList<GameObject>();
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public void Render(){
        if(!enabled)
            return;

        if(gameObjects.isEmpty())
            return;

        if(allowTransparency){
            GL40.glEnable(GL40.GL_BLEND);
            GL40.glBlendFunc(GL40.GL_SRC_ALPHA, GL40.GL_ONE_MINUS_SRC_ALPHA);
        }

        for(int i=0; i<gameObjects.size(); i++){
            gameObjects.get(i).Render();
        }

        if(allowTransparency)
            GL40.glDisable(GL40.GL_BLEND);
    }
}

package Rendering.UserInterface;

import Rendering.*;
import Rendering.Math.Mathf;
import Rendering.Math.Vec2;

public class UI_Element extends GameObject {
    public UI_Shader shader;
    Texture texture;

    Mesh mesh;

    public UI_Element(String name, Vec2 position, Vec2 extents, Texture texture) {
        super(name);

        float xMin = Mathf.remap(0,Config.windowX,-1,1,position.x);
        float xMax = Mathf.remap(0,Config.windowX,-1,1,position.x + extents.x);
        float yMin = Mathf.remap(0,Config.windowY,1,-1,position.y);
        float yMax = Mathf.remap(0,Config.windowY,1,-1,position.y + extents.y);

        float[] data = {
                xMin,yMin,0,1,
                xMax,yMin,1,1,
                xMax,yMax,1,0,
                xMin, yMax,0,0,
        };

        int[] indices = {
                0, 1, 2,
                2, 3, 0
        };

       mesh = new Mesh(data,indices);
       shader = new UI_Shader();
       this.texture = texture;
    }

    public void SetTexture(Texture texture) {
        this.texture = texture;
    }

    public void SetShader(UI_Shader shader) {
        this.shader = shader;
    }

    @Override
    public void Render(){
        if(!enabled)
            return;

        shader.SetTexture(texture);
        shader.Use();

        mesh.Render();

        super.Render();
    }
}

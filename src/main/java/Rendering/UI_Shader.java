package Rendering;

import org.lwjgl.opengl.GL40;

public class UI_Shader extends Shader {
    public UI_Shader() {
        super("./src/main/resources/shaders/shader.vsh", "./resources/shaders/shader.fsh");
    }

    Texture texture;

    public void SetTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void Use() {
        GL40.glBindTexture(GL40.GL_TEXTURE_2D, texture.id);

        super.Use();
    }
}

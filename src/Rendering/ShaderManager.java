package Rendering;

public class ShaderManager {
    public static ShaderManager instance = new ShaderManager();

    public ShaderManager() {
        uiShader = new UI_Shader();
    }

    public UI_Shader getUIShader() {
        return uiShader;
    }

    UI_Shader uiShader;
    Shader errorShader;
}

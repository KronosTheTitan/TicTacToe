package Rendering;

import org.lwjgl.opengl.GL40;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Shader {
    public Shader(String pVertexShader, String pFragmentShader) {
        try {
            vertexShader = Files.readString(Path.of(pVertexShader) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fragmentShader = Files.readString(Path.of(pFragmentShader) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CreateShader();
    }

    int program;
    String vertexShader;
    String fragmentShader;

    void CreateShader() {
        program = GL40.glCreateProgram();
        int vs = CompileShader(GL40.GL_VERTEX_SHADER, vertexShader);
        int fs = CompileShader(GL40.GL_FRAGMENT_SHADER, fragmentShader);

        GL40.glAttachShader(program,vs);
        GL40.glAttachShader(program,fs);
        GL40.glLinkProgram(program);
        GL40.glValidateProgram(program);
    }

    int CompileShader(int shaderType, String shaderCode) {
        int id = GL40.glCreateShader(shaderType);

        GL40.glShaderSource(id, shaderCode);
        GL40.glCompileShader(id);

        int result = GL40.glGetShaderi(id, GL40.GL_COMPILE_STATUS);

        if (result == GL40.GL_FALSE) {
            String message = GL40.glGetShaderInfoLog(id);

            System.out.println("Failed to compile " + (shaderType == GL40.GL_VERTEX_SHADER ? "vertex" : "fragment") + " shader!");
            System.out.println(message);

            GL40.glDeleteShader(id);
            return 0;
        }

        return id;
    }

    public void Use(){
        GL40.glUseProgram(program);
    }

    public void Delete(){
        GL40.glDeleteProgram(program);
    }
}
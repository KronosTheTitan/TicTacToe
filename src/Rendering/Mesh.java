package Rendering;

import org.lwjgl.opengl.GL40;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;


public class Mesh {
    public Mesh(float[] pVertices, int[] pIndices) {
        vertices = pVertices;
        indices = pIndices;

        vao = GL40.glGenVertexArrays();
        GL40.glBindVertexArray(vao);

        buffer = GL40.glGenBuffers();
        GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, buffer);
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, vertices, GL40.GL_STATIC_DRAW );

        GL40.glEnableVertexAttribArray(0);
        GL40.glVertexAttribPointer(0, 2, GL40.GL_FLOAT, false, 4 * Float.BYTES, 0);
        GL40.glEnableVertexAttribArray(1);
        GL40.glVertexAttribPointer(1,2,GL40.GL_FLOAT, false, 4 * Float.BYTES, 2 * Float.BYTES);

        ibo = GL40.glGenBuffers();
        GL40.glBindBuffer(GL40.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL40.glBufferData(GL40.GL_ELEMENT_ARRAY_BUFFER, indices, GL40.GL_STATIC_DRAW );
    }

    float[] vertices;
    int[] indices;

    int vao;
    int buffer;
    int ibo;

    public void Render(){
        GL40.glBindVertexArray(vao);
        GL40.glBindBuffer(GL40.GL_ELEMENT_ARRAY_BUFFER, ibo);

        GL40.glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
        GL40.glBindVertexArray(0);
    }
}

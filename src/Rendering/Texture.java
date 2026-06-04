package Rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL40;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Texture {
    public int id;

    public Texture(String filename) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ByteBuffer pixels = imageToBuffer(image);

        GL40.glEnable(GL40.GL_TEXTURE_2D);

        id = GL40.glGenTextures();

        GL40.glBindTexture(GL40.GL_TEXTURE_2D, id);

        GL40.glPixelStorei(GL40.GL_UNPACK_ALIGNMENT, 1);

        //Setup filtering, i.e. how OpenGL will interpolate the pixels when scaling up or down
        GL40.glTexParameteri(GL40.GL_TEXTURE_2D, GL40.GL_TEXTURE_MIN_FILTER, GL40.GL_NEAREST);
        GL40.glTexParameteri(GL40.GL_TEXTURE_2D, GL40.GL_TEXTURE_MAG_FILTER, GL40.GL_NEAREST);

        //Setup wrap mode, i.e. how OpenGL will handle pixels outside of the expected range
        //Note: GL_CLAMP_TO_EDGE is part of GL12
        GL40.glTexParameteri(GL40.GL_TEXTURE_2D, GL40.GL_TEXTURE_WRAP_S, GL40.GL_CLAMP_TO_EDGE);
        GL40.glTexParameteri(GL40.GL_TEXTURE_2D, GL40.GL_TEXTURE_WRAP_T, GL40.GL_CLAMP_TO_EDGE);

        GL40.glTexImage2D(GL40.GL_TEXTURE_2D, 0, GL40.GL_RGBA, image.getWidth(), image.getHeight(), 0, GL40.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
    }

    public static ByteBuffer imageToBuffer(BufferedImage image) {
        Raster raster = image.getData();

        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());

        ByteBuffer buffer = ByteBuffer.allocateDirect(pixels.length * 4);

        for (int y = image.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);

                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }

        buffer.flip();
        return buffer;
    }
}

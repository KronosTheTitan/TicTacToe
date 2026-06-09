import Rendering.*;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public void run() {
        try {
            Renderer renderer = new Renderer();

            Game game = new Game(renderer);

            renderer.Setup();

            game.Start();

            renderer.Run();
            renderer.Stop();
        } catch (Exception e) {
            try {
                FileWriter writer = new FileWriter("log.txt");
                writer.write(e.getMessage() + "\n");
                writer.write(e.getClass().getName());
                writer.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
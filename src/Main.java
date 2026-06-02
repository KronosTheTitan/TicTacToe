import Rendering.*;
import org.lwjgl.*;

public class Main {
    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        Renderer renderer = new Renderer();

        Game game = new Game(renderer);

        renderer.Setup();

        game.Start();

        renderer.Run();
        renderer.Stop();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
import Rendering.*;

public class Main {
    public void run() {
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
import Rendering.*;
import Rendering.Math.Vec2;
import Rendering.UserInterface.UI_Element;

public class Game {
    Renderer renderer;

    public Game(Renderer renderer) {
        this.renderer = renderer;
    }

    public void Start(){
        System.out.println("Starting game");

        Texture texture = new Texture("./resources/textures/BackgroundGrid.png");
        UI_Element background = new UI_Element("Background", new Vec2(0,0), new Vec2(800, 800), texture);

        renderer.AddGameObject(background,0);
    }

    public void Update(){

    }
}

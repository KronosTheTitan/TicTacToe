import Rendering.*;
import Rendering.Math.Vec2;
import Rendering.UserInterface.Button;
import Rendering.UserInterface.UI_Element;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Random;

public class Game extends GameObject{
    Renderer renderer;
    Button[][] markings = new Button[3][9];

    WinValidator validator;
    boolean gameActive = true;
    private int[] gameState = {
            0,0,0,
            0,0,0,
            0,0,0
    };

    GameObject background;
    GameObject playerWin;
    GameObject compWin;
    GameObject stalemate;
    int totalMoves = 0;

    public Game(Renderer renderer) {
        super("Game");
        this.renderer = renderer;
    }

    public void Start(){
        Texture backgroundTex = new Texture("./resources/textures/BackgroundGrid.png");
        background = new UI_Element("Background", new Vec2(0,0), new Vec2(800, 800), backgroundTex);

        Texture playerWinTex = new Texture("./resources/textures/playerWin.png");
        playerWin = new UI_Element("playerWin", new Vec2(0,0), new Vec2(800, 800), playerWinTex);
        playerWin.enabled = false;

        Texture compWinTex = new Texture("./resources/textures/compWin.png");
        compWin = new UI_Element("compWin", new Vec2(0,0), new Vec2(800, 800), compWinTex);
        compWin.enabled = false;

        Texture stalemateTex = new Texture("./resources/textures/stalemate.png");
        stalemate = new UI_Element("stalemate", new Vec2(0,0), new Vec2(800, 800), stalemateTex);
        stalemate.enabled = false;

        AddChild(background);
        AddChild(playerWin);
        AddChild(compWin);
        AddChild(stalemate);

        Texture[] textures = {
                new Texture("./resources/textures/Blank.png"),
                new Texture("./resources/textures/Cross.png"),
                new Texture("./resources/textures/Circle.png"),
                new Texture("./resources/textures/Hover.png")
        };

        Vec2 markingExtents = new Vec2(224,224);
        Vec2[] markingOrigins = {
            new Vec2(32,32), new Vec2(288,32),new Vec2(544,32),
            new Vec2(32,288), new Vec2(288,288),new Vec2(544,288),
            new Vec2(32,544), new Vec2(288,544),new Vec2(544,544),
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                markings[i][j] = new Button("Marking" + j + "_" + i, markingOrigins[j], markingExtents, textures[i]);

                background.AddChild(markings[i][j]);

                if(i != 0)
                    markings[i][j].enabled = false;
                else {
                    UI_Element hoverOutline = new UI_Element("hoverOutline", markingOrigins[j], markingExtents, textures[3]);
                    markings[i][j].SetHoverEffect(hoverOutline);
                }
            }
        }

        renderer.AddGameObject(this,0);

        validator = new WinValidator();
    }

    @Override
    public void Render() {
        if(!enabled)
            return;

        Vec2 mousePos = Input.getMousePosition();

        for (int i = 0; i < 9; i++) {
            if(!gameActive){
                break;
            }
            if(totalMoves == 9){
                gameActive = false;

                background.enabled = false;
                stalemate.enabled = true;

                break;
            }

            if(!markings[0][i].enabled)
                continue;

            if(!markings[0][i].IsMouseOver(mousePos)){
                markings[0][i].DisableHoverEffect();
                continue;
            }

            markings[0][i].EnableHoverEffect();

            if(!Input.getMouseButtonDown())
                continue;

            if(gameState[i] != 0)
                continue;

            gameState[i] = 1;
            markings[0][i].enabled = false;
            markings[1][i].enabled = true;
            totalMoves++;

            if(validator.Validate(1,gameState)){
                gameActive = false;

                background.enabled = false;
                playerWin.enabled = true;
            }
            else {
                ComputerMoves();
            }
        }

        for (GameObject child : children) {
            child.Render();
        }
    }

    private void ComputerMoves(){
        if(totalMoves == 9)
            return;

        ArrayList<Integer> legalMoves = new ArrayList<Integer>();

        for (int i = 0; i < 9; i++) {
            if(gameState[i] == 0)
                legalMoves.add(i);
        }

        Random random = new Random();
        int target = legalMoves.get(random.nextInt(legalMoves.size()));

        gameState[target] = 2;
        markings[0][target].enabled = false;
        markings[2][target].enabled = true;
        totalMoves++;

        if(validator.Validate(2,gameState)){
            gameActive = false;

            background.enabled = false;
            compWin.enabled = true;
        }
    }
}
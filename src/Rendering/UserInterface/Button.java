package Rendering.UserInterface;

import Rendering.Math.Vec2;
import Rendering.Texture;

public class Button extends UI_Element {
    Runnable onClick;

    public Button(String name, Vec2 position, Vec2 extents, Texture texture, Runnable onClick) {
        super(name, position, extents, texture);

        this.onClick = onClick;
    }

    public void OnClick() {
        onClick.run();
    }
}

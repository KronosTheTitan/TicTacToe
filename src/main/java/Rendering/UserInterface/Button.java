package Rendering.UserInterface;

import Rendering.GameObject;
import Rendering.Math.Vec2;
import Rendering.Texture;

public class Button extends UI_Element {
    Vec2 origin;
    Vec2 extents;

    public Button(String name, Vec2 position, Vec2 extents, Texture texture) {
        this.origin = position;
        this.extents = extents;
        super(name, position, extents, texture);
    }

    public boolean IsMouseOver(Vec2 mousePos) {
        if(!(origin.x <= mousePos.x && origin.x + extents.x > mousePos.x))
            return false;
        return origin.y <= mousePos.y && origin.y + extents.y > mousePos.y;
    }

    private GameObject hoverEffect;
    public void SetHoverEffect(GameObject pHover){
        hoverEffect = pHover;
        AddChild(hoverEffect);
        hoverEffect.enabled = false;
    }

    public void EnableHoverEffect(){
        hoverEffect.enabled = true;
    }

    public void DisableHoverEffect(){
        hoverEffect.enabled = false;
    }
}

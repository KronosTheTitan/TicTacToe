package Rendering;

import java.util.ArrayList;

public class GameObject {
    public String name;

    public boolean enabled = true;

    ArrayList<GameObject> children;

    public GameObject(String name) {
        this.name = name;

        children = new ArrayList<GameObject>();
    }

    public void AddChild(GameObject child) {
        children.add(child);
    }
    public void RemoveChild(GameObject child) {
        children.remove(child);
    }

    public void SetActive(boolean active) {
        enabled = active;
    }

    public void Render() {
        if(!enabled)
            return;

        for (GameObject child : children) {
            child.Render();
        }
    }
}

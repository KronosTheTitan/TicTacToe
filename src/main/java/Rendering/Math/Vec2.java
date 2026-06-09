package Rendering.Math;

public class Vec2 {
    public float x;
    public float y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return Mathf.sqrt((x * x) + (y * y));
    }

    @Override
    public String toString() {
        return "{x = " + x + " y = " + y + "}";
    }
}

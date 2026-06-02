package Rendering.Math;

public class Mathf {
    public static float sqrt(float x) {
        return (float) Math.sqrt(x);
    }

    public static float lerp(float a, float b, float t) {
        return (1.0f - t) * a + b * t;
    }

    public static float invLerp(float a, float b, float v) {
        return (v - a) / (b - a);
    }

    public static float remap(float iMin, float iMax, float oMin, float oMax, float v) {
        float t = invLerp(iMin,iMax,v);
        return lerp(oMin,oMax,t);
    }

    public static float sin(float x) {
        return (float) Math.sin(x);
    }

    public static float cos(float x) {
        return (float) Math.cos(x);
    }

    public static float tan(float x) {
        return (float) Math.tan(x);
    }
    public static float pow(float x, float y) {
        return (float) Math.pow(x, y);
    }

}

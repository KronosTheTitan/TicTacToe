package Rendering.Math;

public class Vec3 {
    public float x;
    public float y;
    public float z;

    public float length() {
        float xy = Mathf.sqrt(x*x+y*y);
        return Mathf.sqrt((xy * xy) + (z * z));
    }
}

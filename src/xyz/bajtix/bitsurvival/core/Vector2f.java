package xyz.bajtix.bitsurvival.core;


public class Vector2f {

    public float x;
    public float y;
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2f lerp(Vector2f a, Vector2f b, float t) {
        return new Vector2f(Util.round(Util.lerp(a.x,b.x,t)), Util.round(Util.lerp(a.y,b.y,t)));
    }
}

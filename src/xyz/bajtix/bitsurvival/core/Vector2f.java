package xyz.bajtix.bitsurvival.core;

import com.sun.javafx.geom.Vec2f;

public class Vector2f extends Vec2f {
    public Vector2f() {
        super(0,0);
    }

    public Vector2f(float x, float y) {
        super(x, y);
    }

    public static Vec2f lerp(Vec2f a, Vec2f b, float t) {
        return new Vec2f(Util.round(Util.lerp(a.x,b.x,t)), Util.round(Util.lerp(a.y,b.y,t)));
    }
}

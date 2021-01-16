package xyz.bajtix.bitsurvival.core;



public class Vector2
{
    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Vector2(){
        this.x = 0;
        this.y = 0;
    }

    public Vector2 add(int x,int y) {
        return new Vector2(this.x + x, this.y + y);
    }

    public Vector2 add(Vector2 v) {
        return add(v.x,v.y);
    }

    public static Vector2 lerp(Vector2 a,Vector2 b, float t) {
        return new Vector2(Util.round(Util.lerp(a.x,b.x,t)), Util.round(Util.lerp(a.y,b.y,t)));
    }

    public Vector2 mult(int t) {
        return new Vector2(x*t,y*t);
    }

    public Vector2f toVec2f() {
        return new Vector2f(x,y);
    }
}
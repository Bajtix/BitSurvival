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

    /**
     * Adds two vector
     * @param x X to add
     * @param y Y to add
     * @return The combined vector
     */
    public Vector2 add(int x,int y) {
        return new Vector2(this.x + x, this.y + y);
    }

    /**
     * Adds two vector
     * @param v Vector to add
     * @return The combined vector
     */
    public Vector2 add(Vector2 v) {
        return add(v.x,v.y);
    }

    /**
     * Linearly interpolates the Vector
     * @param a Point a
     * @param b Point b
     * @param t Time
     * @return Interpolated vector
     */
    public static Vector2 lerp(Vector2 a,Vector2 b, float t) {
        return new Vector2(Util.round(Util.lerp(a.x,b.x,t)), Util.round(Util.lerp(a.y,b.y,t)));
    }

    /**
     * Multiplies the vector
     * @param t Number value to multiply
     * @return The multiplied vector
     */
    public Vector2 mult(int t) {
        return new Vector2(x*t,y*t);
    }

    /**
     * Converts to Vector2Float
     * @return Converted Vector2F
     */
    public Vector2f toVec2f() {
        return new Vector2f(x,y);
    }

    /**
     * Calculates the distance using the Pythagoras theorem.
     * @param a Point A
     * @param b Point B
     * @return The distance
     */
    public static float distance(Vector2 a, Vector2 b) {
        return Util.sqrt(Util.pow(a.x - b.x,2) + Util.pow(a.y - b.y,2));
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
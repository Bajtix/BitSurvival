package xyz.bajtix.bitsurvival.core;


import oracle.jrockit.jfr.events.Bits;
import processing.core.PImage;
import xyz.bajtix.bitsurvival.BitSurvival;

public class Render {


    /*TODO:
    Change image scaling, so images are 16x16 or 8x8, to free some memory. Currently every image is ~256b; This should reduce it by more than a half.
    https://forum.processing.org/two/discussion/21391/resize-image-without-any-smoothing
     */

    public static Vector2 cameraPosition = new Vector2(64,64);
    public static Vector2f pixelCameraPosition = new Vector2f(0,0);

    public static void updateCamera() {
        pixelCameraPosition = Vector2f.lerp(pixelCameraPosition,cameraPosition.mult(32).toVec2f(),Util.baseTime() * 0.2f);
    }

    public static void image(PImage img,float x, float y) {
        BitSurvival.bitSurvival.image(img,x,y);
    }

    public static void image(PImage img,float x, float y,float w, float h) {
        BitSurvival.bitSurvival.image(img,x,y,w,h);
    }

    public static void renderImage(PImage img, float x, float y) {
        image(img, x*32 - pixelCameraPosition.x, y*32 - pixelCameraPosition.y);
    }

    public static void renderImage(PImage img, float x, float y,float w, float h) {
        image(img, x*32 - pixelCameraPosition.x, y*32 - pixelCameraPosition.y,w,h);
    }

    public static void text(String text, float posx,float posy) {
        BitSurvival.bitSurvival.text(text,posx,posy);
    }

    public static void text(String text, float posx, float posy, float w, float h) {BitSurvival.bitSurvival.text(text,posx,posy,w,h);}

    public static void textSize(float s) { BitSurvival.bitSurvival.textSize(s);}

    public static void textAlign(int x,int y) { BitSurvival.bitSurvival.textAlign(x,y);}

    public static void rect(float x, float y, float w, float h) {
        BitSurvival.bitSurvival.rect(x,y,w,h);
    }

    public static void renderRect(float x, float y, float w, float h) {
        rect(x*32-pixelCameraPosition.x,y*32-pixelCameraPosition.y,w,h);
    }

    public static void fill(int v) {
        BitSurvival.bitSurvival.fill(v);
    }

    public static void fill(int r,int g, int b) {
        BitSurvival.bitSurvival.fill(r,g,b);
    }

    public static void fill(int r,int g, int b, int a) {
        BitSurvival.bitSurvival.fill(r,g,b,a);
    }

}

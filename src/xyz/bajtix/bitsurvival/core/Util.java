package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.BitSurvival;

/**
 * Maps the non-static functions of Processing to easier names, and provides some other useful stuff
 */
public class Util {

    public static final int nativeTextureSize = 32;

    public static float time() {
        return BitSurvival.bitSurvival.millis() / 1000f;
    }

    public static float noise(float x, float y) {
        return BitSurvival.bitSurvival.noise(x,y);
    }

    public static PImage loadImage(String name) {
        return BitSurvival.bitSurvival.loadImage(name);
    }

    public static float lerp(float a, float b, float t) {
        return BitSurvival.lerp(a,b,t);
    }

    public static int round(float f){
        return BitSurvival.round(f);
    }

    public static float round(float f,int dp){
        return BitSurvival.round(f * Util.pow(10,dp)) / Util.pow(10,dp);
    }

    public static float deltaTime() {
        return BitSurvival.bitSurvival.deltaTime;
    }

    public static float baseTime() {
        return BitSurvival.bitSurvival.deltaTime / 15f;
    }

    public static float pow(float n, float exp) {
        return BitSurvival.pow(n,exp);
    }

    public static boolean inbounds(int i,int l) {
        return i >= 0 && i < l;
    }

    public static float clamp(float v, float min, float max) {
        return BitSurvival.constrain(v,min,max);
    }
}

package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.BitSurvival;

/**
 * Maps the non-static functions of Processing to easier names, and provides some other useful stuff
 */
public class Util {
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

    public static float deltaTime() {
        return BitSurvival.bitSurvival.deltaTime;
    }

    public static float baseTime() {
        return BitSurvival.bitSurvival.deltaTime / 15f;
    }

    public static boolean inbounds(int i,int l) {
        return i > 0 && i < l;
    }
}

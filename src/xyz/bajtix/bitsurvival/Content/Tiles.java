package xyz.bajtix.bitsurvival.Content;

import xyz.bajtix.bitsurvival.core.Tile;
import xyz.bajtix.bitsurvival.core.Util;

public class Tiles {
    public static Tile
        dirt,
        snow,
        tree,
        water;


    public static void loadTiles() {
        dirt = new Tile(0, Util.loadImage("data/dirt.png"),false);
        snow = new Tile(1,Util.loadImage("data/snow.png"),false);
        tree = new Tile(2,Util.loadImage("data/tree.png"),true);
        water = new Tile(3,Util.loadImage("data/water.png"),true);

        System.out.println("Loading tiles");
    }
}

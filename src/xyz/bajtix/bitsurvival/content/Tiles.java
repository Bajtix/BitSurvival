package xyz.bajtix.bitsurvival.content;

import xyz.bajtix.bitsurvival.content.tiles.IceTile;
import xyz.bajtix.bitsurvival.content.tiles.WaterTile;
import xyz.bajtix.bitsurvival.core.AnimatedTileSheet;
import xyz.bajtix.bitsurvival.core.Tile;
import xyz.bajtix.bitsurvival.core.TileSheet;
import xyz.bajtix.bitsurvival.core.Util;

public class Tiles {
    public static Tile
    dirt,
    snow,
    tree,
    water,
    ice;


    public static void loadTiles() {
        dirt = new Tile(0, Util.loadImage("data/dirt.png"),false);
        snow = new Tile(1,Util.loadImage("data/snow.png"),false);
        tree = new Tile(2,Util.loadImage("data/tree.png"),true);
        water = new WaterTile(3, Util.loadImage("data/water.png"),false,4, .5f);
        ice = new IceTile(4, Util.loadImage("data/ice.png"),false,3);
    }
}

package xyz.bajtix.bitsurvival.content;

import com.sun.deploy.util.ReflectionUtil;
import xyz.bajtix.bitsurvival.content.tiles.*;
import xyz.bajtix.bitsurvival.core.*;

import java.lang.reflect.Field;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.logging.Logger;

public class Tiles {
    public static Tile
    dirt,
    snow,
    tree,
    water,
    ice,
    bonfire,
    bush,
    wood;

    private static HashMap<Integer,Tile> tiles;


    public static void loadTiles() {
        GameLogger.debug("Loading tiles...");
        dirt = new Tile(0, Util.loadImage("data/dirt.png"),false);
        snow = new Tile(1,Util.loadImage("data/snow.png"),false);
        tree = new TreesTile(2,Util.loadImage("data/tree.png"),true);
        water = new WaterTile(3, Util.loadImage("data/water.png"),false,4, .5f);
        ice = new IceTile(4, Util.loadImage("data/ice.png"),false,3);
        bonfire = new BonfireTile(5,Util.loadImage("data/bonfire.png"),false,3,.1f);
        bush = new BushTile(6,Util.loadImage("data/bush.png"),false);
        wood = new WoodTile(7, Util.loadImage("data/wood.png"),false,2);

        regAll();
        GameLogger.debug("Loaded tiles!");
    }

    public static boolean isTileInTiles(Tile t, Tile[] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            if(tiles[i].equals(t))
                return true;
        }
        return false;
    }

    public static void regAll() {
        tiles = new HashMap<>();
        for (Field f : Tiles.class.getDeclaredFields()) {
            try {
                if(f.getType() != Tile.class)
                    continue;
                Tile t = (Tile)f.get(null);
                tiles.put(t.id,t);
            } catch (IllegalAccessException e) {
                GameLogger.err("Tile registry failed. Stacktrace: " + e.getStackTrace().toString());
            }

        }

        GameLogger.debug("Finished registering tiles. Registered " + tiles.size());
    }

    public static Tile getTileById(int i){
        return tiles.get(i);
    }
}

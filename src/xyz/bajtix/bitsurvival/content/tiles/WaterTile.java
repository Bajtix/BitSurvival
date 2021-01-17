package xyz.bajtix.bitsurvival.content.tiles;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.core.AnimatedTileSheet;
import xyz.bajtix.bitsurvival.core.Player;
import xyz.bajtix.bitsurvival.core.Tile;
import xyz.bajtix.bitsurvival.core.Util;

public class WaterTile extends AnimatedTileSheet {

    private float nextTime;

    public WaterTile(int id, PImage graphic, boolean collision, int frameCount, float frameTime) {
        super(id, graphic, collision, frameCount, frameTime);
    }

    @Override
    public void onPlayerUpdate(Player p) {
        if(nextTime <= Util.time()){
            p.damage(5);
            nextTime = Util.time() + 0.25f;
        }
    }

    @Override
    public Tile clone() {
        return new WaterTile(id,graphic,collision,getFrameCount(),frameTime);
    }
}

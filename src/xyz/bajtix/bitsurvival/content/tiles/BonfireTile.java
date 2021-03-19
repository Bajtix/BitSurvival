package xyz.bajtix.bitsurvival.content.tiles;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.core.AnimatedTileSheet;
import xyz.bajtix.bitsurvival.core.HeatEmitter;
import xyz.bajtix.bitsurvival.core.Tile;
import xyz.bajtix.bitsurvival.core.Vector2;


public class BonfireTile extends AnimatedTileSheet implements HeatEmitter {

    public BonfireTile(int id, PImage graphic, boolean collision, int frameCount, float frameTime) {
        super(id, graphic, collision, frameCount, frameTime);
    }

    @Override
    public void onPlace(Tile previous) {
        super.onPlace(previous);
        world.addHeatEmitter(this);
    }

    @Override
    public void onReplace() {
        super.onReplace();
        world.removeHeatEmitter(this);
    }

    @Override
    public float getHeatLevel() {
        return 15;
    }

    @Override
    public float getHeatRange() {
        return 7;
    }

    @Override
    public Vector2 getHeatCenter() {
        return pos;
    }

    @Override
    public Tile clone() {
        return new BonfireTile(id,graphic,collision,getFrameCount(),frameTime);
    }
}

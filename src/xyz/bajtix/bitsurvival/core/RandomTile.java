package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;

public class RandomTile extends TileSheet {
    public RandomTile(int id, PImage graphic, boolean collision, int frameCount) {
        super(id, graphic, collision, frameCount);
    }

    @Override
    public void onPlace(Tile previous) {
        super.onPlace(previous);
        int i = Util.round(Util.random(getFrameCount()-1));
        this.setFrame(i);
    }

    @Override
    public Tile clone() {
        return new RandomTile(id,graphic,collision,getFrameCount());
    }
}

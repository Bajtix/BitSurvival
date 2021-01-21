package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.content.Items;

public class TopRandomBuildingTile extends RandomTile  {

    protected Tile previous;

    public TopRandomBuildingTile(int id, PImage graphic, boolean collision, int frameCount) {
        super(id, graphic, collision, frameCount);
    }

    @Override
    public void onPlace(Tile previous) {
        super.onPlace(previous);
        this.previous = previous;
    }


    @Override
    public Tile clone() {
        return new TopRandomBuildingTile(id,graphic,collision,getFrameCount());
    }
}

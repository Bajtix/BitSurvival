package xyz.bajtix.bitsurvival.content.tiles;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.content.Tiles;
import xyz.bajtix.bitsurvival.core.Player;
import xyz.bajtix.bitsurvival.core.Tile;
import xyz.bajtix.bitsurvival.core.TileSheet;

public class IceTile extends TileSheet {
    public IceTile(int id, PImage graphic, boolean collision, int frameCount) {
        super(id, graphic, collision, frameCount);
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();
    }

    @Override
    public void onStepOn(Player p) {
        nextFrame();

        if(getFrame() == getFrameCount()) {
            world.get().addTile(Tiles.water, pos.x, pos.y);
        }
    }

    @Override
    public Tile clone() {
        return new IceTile(id,graphic,collision,getFrameCount());
    }
}

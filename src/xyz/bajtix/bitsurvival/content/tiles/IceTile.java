package xyz.bajtix.bitsurvival.content.tiles;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.content.Tiles;
import xyz.bajtix.bitsurvival.core.*;

public class IceTile extends TileSheet implements HeatEmitter {

    private float meltTime = 0;
    private boolean isMelting = false;

    public IceTile(int id, PImage graphic, boolean collision, int frameCount) {
        super(id, graphic, collision, frameCount);
    }

    @Override
    public void onStepOn(Player p) {
        nextFrame();

        if(getFrame() == getFrameCount()) {
            world.get().addTile(Tiles.water, pos.x, pos.y);
        }
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();

        if(world.get().getHeat(pos) > 4.5f) {
            if(!isMelting) {
                meltTime = Util.time() + Util.random(20);
            }
            isMelting = true;
        }
        else
        {
            isMelting = false;
        }


        if(meltTime <= Util.time() && isMelting){
            world.get().addTile(Tiles.water,pos);
        }
    }

    @Override
    public Tile clone() {
        return new IceTile(id,graphic,collision,getFrameCount());
    }

    @Override
    public float getHeatLevel() {
        return 7;
    }

    @Override
    public float getHeatRange() {
        return 3;
    }

    @Override
    public Vector2 getHeatCenter() {
        return pos;
    }
}

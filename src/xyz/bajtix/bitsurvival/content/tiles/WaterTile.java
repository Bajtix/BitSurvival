package xyz.bajtix.bitsurvival.content.tiles;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.content.Tiles;
import xyz.bajtix.bitsurvival.core.AnimatedTileSheet;
import xyz.bajtix.bitsurvival.core.Player;
import xyz.bajtix.bitsurvival.core.Tile;
import xyz.bajtix.bitsurvival.core.Util;

public class WaterTile extends AnimatedTileSheet {

    private float nextTime;

    private float meltTime;
    private boolean isMelting = false;

    public WaterTile(int id, PImage graphic, boolean collision, int frameCount, float frameTime) {
        super(id, graphic, collision, frameCount, frameTime);
    }

    @Override
    public void update() {
        super.update();

        if(world.get().getHeat(pos) <= 4.5f) {
            if(!isMelting)
                meltTime = Util.time() + Util.random(20);
            isMelting = true;
        }
        else
        {
            isMelting = false;
        }


        if(meltTime <= Util.time() && isMelting){
            world.get().addTile(Tiles.ice,pos);
        }
    }

    @Override
    public void onPlayerUpdate(Player p) {
        if(nextTime <= Util.time()){
            p.damage(5, false);
            nextTime = Util.time() + 0.25f;
        }
    }

    @Override
    public float getPlayerSpeed() {
        return 2;
    }

    @Override
    public Tile clone() {
        return new WaterTile(id,graphic,collision,getFrameCount(),frameTime);
    }
}

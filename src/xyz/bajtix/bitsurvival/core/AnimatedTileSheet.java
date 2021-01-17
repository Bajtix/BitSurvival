package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;

public class AnimatedTileSheet extends TileSheet {

    private float nextFrame = 0;
    private float frameTime;

    public AnimatedTileSheet(int id, PImage graphic, boolean collision, int frameCount, float frameTime) {
        super(id, graphic, collision, frameCount);
        this.frameTime = frameTime;
    }

    @Override
    public void updateAnimation() {

        if(Util.time() > nextFrame) {
            nextFrame();
            nextFrame = Util.time() + frameTime;
        }

        super.updateAnimation();
    }

    @Override
    public Tile clone() {
        return new AnimatedTileSheet(id,graphic,collision,getFrameCount(),frameTime);
    }
}

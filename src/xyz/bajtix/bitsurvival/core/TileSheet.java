package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;

public class TileSheet extends Tile{

    private int frame;
    private int frameCount;

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public void nextFrame() {
        this.frame++;
    }

    public void previousFrame() { this.frame--; }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    private final PImage[] frames;

    public TileSheet(int id, PImage graphic, boolean collision,int frameCount) {
        super(id, graphic, collision);
        this.frame = 0;
        this.frameCount = frameCount;
        this.frames = new PImage[frameCount];
        for(int i = 0; i < frameCount; i++){
            frames[i] = this.graphic.get(i*Util.nativeTextureSize,0,Util.nativeTextureSize,Util.nativeTextureSize);
        }
    }

    @Override
    public void updateAnimation() {
        if(Util.inbounds(frame,frameCount))
            this.graphic = frames[frame];
        else
            frame = 0;
        super.updateAnimation();
    }

    public Tile clone(){
        return new TileSheet(this.id,this.graphic,this.collision,this.frameCount);
    }
}

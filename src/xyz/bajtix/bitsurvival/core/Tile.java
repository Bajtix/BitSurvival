package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.BitSurvival;

import java.lang.ref.SoftReference;

public class Tile
{
    public int id;
    public PImage graphic;
    public boolean collision;

    public Vector2 pos;
    public SoftReference<World> world;

    public Tile(int id, PImage graphic, boolean collision)
    {
        this.id = id;
        this.graphic = graphic;
        this.collision = collision;
    }

    public void update() {

    }

    public void updateAnimation() {

    }

    public void onStepOn(Player p) {

    }

    public void onPlayerUpdate(Player p) {

    }

    @Override
    public Tile clone(){
        return new Tile(this.id,this.graphic,this.collision);
    }
}
package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;

public class Tile
{
    public int id;
    public PImage graphic;
    public boolean collision;

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

    @Override
    public Tile clone(){
        return new Tile(this.id,this.graphic,this.collision);
    }
}
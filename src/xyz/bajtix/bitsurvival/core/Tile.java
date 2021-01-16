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
}
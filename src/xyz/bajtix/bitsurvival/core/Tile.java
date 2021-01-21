package xyz.bajtix.bitsurvival.core;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.BitSurvival;

import java.lang.ref.SoftReference;
import java.util.Objects;

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

    public void onPlace(Tile t) {

    }

    public void onReplace() {

    }

    public void update() {

    }

    public void updateAnimation() {

    }

    public void onStepOn(Player p) {

    }

    public void onPlayerUpdate(Player p) {

    }

    public void interact(Player p) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return id == tile.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Tile clone(){
        return new Tile(this.id,this.graphic,this.collision);
    }

    public float getPlayerSpeed() {
        return 1;
    }
}
package xyz.bajtix.bitsurvival.content.tiles;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.content.Items;
import xyz.bajtix.bitsurvival.core.FiniteResource;
import xyz.bajtix.bitsurvival.core.ItemStack;
import xyz.bajtix.bitsurvival.core.Tile;
import xyz.bajtix.bitsurvival.core.TopRandomBuildingTile;

public class WoodTile extends TopRandomBuildingTile implements FiniteResource {
    public WoodTile(int id, PImage graphic, boolean collision, int frameCount) {
        super(id, graphic, collision, frameCount);
    }

    @Override
    public Tile clone() {
        return new WoodTile(id,graphic,collision,getFrameCount());
    }

    @Override
    public float getPlayerSpeed() {
        return 0.7f;
    }

    @Override
    public Tile getBackupTile() {
        return previous;
    }

    @Override
    public ItemStack getDrop() {
        return new ItemStack(Items.wood,1);
    }

    @Override
    public String getTag() {
        return "wood";
    }
}

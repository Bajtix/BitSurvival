package xyz.bajtix.bitsurvival.content.tiles;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.content.Items;
import xyz.bajtix.bitsurvival.core.*;

public class WoodTile extends TopRandomBuildingTile implements Resource {
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

    /**
     * Deals damage to tile
     *
     * @param damage How much damage to deal
     * @param player The player that damages the tile
     * @param world The world in which the action takes place
     * @return The drop after the hit
     */
    @Override
    public ItemStack hit(float damage, Player player, World world) {
        world.addTile(getBackupTile(),pos);
        return new ItemStack(Items.wood,1);
    }


    @Override
    public String getTag() {
        return "wood";
    }

    @Override
    public int getHarvestLevel() {
        return 0;
    }
}

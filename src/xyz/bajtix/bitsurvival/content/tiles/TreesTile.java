package xyz.bajtix.bitsurvival.content.tiles;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.content.Items;
import xyz.bajtix.bitsurvival.content.Tiles;
import xyz.bajtix.bitsurvival.core.*;

import java.lang.ref.SoftReference;

public class TreesTile extends Tile implements Resource {

    private int hp = 15;

    public TreesTile(int id, PImage graphic, boolean collision) {
        super(id, graphic, collision);
    }

    @Override
    public Tile getBackupTile() {
        return Tiles.snow;
    }

    /**
     * Deals damage to tile
     *
     * @param damage How much damage to deal
     * @param player
     * @param world
     * @return The drop after the hit
     */
    @Override
    public ItemStack hit(float damage, SoftReference<Player> player, SoftReference<World> world) {
        hp-=damage;
        if(hp <= 0) {
            world.get().addTile(getBackupTile(),pos);
            return new ItemStack(Items.wood,
                    Util.round(Util.random(15) + 10));
        }
        else
            return null;
    }

    @Override
    public String getTag() {
        return "wood";
    }

    @Override
    public int getHarvestLevel() {
        return 0;
    }

    @Override
    public Tile clone() {
        return new TreesTile(id,graphic,collision);
    }
}

package xyz.bajtix.bitsurvival.content.tiles;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.content.Items;
import xyz.bajtix.bitsurvival.content.Tiles;
import xyz.bajtix.bitsurvival.core.FiniteResource;
import xyz.bajtix.bitsurvival.core.ItemStack;
import xyz.bajtix.bitsurvival.core.Tile;
import xyz.bajtix.bitsurvival.core.Util;

public class TreesTile extends Tile implements FiniteResource {
    public TreesTile(int id, PImage graphic, boolean collision) {
        super(id, graphic, collision);
    }

    @Override
    public Tile getBackupTile() {
        return Tiles.snow;
    }

    @Override
    public ItemStack getDrop() {
        return new ItemStack(Items.wood,
                Util.round(Util.random(15) + 10));
    }

    @Override
    public String getTag() {
        return "wood";
    }

    @Override
    public Tile clone() {
        return new TreesTile(id,graphic,collision);
    }
}

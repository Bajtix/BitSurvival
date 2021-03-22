package xyz.bajtix.bitsurvival.content.tiles;

import processing.core.PImage;
import xyz.bajtix.bitsurvival.content.Items;
import xyz.bajtix.bitsurvival.content.Tiles;
import xyz.bajtix.bitsurvival.core.ItemStack;
import xyz.bajtix.bitsurvival.core.Player;
import xyz.bajtix.bitsurvival.core.Tile;
import xyz.bajtix.bitsurvival.core.Util;

public class TileBush extends Tile {
    public TileBush(int id, PImage graphic, boolean collision) {
        super(id, graphic, collision);
    }

    @Override
    public void interact(Player p) {
        p.inventory.insert(new ItemStack(Items.wood, Util.round(Util.random(3) + 1)));
        world.addTile(Tiles.snow,pos);
    }

    @Override
    public Tile clone() {
        return new TileBush(id,graphic,collision);
    }
}

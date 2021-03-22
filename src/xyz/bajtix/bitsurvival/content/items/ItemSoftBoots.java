package xyz.bajtix.bitsurvival.content.items;

import xyz.bajtix.bitsurvival.content.Tiles;
import xyz.bajtix.bitsurvival.content.tiles.TileIce;
import xyz.bajtix.bitsurvival.core.*;

public class ItemSoftBoots extends ArmorItem {
    public ItemSoftBoots(String name, int maxStackSize, int slot, StatModifier... modifier) {
        super(name, maxStackSize, slot, modifier);
    }


    public void onStep(Player player, World world) {
        if(world.getTile(player.position).equals(Tiles.ice)) {
            ((TileSheet)world.getTile(player.position)).previousFrame();
        }
    }

}

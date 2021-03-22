package xyz.bajtix.bitsurvival.content.items;

import xyz.bajtix.bitsurvival.content.Tiles;
import xyz.bajtix.bitsurvival.core.*;

public class ItemSoftBoots extends ArmorItem {
    public ItemSoftBoots(String name, int maxStackSize, int slot, StatModifier... modifier) {
        super(name, maxStackSize, slot, modifier);
        EventSystem.addListener("playerEvents",this);
    }

    @Override
    public void equippedTick(Player player, World world) {

        super.equippedTick(player, world);
        if (world.map[player.position.x][player.position.y].equals(Tiles.ice))
            GameLogger.debug("on ice with boots!");
    }


}

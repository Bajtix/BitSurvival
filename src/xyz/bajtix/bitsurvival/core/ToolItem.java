package xyz.bajtix.bitsurvival.core;

import java.lang.ref.SoftReference;

public class ToolItem extends Item {
    public ToolItem(String name, int maxStackSize, String destroys, int baseDmg, int meleeDmg, int harvestLevel) {
        super(name, maxStackSize);
    }

    @Override
    public void interact(Vector2 pos, SoftReference<ItemStack> in, SoftReference<World> world, SoftReference<Player> player) {
        if(world.get().map[pos.x][pos.y] instanceof FiniteResource) {
            ItemStack s = ((FiniteResource)world.get().map[pos.x][pos.y]).getDrop();
            player.get().inventory.insert(s);

            world.get().addTile(((FiniteResource) world.get().map[pos.x][pos.y]).getBackupTile(), pos);
        }
    }
}

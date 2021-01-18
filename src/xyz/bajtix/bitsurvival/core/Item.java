package xyz.bajtix.bitsurvival.core;

import java.lang.ref.SoftReference;

public class Item
{
    public String name;
    public int maxStackSize;

    public Item(String name, int maxStackSize) {
        this.name = name;
        this.maxStackSize = maxStackSize;
    }

    public void interact(Vector2 pos, SoftReference<ItemStack> in, SoftReference<World> world) {

    }
}

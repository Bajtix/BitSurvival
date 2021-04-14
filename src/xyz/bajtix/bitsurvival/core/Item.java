package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.content.Items;

import java.lang.reflect.Field;

public class Item implements Cloneable
{
    public final String name;
    public int maxStackSize;
    public final String tag;

    private boolean isMainInstance = false;

    public Item(String name, int maxStackSize) {
        this.name = name;
        this.maxStackSize = maxStackSize;
        this.isMainInstance = true;
        this.tag = "item";
    }

    public Item(String name, int maxStackSize, String tag) {
        this.name = name;
        this.maxStackSize = maxStackSize;
        this.isMainInstance = true;
        this.tag = tag;
    }

    public void interact(Vector2 pos, ItemStack in, World world, Player player) {

    }

    @Override
    protected Item clone()  {
        try {
            if(!isMainInstance)
                return Items.getItemByName(this.name).clone();
            else
                return (Item)super.clone();
        } catch (CloneNotSupportedException e) {
            GameLogger.err("Failed to create a clone of " + name + this.getClass().getCanonicalName());
            e.printStackTrace();
            return null;
        }
    }

    /*TODO: Cloning items. In order to implement it, I should make the same thing I did with Tiles, and make a registry for Items.
     * I can attempt to use the clone method to clone the item from registry by name, or create a copy constructor for each item. I should also remake the Tile system so it functions the same way for consistency reasons
     */

}

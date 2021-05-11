package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.content.Items;

import java.lang.reflect.Field;
import java.util.Objects;

public class Item implements Cloneable
{
    public final String name;
    public int maxStackSize;
    public final String tag;

    public boolean isMainInstance = false; //THIS SHOULD BE PROTECTED!!!

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
            else {
                Item slf = (Item) super.clone(); // Creates an exact clone, but is not main instance. This makes it possible to create new items from existing ones.
                slf.isMainInstance = false;
                return slf;
            }
        } catch (CloneNotSupportedException e) {
            GameLogger.err("Failed to create a clone of " + name + this.getClass().getCanonicalName());
            e.printStackTrace();
            return null;
        }
    }

    /*TODO: Cloning items. In order to implement it, I should make the same thing I did with Tiles, and make a registry for Items.
     * I can attempt to use the clone method to clone the item from registry by name, or create a copy constructor for each item. I should also remake the Tile system so it functions the same way for consistency reasons
     * UPDATE 11.05.21 : In theory, it is done, but kinda quirky.
     */

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Item)) {
            GameLogger.warning("Comparing Item with " +  o.getClass().getCanonicalName() + " will always return false;");
            return false;
        }
        return ((Item)o).name.equals(name);
    }
}

package xyz.bajtix.bitsurvival.core;

public class Item
{
    public String name;
    public int maxStackSize;
    public String tag = "item";

    public Item(String name, int maxStackSize) {
        this.name = name;
        this.maxStackSize = maxStackSize;
    }

    public Item(String name, int maxStackSize, String tag) {
        this.name = name;
        this.maxStackSize = maxStackSize;
        this.tag = tag;
    }

    public void interact(Vector2 pos, ItemStack in, World world, Player player) {

    }
}

package xyz.bajtix.bitsurvival.core;

public class ItemStack
{
    public Item item;
    public int count;

    /**
     * Creates a new ItemStack instance with the cloned item
     * @param item The item of the stack
     * @param count The count
     */
    public ItemStack(Item item,int count) {
        this.item = item.clone();
        this.count = count;
    }

    /**
     * Creates a new ItemStack instance
     * @param item The item of the stack
     * @param count The count
     * @param clone Should the item create a new instance? Defaults to true when not specified
     */
    public ItemStack(Item item,int count, boolean clone) {
        if(clone)
            this.item = item.clone();
        else
            this.item = item;
        this.count = count;
    }
}
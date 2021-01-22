package xyz.bajtix.bitsurvival.core;

import java.lang.ref.SoftReference;

public class Inventory
{
    public ItemStack[] stacks;

    public Inventory(int size) {
        this.stacks = new ItemStack[size];

        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = null;
        }
    }

    /**
     * Adds an item to the inventory
     * @param stack the stack to add
     * @return the remainder
     */
    public ItemStack insert(ItemStack stack) {

        if(stack == null) return null;

        for(ItemStack s : stacks) {
            if(s != null)
                if(s.item == stack.item) {
                    if(s.count < s.item.maxStackSize) {
                        int capacity = s.item.maxStackSize - s.count;
                        if(stack.count <= capacity) {
                            s.count+=stack.count;
                            stack.count = 0;
                        }
                        else {
                            s.count = s.item.maxStackSize;
                            stack.count -= capacity;
                        }
                    }
                }
        }
        if(stack.count > 0)
            for(int i = 0; i < stacks.length; i++) {
                if(stacks[i] == null) {
                    stacks[i] = stack;
                    return null;
                }
            }

        return stack;
    }

    public void validate() {
        for (int i = 0; i < stacks.length; i++) {
            if(stacks[i] != null && stacks[i].count <= 0)
                stacks[i] = null;
        }
    }

    public SoftReference<ItemStack> get(int slot) {
        return new SoftReference<>(stacks[slot]);
    }


}

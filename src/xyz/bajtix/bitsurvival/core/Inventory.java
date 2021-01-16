package xyz.bajtix.bitsurvival.core;

public class Inventory
{
    public ItemStack[] stacks;

    public Inventory(int size) {
        this.stacks = new ItemStack[size];
    }

    //Adds the ItemStack stack to the inventory. Return the remainder
    public ItemStack insert(ItemStack stack) {
        for(ItemStack s : stacks) {
            if(s.item == stack.item)
            {
                if(s.count < s.item.maxStackSize) {
                    int capacity = s.item.maxStackSize - s.count;
                    if(stack.count <= capacity)
                    {
                        s.count+=stack.count;
                        stack.count = 0;
                    }
                    else
                    {
                        s.count = s.item.maxStackSize;
                        stack.count -= capacity;
                    }
                }
            }
        }

        for(int i = 0; i < stacks.length; i++) {
            if(stacks[i] == null) {
                stacks[i] = stack;
                return null;
            }
        }

        return stack;
    }
}

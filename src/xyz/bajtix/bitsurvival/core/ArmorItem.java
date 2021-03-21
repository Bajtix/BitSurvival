package xyz.bajtix.bitsurvival.core;

public class ArmorItem extends Item implements Equipable {

    private final int slot;
    private final StatModifier[] modifier;

    public ArmorItem(String name, int maxStackSize, int slot, StatModifier... modifier) {
        super(name, maxStackSize);
        this.slot = slot;
        this.modifier = modifier;
    }


    @Override
    public void equippedTick(Player player, World world) {

    }

    @Override
    public StatModifier[] getModifier(Player player, World world) {
        return modifier;
    }

    @Override
    public boolean canEquip(Player player, World world) {
        return true; // TODO: make it so can be equipped only if player equipped does not contain an item with the same slot value
    }
}

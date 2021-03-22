package xyz.bajtix.bitsurvival.core;

public interface Equipable {
    void equippedTick(Player player, World world);
    void equipped(Player player, World world);
    void unequipped(Player player, World world);
    StatModifier[] getModifier(Player player, World world);

    boolean canEquip(Player player, World world);
}

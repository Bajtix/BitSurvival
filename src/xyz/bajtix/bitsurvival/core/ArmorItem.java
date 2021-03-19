package xyz.bajtix.bitsurvival.core;

public class ArmorItem extends Item implements Equipable {

    public ArmorItem(String name, int maxStackSize, int slot) {
        super(name, maxStackSize);
    }

    @Override
    public void addStuff() {

    }

    @Override
    public void multiplyStuff() {

    }

    @Override
    public boolean canEquip(Player player) {
        return true;
    }
}

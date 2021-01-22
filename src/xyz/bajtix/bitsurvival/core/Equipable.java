package xyz.bajtix.bitsurvival.core;

import java.lang.ref.SoftReference;

public interface Equipable {
    void addStuff();
    void multiplyStuff();

    boolean canEquip(SoftReference<Player> player);
}

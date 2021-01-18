package xyz.bajtix.bitsurvival.content;

import xyz.bajtix.bitsurvival.core.Item;
import xyz.bajtix.bitsurvival.core.TilePlaceItem;

import java.lang.ref.SoftReference;

public class Items {
    public static final Item testItem = new Item("TEST",8);
    public static final Item wood = new Item("WOOD",8);
    public static final Item bonfire = new TilePlaceItem("BONFIRE",1,new SoftReference<>(Tiles.bonfire));
}

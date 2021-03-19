package xyz.bajtix.bitsurvival.content;

import xyz.bajtix.bitsurvival.core.*;

import java.lang.ref.SoftReference;

public class Items {
    public static final Item wood = new TilePlaceItem("WOOD",32, new SoftReference<>(Tiles.wood),Tiles.snow,Tiles.dirt,Tiles.ice,Tiles.water);
    public static final Item bonfire = new TilePlaceItem("BONFIRE",1,new SoftReference<>(Tiles.bonfire), Tiles.snow);
    public static final Item woodAxe = new ToolItem("WOOD AXE",1, "wood",4,2,1,200);
    public static final Item softBoots = new ArmorItem("SOFT BOOTS", 1, EQUIPMENT_SLOTS.SHOES);
}

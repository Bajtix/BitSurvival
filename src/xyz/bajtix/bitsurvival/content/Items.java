package xyz.bajtix.bitsurvival.content;

import xyz.bajtix.bitsurvival.core.Item;
import xyz.bajtix.bitsurvival.core.TilePlaceItem;
import xyz.bajtix.bitsurvival.core.ToolItem;

import java.lang.ref.SoftReference;

public class Items {
    public static final Item wood = new TilePlaceItem("WOOD",32, new SoftReference<>(Tiles.wood),Tiles.snow,Tiles.dirt,Tiles.ice,Tiles.water);
    public static final Item bonfire = new TilePlaceItem("BONFIRE",1,new SoftReference<>(Tiles.bonfire), Tiles.snow);
    public static final Item woodAxe = new ToolItem("WOOD AXE",1, "wood",4,2,1);
}

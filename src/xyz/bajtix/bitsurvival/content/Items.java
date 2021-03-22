package xyz.bajtix.bitsurvival.content;

import xyz.bajtix.bitsurvival.content.items.ItemSoftBoots;
import xyz.bajtix.bitsurvival.core.*;

public class Items {
    public static final Item wood = new TilePlaceItem("WOOD",32, Tiles.wood, Tiles.snow,Tiles.dirt,Tiles.ice,Tiles.water);
    public static final Item bonfire = new TilePlaceItem("BONFIRE",1,Tiles.bonfire, Tiles.snow);
    public static final Item woodAxe = new ToolItem("WOOD AXE",1, "wood",4,2,1,200);

    public static final Item softBoots = new ItemSoftBoots("SOFT BOOTS", 1, EQUIPMENT_SLOTS.SHOES,
            new StatModifier(0.01f, StatModifier.StatMode.ADD, "stat_speedMultiplier"),
            new StatModifier(1f, StatModifier.StatMode.ADD, "stat_heatResistance")
            );
}

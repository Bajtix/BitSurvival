package xyz.bajtix.bitsurvival.content;

import xyz.bajtix.bitsurvival.content.items.ItemSoftBoots;
import xyz.bajtix.bitsurvival.core.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

public class Items {

    private static HashMap<String,Item> items;

    public static final Item wood = new TilePlaceItem("WOOD",32, Tiles.wood, Tiles.snow,Tiles.dirt,Tiles.ice,Tiles.water);
    public static final Item bonfire = new TilePlaceItem("BONFIRE",1,Tiles.bonfire, Tiles.snow);
    public static final Item woodAxe = new ToolItem("WOOD AXE",1, "wood",4,2,1,200);

    public static final Item softBoots = new ItemSoftBoots("SOFT BOOTS", 1, EQUIPMENT_SLOTS.SHOES,
            new StatModifier(0.01f, StatModifier.StatMode.ADD, "stat_speedMultiplier"),
            new StatModifier(1f, StatModifier.StatMode.ADD, "stat_heatResistance")
            );


    public static void regAll() {
        items = new HashMap<>();
        for (Field f : Items.class.getDeclaredFields()) {
            try {
                if(f.getType() != Item.class)
                    continue;
                Item t = (Item)f.get(null);
                items.put(t.name,t);
            } catch (IllegalAccessException e) {
                GameLogger.err("Item registry failed. Stacktrace: " + Arrays.toString(e.getStackTrace()));
            }
        }

        GameLogger.debug("Finished registering items. Registered " + items.size());
    }

    public static Item getItemByName(String name) {return items.get(name);}
}

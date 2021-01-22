package xyz.bajtix.bitsurvival.content.gui;

import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.content.GUIs;
import xyz.bajtix.bitsurvival.core.GUI;
import xyz.bajtix.bitsurvival.core.ItemStack;
import xyz.bajtix.bitsurvival.core.Player;
import xyz.bajtix.bitsurvival.core.Render;

import java.lang.ref.SoftReference;

public class InventoryGUI extends GUI {

    private final SoftReference<Player> player;

    public InventoryGUI(SoftReference<Player> player) {
        this.player = player;
    }

    private int selectedSlot;


    @Override
    public void update() {
        selectedSlot = player.get().selectedItem;
        Render.fill(0);
        Render.rect(112,90,480-224,480-180);
        Render.fill(255);
        Render.rect(116,94,480-224-8,480-180-8);
        Render.fill(0);
        Render.textSize(42);
        Render.textAlign(3,3);
        Render.text("Inventory",116,84,480-224-8,32);
        Render.textSize(24);
        Render.textAlign(BitSurvival.LEFT,BitSurvival.BOTTOM);

        int id = 0;
        for(ItemStack i : player.get().inventory.stacks) {
            String tname = "[EMPTY]";
            if(id == selectedSlot) {
                Render.fill(0);
            }
            else
                Render.fill(128);
            if(i != null) tname = i.item.name + " * " + i.count;
            Render.text(tname,118,124 + 16 * (id+1));


            id++;
        }

        Render.textAlign(BitSurvival.RIGHT,BitSurvival.BOTTOM);
        id = 0;
        for(ItemStack i : player.get().equipped.stacks) {
            String tname = "[EMPTY]";
            if(id == selectedSlot) {
                Render.fill(0);
            }
            else
                Render.fill(128);
            if(i != null) tname = i.item.name + " * " + i.count;
            Render.text(tname,480-118,124 + 16 * (id+1));


            id++;
        }
    }
}

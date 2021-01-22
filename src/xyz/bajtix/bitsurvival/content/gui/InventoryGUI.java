package xyz.bajtix.bitsurvival.content.gui;

import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.content.GUIs;
import xyz.bajtix.bitsurvival.core.*;

import java.lang.ref.SoftReference;

public class InventoryGUI extends GUI {

    private final SoftReference<Player> player;

    public InventoryGUI(SoftReference<Player> player) {
        this.player = player;
    }

    private int selectedSlot;


    @Override
    public void setup() {
        Keys.frame();
    }

    private void doInput() {
        if(Keys.isDown(39)) { //right
            if(player.get().getSelectedItemStack().get() instanceof Equipable) {
                player.get().getSelectedItemStack().get().count--;
                ItemStack s = player.get().getSelectedItemStack().get();
                player.get().equipped.insert(new ItemStack(s.item,1));
            }
        }

        if(Keys.isDown(37)) { //right
            if(player.get().equipped.stacks[player.get().selectedItem] != null) {
                player.get().equipped.stacks[player.get().selectedItem].count--;
                ItemStack s = player.get().equipped.stacks[player.get().selectedItem];
                player.get().inventory.insert(new ItemStack(s.item,1));
            }
        }

        if(Keys.isDown(38)) { //up
            if(player.get().selectedItem > 0)
                player.get().selectedItem --;
            else
                player.get().selectedItem = 7;
        }

        if(Keys.isDown(40)) { //down
            if(player.get().selectedItem < 7)
                player.get().selectedItem ++;
            else
                player.get().selectedItem = 0;
        }



        if(Keys.isDown('I')) {
            UIManager.open(new SoftReference<>(GUIs.baseGameGUI));
        }
    }

    @Override
    public void update() {

        doInput();

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

        //this section can be contained in just one for loop, but i am lazy
        int id = 0;
        for(ItemStack i : player.get().inventory.stacks) {
            String tname = "[EMPTY]";
            if(id == selectedSlot) {
                Render.fill(0);
            }
            else
                Render.fill(128);
            if(i != null) tname = i.item.name + " * " + i.count;
            Render.text(tname,118,154 + 16 * (id+1));

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
            Render.text(tname,480-118,154 + 16 * (id+1));

            id++;
        }
    }
}

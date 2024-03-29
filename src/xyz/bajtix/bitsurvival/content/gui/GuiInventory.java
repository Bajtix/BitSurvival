package xyz.bajtix.bitsurvival.content.gui;

import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.content.GUIs;
import xyz.bajtix.bitsurvival.core.*;

public class GuiInventory extends GUI {

    private final Player player;

    public GuiInventory(Player player) {
        this.player = player;
    }


    @Override
    public void setup() {
        Keys.frame();
    }

    private void doInput() {
        Player pl = player;
        if(Keys.isDown(39)) { //right
            if(pl.getSelectedItemStack().item instanceof Equipable) { // replace pl with player.get() is doesn't work
                pl.getSelectedItemStack().count--;
                ItemStack s = pl.getSelectedItemStack();
                ((Equipable)s.item).equipped(player, player.world);
                pl.equipped.insert(new ItemStack(s.item,1,false));
            }
        }

        if(Keys.isDown(37)) { //left
            if(pl.equipped.stacks[pl.selectedItem] != null) {
                pl.equipped.stacks[pl.selectedItem].count--;
                ItemStack s = pl.equipped.stacks[pl.selectedItem];
                ((Equipable)s.item).unequipped(player, player.world);
                pl.inventory.insert(new ItemStack(s.item,1,false));
            }
        }

        if(Keys.isDown(38)) { //up
            if(pl.selectedItem > 0)
                pl.selectedItem --;
            else
                pl.selectedItem = 7;
        }

        if(Keys.isDown(40)) { //down
            if(pl.selectedItem < 7)
                pl.selectedItem ++;
            else
                pl.selectedItem = 0;
        }



        if(Keys.isDown('I')) {
            UIManager.open(GUIs.baseGameGUI);
        }
    }

    @Override
    public void update() {

        doInput();

        int selectedSlot = player.selectedItem;
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
        for(ItemStack i : player.inventory.stacks) {
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
        for(ItemStack i : player.equipped.stacks) {
            String tname = "[EMPTY]";
            if(id == selectedSlot) {
                Render.fill(0);
            }
            else
                Render.fill(128);
            if(i != null) tname = i.item.name + " * " + i.count ;
            Render.text(tname,480-118,154 + 16 * (id+1));

            id++;
        }
    }
}

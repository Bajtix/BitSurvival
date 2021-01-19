package xyz.bajtix.bitsurvival.content.gui;

import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.core.GUI;
import xyz.bajtix.bitsurvival.core.Player;
import xyz.bajtix.bitsurvival.core.Render;
import xyz.bajtix.bitsurvival.core.Util;

import java.lang.ref.SoftReference;

public class BaseGameGUI extends GUI {

    public SoftReference<Player> player;

    public BaseGameGUI(SoftReference<Player> player) {
        this.player = player;
    }

    @Override
    public void update() {
        Player pl = player.get();
        Render.fill(0);
        Render.rect(8,4,240,48);
        Render.fill(255);
        Render.rect(12,8,232,40);
        Render.fill(188);
        Render.rect(16,32,(pl.health / 100f)*224,12);
        Render.fill(0);
        BitSurvival.bitSurvival.textSize(30);
        Render.text("HP: " + Util.round(pl.health,0),16,43);
        Render.text("HT: " + Util.round(pl.heat - 6f,2) + "*C", 16, 23);

        String itemName = "HAND";

        if(pl.getSelectedItemStack().get() != null)
            itemName = pl.getSelectedItemStack().get().item.name + " x " + pl.getSelectedItemStack().get().count;

        Render.text("IT: " + itemName, 120, 23);

        if(pl.actionDelay > 0)
            Render.renderRect(pl.position.x - pl.actionDelay/256 + 0.5f, pl.position.y - 0.5f,pl.actionDelay/4,4);

    }

    @Override
    public void setup() {
        super.setup();
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }
}

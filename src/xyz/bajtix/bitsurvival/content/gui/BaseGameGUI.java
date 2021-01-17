package xyz.bajtix.bitsurvival.content.gui;

import xyz.bajtix.bitsurvival.core.GUI;
import xyz.bajtix.bitsurvival.core.Player;
import xyz.bajtix.bitsurvival.core.Render;

import java.lang.ref.SoftReference;

public class BaseGameGUI extends GUI {

    public SoftReference<Player> player;

    public BaseGameGUI(SoftReference<Player> player) {
        this.player = player;
    }

    @Override
    public void update(char key) {
        Render.text("HP:" + player.get().health,40,40);
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

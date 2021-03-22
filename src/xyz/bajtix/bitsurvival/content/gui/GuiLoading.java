package xyz.bajtix.bitsurvival.content.gui;

import xyz.bajtix.bitsurvival.core.GUI;
import xyz.bajtix.bitsurvival.core.Render;

public class GuiLoading extends GUI {
    @Override
    public void setup() {
        super.setup();
    }

    @Override
    public void update() {
        Render.fill(0);
        Render.rect(0,0,480,480);
        Render.fill(255);
        Render.text("Loading",240 - 20,224,40,32);
    }
}

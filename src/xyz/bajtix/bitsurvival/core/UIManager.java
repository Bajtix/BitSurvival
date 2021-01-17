package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.content.GUIs;
import xyz.bajtix.bitsurvival.content.gui.BaseGameGUI;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

public class UIManager {

    private static GUI currentGUI;

    public static void initialize() {
        GUIs.baseGameGUI = new BaseGameGUI(new SoftReference(BitSurvival.bitSurvival.player));
    }

    public static void update(char key) {
        Render.fill(0);
        if(currentGUI != null)
            currentGUI.update(key);
        Render.fill(255);
    }

    public static void open(SoftReference<GUI> gui) {
        if(currentGUI != null)
            currentGUI.cleanup();
        currentGUI = gui.get();
        if(currentGUI != null)
            currentGUI.setup();
    }

    public static Reference<GUI> getOpen() {
        return new SoftReference<>(currentGUI);
    }
}

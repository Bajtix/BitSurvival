package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.content.GUIs;
import xyz.bajtix.bitsurvival.content.gui.BaseGameGUI;
import xyz.bajtix.bitsurvival.content.gui.LoadingGUI;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

public class UIManager {

    //TODO: If something does not work, I should check if the reference stuff is not messing up everything
    private static GUI currentGUI;

    public static void initialize() {
        GameLogger.debug("Initializing User Interfaces");
        GUIs.baseGameGUI = new BaseGameGUI(new SoftReference(BitSurvival.bitSurvival.player));
        GUIs.loadingGUI = new LoadingGUI();
    }

    public static void update() {
        Render.fill(0);

        if(currentGUI != null) {
            currentGUI.update();
        }
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

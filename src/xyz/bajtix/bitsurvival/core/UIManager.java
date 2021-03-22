package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.BitSurvival;
import xyz.bajtix.bitsurvival.content.GUIs;
import xyz.bajtix.bitsurvival.content.gui.GuiBaseGame;
import xyz.bajtix.bitsurvival.content.gui.GuiInventory;
import xyz.bajtix.bitsurvival.content.gui.GuiLoading;

public class UIManager {

    //TODO: If something does not work, I should check if the reference stuff is not messing up everything
    private static GUI currentGUI;

    public static void initialize() {
        GameLogger.debug("Initializing User Interfaces");
        GUIs.baseGameGUI = new GuiBaseGame(BitSurvival.bitSurvival.player);
        GUIs.inventoryGUI = new GuiInventory(BitSurvival.bitSurvival.player);
        GUIs.loadingGUI = new GuiLoading();
    }

    public static void update() {
        Render.fill(0);

        if(currentGUI != null) {
            currentGUI.update();
        }
        Render.fill(255);
    }

    public static void open(GUI gui) {
        if(currentGUI != null)
            currentGUI.cleanup();
        currentGUI = gui;
        if(currentGUI != null)
            currentGUI.setup();
    }

    public static GUI getOpen() {
        return currentGUI;
    }
}

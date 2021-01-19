package xyz.bajtix.bitsurvival.core;

import java.util.ArrayList;

public class Keys {
    private static ArrayList<Integer> pressedKeys = new ArrayList<>();
    private static ArrayList<Integer> keysDown = new ArrayList<>();
    private static ArrayList<Integer> keysUp = new ArrayList<>();

    public static void registerKey(int id){
        if(!keysDown.contains(id) && !pressedKeys.contains(id))
            keysDown.add(Integer.valueOf(id));
        if(!pressedKeys.contains(id))
            pressedKeys.add(Integer.valueOf(id));
    }

    public static void unregisterKey(int id) {
        if(!keysUp.contains(id))
            keysUp.add(Integer.valueOf(id));

        if(pressedKeys.contains(id))
            pressedKeys.remove(Integer.valueOf(id));
        else
            GameLogger.warning("KeyLogger tried to unregister a non-pressed key");
    }

    /**
     * Checks if key is down
     * @param id The id of a key, can be a char
     * @return Is the key pressed?
     */
    public static boolean isPressed(int id) {
        return pressedKeys.contains(Integer.valueOf(id));
    }

    /**
     * Checks if key was pressed in this frame
     * @param id The id of a key, can be a char
     * @return Was the key pressed?
     */
    public static boolean isDown(int id) {
        return keysDown.contains(id);
    }

    /**
     * Checks if key was released in this frame
     * @param id The id of a key, can be a char
     * @return Was the key released?
     */
    public static boolean isUp(int id) {
        return keysUp.contains(id);
    }

    /**
     * Clears the buffers
     */
    public static void frame() {
        keysDown.clear();
        keysUp.clear();
    }

    public static void showPressedKeys() {
        String k = "[";

        for (int i : pressedKeys) {
            k += Character.valueOf((char)i) + ";";
        }

        k+="]";

        GameLogger.debug("Pressed keys are " + k);
    }
}

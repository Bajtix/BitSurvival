package xyz.bajtix.bitsurvival.core;

import xyz.bajtix.bitsurvival.BitSurvival;

public class GameLogger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String time() {
        return  BitSurvival.hour() + ":" + BitSurvival.minute() + ":" + BitSurvival.second() + "~" + BitSurvival.bitSurvival.millis();
    }

    public static void debug(Object w) {
        System.out.println(ANSI_WHITE + "[" + time() + "] " + w + ANSI_RESET);
    }

    public static void warning(Object w) {
        System.out.println(ANSI_YELLOW + "[" + time() + "] " + w + ANSI_RESET);
    }

    public static void err(Object w) {
        System.out.println(ANSI_RED + "[" + time() + "] " + w + ANSI_RESET);
    }
}

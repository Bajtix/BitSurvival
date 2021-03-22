package xyz.bajtix.bitsurvival.core;

import sun.reflect.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class EventSystem {
    private static HashMap<String, ArrayList<Object>> listeners;

    public static void initialize() {
        listeners = new HashMap<>();
        GameLogger.debug("[ES] EventSystem initialized");
    }

    public static void addListener(String channel, Object listener) {
        if(listener == null) {
            GameLogger.warning("[ES] Event System attempted to register a null object");
            return;
        }

        if(!listeners.containsKey(channel))
            listeners.put(channel, new ArrayList<>());

        listeners.get(channel).add(listener);

        GameLogger.debug("[ES] Registering listener");
    }

    public static void removeListener(String channel, Object listener) {
        if(listener == null) {
            GameLogger.warning("[ES] Event System attempted to remove a null object");
            return;
        }

        if(listeners.containsKey(channel)) {
            if(listeners.get(channel).contains(listener)) {
                listeners.get(channel).remove(listener);
            }
            else {
                GameLogger.warning("[ES] Event System attempted to remove an unregistered object"); return;
            }
            GameLogger.debug("[ES] Removing listener");
        } else {
            GameLogger.warning("[ES] Event System attempted to remove an listener object from a non-existing channel");
        }
    }

    public static void eventAll(String event, Object... params) {
        for (String f : listeners.keySet()) {
            eventChannel(f,event,params);
        }
    }

    public static void eventChannel(String channel, String event, Object... params) {
        for(Object o : listeners.get(channel)) {
            if(o == null) continue;
            try {
                Method m = o.getClass().getMethod(event, objectClassArray(params));
                m.invoke(o, params);
            } catch (NoSuchMethodException e) {
                //GameLogger.warning(String.format("An event was called for an instance of %s but the handler does not exist", o.getClass().getCanonicalName())); //this is unnecessary, as a lot of events just should be ignored
            } catch (IllegalAccessException e) {
                GameLogger.err(String.format("EventSystem tried to call a method %s in %s which is not accessible. \n",event, o.getClass().getCanonicalName()) + Arrays.toString(e.getStackTrace()));
            } catch (InvocationTargetException e) {
                GameLogger.err(String.format("An EventSystem Event has thrown an error while executing %s in %s. \n", event, o.getClass().getCanonicalName()) + Arrays.toString(e.getStackTrace()));
            }
        }
    }

    public static Class<?>[] objectClassArray(Object... params) {
        ArrayList<Class<?>> cs = new ArrayList<>();
        for (Object o : params) {
            cs.add(o.getClass());
        }
        return cs.toArray(new Class[0]);
    }
}

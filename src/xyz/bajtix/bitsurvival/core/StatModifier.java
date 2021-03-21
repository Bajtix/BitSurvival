package xyz.bajtix.bitsurvival.core;

import java.lang.reflect.Field;

public class StatModifier {
    private Object o;

    public StatModifier(float value, StatMode mode) {
        this.value = value;
        this.mode = mode;
        this.stat = "base";
    }

    public StatModifier(float value, StatMode mode, String stat) {
        this.value = value;
        this.mode = mode;
        this.stat = stat;
    }

    public enum StatMode {
        ADD,
        MULTIPLY,
        MAX,
        MIN,
        AVERAGE
    }
    public float value;
    public StatMode mode;
    public String stat;


    public float apply(float value) {
        switch (mode) {
            case ADD:
                value += this.value; break;
            case MULTIPLY:
                value *= this.value; break;
            case AVERAGE:
                value = (this.value + value)/2; break;
            case MAX:
                value = Math.max(value,this.value); break;
            case MIN:
                value = Math.min(value,this.value); break;
        }
        return value;
    }

    public void applyToObject(Object o) {
        try {
            Field field = o.getClass().getField(stat);
            float ivalue = (float)field.get(o);
            ivalue = apply(ivalue);
            field.set(o,ivalue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

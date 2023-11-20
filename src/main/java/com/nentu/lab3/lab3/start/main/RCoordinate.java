package com.nentu.lab3.lab3.start.main;

import java.util.function.Consumer;

public class RCoordinate {
    private final Consumer<Float> beforeSet;
    private final float value;
    public boolean checked;

    public float getValue() {
        return value;
    }

    public RCoordinate(Consumer<Float> beforeSet, float value, boolean checked) {
        this.beforeSet = beforeSet;
        this.value = value;
        this.checked = checked;
    }
    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        if (this.checked!=checked){ // changed
            beforeSet.accept(checked ? value : null); //0 - deselected
        }
        else {
            beforeSet.accept(-1f);
        }
        this.checked = checked;
    }
}

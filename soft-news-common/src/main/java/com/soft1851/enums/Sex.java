package com.soft1851.enums;

/**
 * @author Administrator
 */

public enum Sex {
    //
    man(1),
    woman(0);
    public final Integer type;

    Sex(Integer type) {
        this.type = type;
    }
}

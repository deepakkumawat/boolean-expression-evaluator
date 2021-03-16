package com.bee.base;

import java.util.function.BiFunction;

public enum ConditionalOperation {

    AND((x, y) -> x && y),
    OR((x, y) -> x || y),
    NOT((x, y) -> !x);

    ConditionalOperation(BiFunction<Boolean, Boolean, Boolean> operation) {
        this.operation = operation;
    }

    private final BiFunction<Boolean, Boolean, Boolean> operation;

    public boolean apply(boolean x, boolean y) {
        return operation.apply(x, y);
    }

}

package com.bee.base;

import java.util.function.BiFunction;

public enum RelationalOperation {

    IS_GREATER((x, y) -> x > y),
    IS_LESSER((x, y) -> x < y);

    RelationalOperation(BiFunction<Integer, Integer, Boolean> operation) {
        this.operation = operation;
    }

    private final BiFunction<Integer, Integer, Boolean> operation;

    public boolean apply(int x, int y) {
        return operation.apply(x, y);
    }

}

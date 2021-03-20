package com.bee.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public enum RelationalOperator {

    IS_EQUAL("==", Integer::equals),
    IS_NOT_EQUAL("!=", (x, y) -> !x.equals(y)),
    IS_GREATER(">", (x, y) -> y > x),
    IS_GREATER_OR_EQUAL(">=", (x, y) -> y >= x),
    IS_LESSER("<", (x, y) -> y < x),
    IS_LESSER_OR_EQUAL("<=", (x, y) -> y <= x);

    // Reverse-lookup map
    private static final Map<String, RelationalOperator> lookup = new HashMap<>();
    static {
        for (RelationalOperator op : RelationalOperator.values()) {
            lookup.put(op.symbol, op);
        }
    }

    public static RelationalOperator of(String operatorStr) {
        operatorStr = operatorStr.replaceAll("\\s*", "");
        if (!lookup.containsKey(operatorStr)) {
            throw new IllegalArgumentException(operatorStr + " Invalid Operator");
        }
        return lookup.get(operatorStr);
    }

    public static boolean is(String operatorStr) {
        return lookup.containsKey(operatorStr.replaceAll("\\s*", ""));
    }

    private final BiPredicate<Integer, Integer> operation;
    private final String symbol;

    RelationalOperator(String symbol, BiPredicate<Integer, Integer> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public boolean apply(int x, int y) {
        return operation.test(x, y);
    }

}

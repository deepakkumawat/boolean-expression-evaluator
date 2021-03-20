package com.bee.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public enum LogicalOperator {

    AND("&&", (x, y) -> x && y),
    NOT("!", (x, y) -> !x),
    OR("||", (x, y) -> x || y);

    // Reverse-lookup map
    private static final Map<String, LogicalOperator> lookup = new HashMap<>();
    static {
        for (LogicalOperator op : LogicalOperator.values()) {
            lookup.put(op.symbol, op);
        }
    }

    public static LogicalOperator of(String operatorStr) {
        operatorStr = operatorStr.replaceAll("\\s*", "");
        if (!lookup.containsKey(operatorStr)) {
            throw new IllegalArgumentException(operatorStr + " Invalid Operator");
        }
        return lookup.get(operatorStr);
    }

    public static boolean is(String operatorStr) {
        return lookup.containsKey(operatorStr.replaceAll("\\s*", ""));
    }

    public String getSymbol() {
        return symbol;
    }

    private final BiPredicate<Boolean, Boolean> operation;
    private final String symbol;

    LogicalOperator(String symbol, BiPredicate<Boolean, Boolean> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public boolean apply(boolean x, boolean y) {
        return operation.test(x, y);
    }

}

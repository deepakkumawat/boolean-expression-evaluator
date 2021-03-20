package com.bee;

import com.bee.common.Pair;
import com.bee.operator.LogicalOperator;
import com.bee.operator.RelationalOperator;

import java.util.Map;
import java.util.Stack;

import static com.bee.common.Constants.*;
import static com.bee.common.Util.*;

public class ExpressionEvaluator {

    private static final Stack<Boolean> booleanValueStack = new Stack<>();
    private static final Stack<Integer> numberValueStack = new Stack<>();
    private static final Stack<String> relationalOperatorStack = new Stack<>();
    private static final Stack<String> logicalOperatorStack = new Stack<>();

    public static boolean evaluate(Map<String, Integer> variables, String expression) {

        int i = 0;
        char ch;

        stateCleanUp();

        while (i < expression.length()) {
            ch = expression.charAt(i);
            switch (whatIs(ch)) {
                case SPACE -> i++;
                case NUMBER -> i = processNumber(expression, i);
                case VARIABLE -> i = processVariable(variables, expression, i);
                case OPERATOR -> i = processOperator(expression, i);
                case OPENING_PARENTHESIS -> i = processOpeningParenthesis(ch, i);
                case CLOSING_PARENTHESIS -> i = processClosingParenthesis(i);
                default -> throw new RuntimeException("Unknown character found : " + ch);
            }
        }

        if (!relationalOperatorStack.isEmpty()) {
            booleanValueStack.push(RelationalOperator.of(relationalOperatorStack.pop()).apply(numberValueStack.pop(), numberValueStack.pop()));
        }
        if (!logicalOperatorStack.isEmpty()) {
            booleanValueStack.push(LogicalOperator.of(logicalOperatorStack.pop()).apply(booleanValueStack.pop(), booleanValueStack.pop()));
        }
        return booleanValueStack.pop();
    }

    private static void stateCleanUp() {
        booleanValueStack.clear();
        numberValueStack.clear();
        relationalOperatorStack.clear();
        logicalOperatorStack.clear();
    }
    private static int processNumber(String expression, int i) {
        Pair<Integer, Integer> numberDetails = getNumber(expression, i);
        numberValueStack.push(numberDetails.getValue());
        return numberDetails.getIndex();
    }
    private static int processVariable(Map<String, Integer> variables, String expression, int i) {
        Pair<String, Integer> variableDetails = getOperand(expression, i);
        numberValueStack.push(variables.get(variableDetails.getValue()));
        return variableDetails.getIndex();
    }
    private static int processOperator(String expression, int i) {
        Pair<String, Integer> response = getOperator(expression, i);
        if (RelationalOperator.is(response.getValue()))
            relationalOperatorStack.push(response.getValue());
        else if (LogicalOperator.is(response.getValue())) {
            if (!relationalOperatorStack.isEmpty())
                booleanValueStack.push(RelationalOperator.of(relationalOperatorStack.pop()).apply(numberValueStack.pop(), numberValueStack.pop()));
            if (!logicalOperatorStack.isEmpty() && LogicalOperator.is(logicalOperatorStack.peek()) && !response.getValue().equals("!"))
                booleanValueStack.push(LogicalOperator.of(logicalOperatorStack.pop()).apply(booleanValueStack.pop(), booleanValueStack.pop()));
            logicalOperatorStack.push(response.getValue());
        }
        return response.getIndex();
    }
    private static int processOpeningParenthesis(char ch, int i) {
        logicalOperatorStack.push(Character.toString(ch));
        return i+1;
    }
    private static int processClosingParenthesis(int i) {
        if (!relationalOperatorStack.isEmpty())
            booleanValueStack.push(RelationalOperator.of(relationalOperatorStack.pop()).apply(numberValueStack.pop(), numberValueStack.pop()));
        if (!logicalOperatorStack.isEmpty() && LogicalOperator.is(logicalOperatorStack.peek())) {
            booleanValueStack.push(LogicalOperator.of(logicalOperatorStack.pop()).apply(booleanValueStack.pop(), booleanValueStack.pop()));
        }
        logicalOperatorStack.pop();
        if (!logicalOperatorStack.isEmpty() && LogicalOperator.NOT.getSymbol().equals(logicalOperatorStack.peek())) {
            booleanValueStack.push(LogicalOperator.of(logicalOperatorStack.pop()).apply(booleanValueStack.pop(), true));
        }
        return i+1;
    }

}

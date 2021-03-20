package com.bee.common;

import com.bee.operator.LogicalOperator;

import static com.bee.common.Constants.*;

public class Util {

    public static String whatIs(char ch) {
        String itIs = UNKNOWN;
        if (ch == ' ')
            itIs = SPACE;
        else if (isNumeric(ch))
            itIs = NUMBER;
        else if (isOperator(ch))
            itIs = OPERATOR;
        else if (isVariable(ch))
            itIs = VARIABLE;
        else if (ch == '(')
            itIs = OPENING_PARENTHESIS;
        else if (ch == ')')
            itIs = CLOSING_PARENTHESIS;
        return itIs;
    }

    public static boolean isNumeric(char ch) {
        return '0' <= ch && ch <= '9';
    }
    public static boolean isOperator(char ch) {
        return (ch == '<' || ch == '>' || ch == '=' || ch == '|' || ch == '&' || ch == '!');
    }
    public static boolean isVariable(char ch) {
        return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }
    public static Pair<Integer, Integer> getNumber(String exp, int i) {
        int number = 0;
        while (i < exp.length() && isNumeric(exp.charAt(i))) {
            number = (number * 10) + (exp.charAt(i) - '0');
            i++;
        }
        return Pair.of(number, i);
    }
    public static Pair<String, Integer> getOperator(String exp, int i) {
        StringBuilder operator = new StringBuilder();
        char ch;
        for (; i < exp.length(); i++) {
            ch = exp.charAt(i);
            if ((LogicalOperator.NOT.getSymbol().equals(Character.toString(ch)) && operator.isEmpty())
                || (isOperator(ch) && !LogicalOperator.NOT.getSymbol().equals(Character.toString(ch)))
                || ch == ' ')
                operator.append(ch);
            else
                break;
        }
        return Pair.of(operator.toString().replace(" ", ""), i);
    }
    public static Pair<String, Integer> getOperand(String exp, int i) {
        StringBuilder variableName = new StringBuilder();
        char ch = exp.charAt(i);
        if (isVariable(ch)) {
            variableName.append(ch);
            for (i++; i < exp.length(); i++) {
                ch = exp.charAt(i);
                if (!isOperator(ch) && ch != ' ' && ch != '(' && ch != ')')
                    variableName.append(ch);
                else break;
            }
        }
        return Pair.of(variableName.toString(), i);
    }

}

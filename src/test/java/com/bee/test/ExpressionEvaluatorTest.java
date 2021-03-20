package com.bee.test;

import com.bee.ExpressionEvaluator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionEvaluatorTest {

    private static final int a = 1;
    private static final int b = 6;
    private static final int c = 10;
    private static final int largeVariableName = 25;
    private static Map<String, Integer> variableMap;

    @BeforeAll
    public static void init() {
        variableMap = new HashMap<>();
        variableMap.put("a", a);
        variableMap.put("b", b);
        variableMap.put("c", c);
        variableMap.put("largeVariableName", largeVariableName);
    }

    @Test
    public void relationalExpressionTest_only_number() {
        assertEquals(15>14, ExpressionEvaluator.evaluate(variableMap, "15>14"));
        assertEquals(15<14, ExpressionEvaluator.evaluate(variableMap, "15<14"));
        assertEquals(15>14, ExpressionEvaluator.evaluate(variableMap, " 15 > 14 "));
        assertEquals(15<14, ExpressionEvaluator.evaluate(variableMap, " 15 < 14 "));
        assertEquals(15==14, ExpressionEvaluator.evaluate(variableMap, " 15 == 14 "));
        assertEquals(15!=14, ExpressionEvaluator.evaluate(variableMap, " 15 != 14 "));
    }

    @Test
    public void logicalExpressionTest_only_number() {
        assertEquals((15 < 14) && (15 > 14), ExpressionEvaluator.evaluate(variableMap, "(15 < 14) && (15 > 14)"));
        assertEquals((15 > 14) && (15 < 14), ExpressionEvaluator.evaluate(variableMap, "(15>14)&&(15<14)"));
        assertEquals((15 > 14) && !(15 < 14), ExpressionEvaluator.evaluate(variableMap, "(15>14)&&!(15<14)"));
        assertEquals((15 < 14) || (15 > 14), ExpressionEvaluator.evaluate(variableMap, "(15<14)||(15>14)"));
    }

    @Test
    public void relationalExpressionTest_only_variable() {
        assertEquals(a>b, ExpressionEvaluator.evaluate(variableMap, "a>b"));
        assertEquals(a<b, ExpressionEvaluator.evaluate(variableMap, "a<b"));
        assertEquals(a>b, ExpressionEvaluator.evaluate(variableMap, " a > b "));
        assertEquals(a<b, ExpressionEvaluator.evaluate(variableMap, " a < b "));
        assertEquals(a==b, ExpressionEvaluator.evaluate(variableMap, " a == b "));
        assertEquals(a!=b, ExpressionEvaluator.evaluate(variableMap, " a != b "));
    }

    @Test
    public void logicalExpressionTest_only_variable() {
        assertEquals((a < b) && (a > b), ExpressionEvaluator.evaluate(variableMap, "(a < b) && (a > b)"));
        assertEquals((a < b) && !(a != b), ExpressionEvaluator.evaluate(variableMap, "(a<b)&&!(a!=b)"));
        assertEquals((a > b) || (a < b), ExpressionEvaluator.evaluate(variableMap, "(a>b)||(a<b)"));
    }

    @Test
    public void logicalExpressionTest_complex() {
        assertEquals(a > 14, ExpressionEvaluator.evaluate(variableMap, "a > 14"));
        assertEquals((a > 14), ExpressionEvaluator.evaluate(variableMap, "(a > 14)"));
        assertEquals(!(a > 14), ExpressionEvaluator.evaluate(variableMap, "!(a > 14)"));
        assertEquals(((a > 14)), ExpressionEvaluator.evaluate(variableMap, "((a > 14))"));
        assertEquals(largeVariableName == 14, ExpressionEvaluator.evaluate(variableMap, "largeVariableName == 14"));
        assertEquals(a > 14, ExpressionEvaluator.evaluate(variableMap, "a>14"));
        assertEquals((14> a), ExpressionEvaluator.evaluate(variableMap, "14 > a"));
        assertEquals((a > 4 || b < 3), ExpressionEvaluator.evaluate(variableMap, "a > 4 || b < 3"));
        assertEquals((a > 4 && b < 3 && 1 < 3), ExpressionEvaluator.evaluate(variableMap, "a > 4 && b < 3  && 1 < 3"));
        assertEquals((a < 4) && (b > 3), ExpressionEvaluator.evaluate(variableMap, "(a < 4) && (b > 3)"));
        assertEquals(((a < 4) && (b > 3)), ExpressionEvaluator.evaluate(variableMap, "((a < 4) && (b > 3))"));
        assertEquals(((a > 4) && !(b < 6)), ExpressionEvaluator.evaluate(variableMap, "((a > 4) && !(b < 6))"));
        assertEquals(((a < 4) && (b > 3)) || ((a > 4) && (b < 6)), ExpressionEvaluator.evaluate(variableMap, "((a < 4) && (b > 3)) || ((a > 4) && (b < 6))"));
        assertEquals((a < 4 && b > 3 && c < 9) || (a > 4 && (b < 6) || c > 10), ExpressionEvaluator.evaluate(variableMap, "(a < 4 && b > 3 && c < 9) || (a > 4 && (b < 6) || c > 10)"));
        assertEquals((a < 4 && b > 3 && c < 9) || (a > 4 && !(b < 6) || c > 10), ExpressionEvaluator.evaluate(variableMap, "(a < 4 && b > 3 && c < 9) || (a > 4 && !(b < 6) || c > 10)"));
    }
}

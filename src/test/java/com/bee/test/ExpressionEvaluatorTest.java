package com.bee.test;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class ExpressionEvaluatorTest {



    @Test
    public void relationalExpressionTest() {
        String expression = "a > b";
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 4);
        map.put("b", 5);
        Assert.assert(4>5, expression);
    }
}

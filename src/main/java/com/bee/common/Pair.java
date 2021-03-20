package com.bee.common;

public class Pair<V, I> {

    private final V value;
    private final I index;

    private Pair(V value, I index) {
        this.value = value;
        this.index = index;
    }

    public static <V, I> Pair<V, I> of(V value, I index) {
        return new Pair<>(value, index);
    }

    public V getValue() {
        return value;
    }

    public I getIndex() {
        return index;
    }
}

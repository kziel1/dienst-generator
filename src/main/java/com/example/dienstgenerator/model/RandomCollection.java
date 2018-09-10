package com.example.dienstgenerator.model;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<E> {
    private final NavigableMap<Integer, E> map = new TreeMap<>();
    private final Random random;
    private int total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection<E> add(int weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    public E next() {
        int value = random.nextInt() * total;
        //TODO fix map.higherEntry(value)==null
        return map.higherEntry(value).getValue();
    }
}
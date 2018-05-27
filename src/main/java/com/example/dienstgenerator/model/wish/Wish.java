package com.example.dienstgenerator.model.wish;

import com.example.dienstgenerator.model.Shift;

import java.util.List;
import java.util.Map;

public abstract class Wish {
    String employee;

    public abstract void applyWish(List<Map<String, Integer>> shifts);
}

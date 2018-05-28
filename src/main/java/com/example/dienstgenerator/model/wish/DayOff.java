package com.example.dienstgenerator.model.wish;

import com.example.dienstgenerator.model.Shift;

import java.util.List;
import java.util.Map;

public class DayOff extends Wish {
    private int day;

    public DayOff(String employee, int day) {
        this.employee = employee;
        this.day = day;
    }

    @Override
    public void applyWish(List<Map<String, Integer>> shifts) {
        shifts.get(day).put(employee, 0);
    }
}

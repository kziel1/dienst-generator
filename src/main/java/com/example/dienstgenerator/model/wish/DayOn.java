package com.example.dienstgenerator.model.wish;

import java.util.List;
import java.util.Map;

public class DayOn extends Wish {
    private int day;

    public DayOn(String employee, int day) {
        this.employee = employee;
        this.day = day;
    }

    @Override
    public void applyWish(List<Map<String, Integer>> shifts) {

        shifts.get(day).put(employee, 100);
        if (day > 0) {
            shifts.get(day - 1).put(employee, 0);
        }
        if (day > 1) {
            if (shifts.get(day - 2).get(employee) > 0) {
                shifts.get(day - 2).put(employee, 10);
            }
        }
        if (day < shifts.size() - 1) {
            shifts.get(day + 1).put(employee, 0);
        }
        if (day < shifts.size() - 2) {
            if (shifts.get(day + 2).get(employee) > 0) {
                shifts.get(day + 2).put(employee, 10);
            }
        }
    }
}

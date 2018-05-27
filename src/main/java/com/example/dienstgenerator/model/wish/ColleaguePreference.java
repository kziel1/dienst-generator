package com.example.dienstgenerator.model.wish;

import java.util.List;
import java.util.Map;

public class ColleaguePreference extends Wish {
    String colleague;

    public ColleaguePreference(String employee, String colleague) {
        this.employee = employee;
        this.colleague = colleague;
    }

    @Override
    public void applyWish(List<Map<String, Integer>> shifts) {

    }
}

package com.example.dienstgenerator.model.wish;

import java.util.List;
import java.util.Map;

import static java.lang.Math.min;

public class ColleaguePreference extends Wish {
    String colleague;

    public ColleaguePreference(String employee, String colleague) {
        this.employee = employee;
        this.colleague = colleague;
    }

    @Override
    public void applyWish(List<Map<String, Integer>> shifts) {
        for (Map<String, Integer> shift : shifts) {
            Integer employeeWeight = shift.get(employee);
            Integer colleagueWeight = shift.get(colleague);
            if (employeeWeight == 100 || employeeWeight == 0) {
                continue;
            }
            shift.put(employee, min((employeeWeight + colleagueWeight) / 2 + 20, 100));
        }
    }
}

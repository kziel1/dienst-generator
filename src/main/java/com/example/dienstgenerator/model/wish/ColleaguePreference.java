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
        for (Map<String, Integer> shift : shifts) {
            Integer employeeWeight = shift.get(employee);
            Integer colleagueWeight = shift.get(colleague);
            if (employeeWeight == 100 || employeeWeight == 0) {
                continue;
            } else if (employeeWeight == 50 && colleagueWeight == 50) {
                shift.put(employee, 60);

            } else {
                shift.put(employee, (employeeWeight + colleagueWeight) / 2);
            }
        }
    }
}

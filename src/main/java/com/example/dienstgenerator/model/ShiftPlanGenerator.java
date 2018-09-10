package com.example.dienstgenerator.model;

import com.example.dienstgenerator.model.wish.ColleaguePreference;
import com.example.dienstgenerator.model.wish.DayOff;
import com.example.dienstgenerator.model.wish.DayOn;
import com.example.dienstgenerator.model.wish.Wish;
import lombok.Builder;

import java.time.YearMonth;
import java.util.*;

@Builder
public class ShiftPlanGenerator {
    private int seedCount;
    private int shiftLoad;
    private int year;
    private int month;
    private List<String> employees;
    private List<Wish> wishes;
    private List<List<String>> generatedShiftPlan;
    private List<List<List<String>>> seededShifts;

    public List<List<String>> generateShiftPlan() {
        initiateShifts();
        seedShiftPlans();
        List<List<List<String>>> seededShifts = new ArrayList<>();
        return generatedShiftPlan;
    }

    private void seedShiftPlans() {
        applyDayOffWishes();
        applyDayOnWishes();
        applyWeekendShifts();
    }











    private void initiateShifts() {
        int dayCount = YearMonth.of(year, month + 1).lengthOfMonth();
        shifts = new ArrayList<>(dayCount);
        for (int i = 0; i < dayCount; i++) {
            Map<String, Integer> shift = new HashMap<>();
            for (String employee : employees) {
                shift.put(employee, 50);
            }
            shifts.add(shift);
        }
    }

    public void printShifts() {
        System.out.println("\nShifts:");
        for (int i = 0; i < shifts.size(); i++) {
            System.out.print("day " + i);
            System.out.println(shifts.get(i).toString());
        }
    }

    public void printShiftsStatistics() {
        Map<String, Integer> weekendShifts = new HashMap<>();
        Map<String, Integer> normalShifts = new HashMap<>();
        for (String employee : employees) {
            weekendShifts.put(employee, 0);
            normalShifts.put(employee, 0);
        }
        for (int i = 0; i < shifts.size(); i++) {
            Map<String, Integer> shift = shifts.get(i);
            for (String employee : employees) {
                if (shift.get(employee) > 0) {
                    if (isWeekendShift(year, month, i)) {
                        weekendShifts.put(employee, weekendShifts.get(employee) + 1);
                    } else {
                        normalShifts.put(employee, normalShifts.get(employee) + 1);

                    }
                }
            }
        }
        System.out.println("\nStatistics (normal / weekend):");
        for (String employee : employees) {
            System.out.println(employee + " " + weekendShifts.get(employee) + "/" + normalShifts.get(employee));
        }
    }
}

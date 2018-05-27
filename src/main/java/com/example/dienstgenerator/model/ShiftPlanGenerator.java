package com.example.dienstgenerator.model;

import com.example.dienstgenerator.model.wish.ColleaguePreference;
import com.example.dienstgenerator.model.wish.DayOff;
import com.example.dienstgenerator.model.wish.DayOn;
import com.example.dienstgenerator.model.wish.Wish;
import lombok.Builder;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
public class ShiftPlanGenerator {
    private int shiftLoad;
    private YearMonth yearMonth;
    private List<String> employees;
    private List<Wish> wishes;
    private List<Map<String, Integer>> shifts;

    public List<Map<String, Integer>> generateShiftPlan() {
        initiateShifts();
        applyWishes();
        normalizeNormalShifts();
        normalizeWeekendShifts();
        return shifts;
    }

    private void initiateShifts() {
        shifts = new ArrayList<>(yearMonth.lengthOfMonth());
        for (int i = 0; i < yearMonth.lengthOfMonth(); i++) {
            Map<String, Integer> shift = new HashMap<>();
            for (String employee : employees) {
                shift.put(employee, 50);
            }
            shifts.add(shift);
        }
    }

    private void applyWishes() {
        applyDayOffWishes();
        applyDayOnWishes();
        applyColleaguePreference();
    }

    private void applyDayOffWishes() {
        wishes.stream()
                .filter(wish -> wish instanceof DayOff)
                .forEach(wish -> wish.applyWish(shifts));
    }

    private void applyDayOnWishes() {
        wishes.stream()
                .filter(wish -> wish instanceof DayOn)
                .forEach(wish -> wish.applyWish(shifts));
    }

    private void applyColleaguePreference() {
        wishes.stream()
                .filter(wish -> wish instanceof ColleaguePreference)
                .forEach(wish -> wish.applyWish(shifts));
    }

    private void normalizeNormalShifts() {

    }

    private void normalizeWeekendShifts() {

    }
}

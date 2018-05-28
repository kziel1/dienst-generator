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
    private int shiftLoad;
    private int year;
    private int month;
    private List<String> employees;
    private List<Wish> wishes;
    private List<Map<String, Integer>> shifts;

    public List<Map<String, Integer>> generateShiftPlan() {
        initiateShifts();
        applyWishes();
        seedWeekendShifts();
        seedNormalShifts();
        return shifts;
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

    private void seedWeekendShifts() {
        int weekendShiftCount = getWeekendShiftCount();
    }

    private int getWeekendShiftCount() {
        int weekendShiftCount = 0;
        Calendar calendar = Calendar.getInstance();
        for (int i = 1; i < shifts.size() + 1; i++) {
            calendar.set(year, month, i);
            if (Arrays.asList(Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY)
                    .contains(calendar.get(Calendar.DAY_OF_WEEK))) {
                weekendShiftCount++;
            }
        }
        return weekendShiftCount;
    }

    private void seedNormalShifts() {
        int normalShiftCount = getNormalShiftCount();
    }

    private int getNormalShiftCount() {
        int normalShiftCount = 0;
        Calendar calendar = Calendar.getInstance();
        for (int i = 1; i < shifts.size() + 1; i++) {
            calendar.set(year, month, i);
            if (Arrays.asList(Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY)
                    .contains(calendar.get(Calendar.DAY_OF_WEEK))) {
                normalShiftCount++;
            }
        }
        return normalShiftCount;
    }
}

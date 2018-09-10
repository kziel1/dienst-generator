package com.example.dienstgenerator.model;

import com.example.dienstgenerator.model.wish.ColleaguePreference;
import com.example.dienstgenerator.model.wish.DayOff;
import com.example.dienstgenerator.model.wish.DayOn;
import com.example.dienstgenerator.model.wish.Wish;
import lombok.Builder;

import java.time.YearMonth;
import java.util.*;

@Builder
public class ShiftPlanGeneratorBackup {
    private int seedCount;
    private int shiftLoad;
    private int year;
    private int month;
    private List<String> employees;
    private List<Wish> wishes;
    private List<Map<String, Integer>> shifts;
    private List<List<String>> generatedShifts;
    private List<List<List<String>>> seededShifts;

    public List<Map<String, Integer>> generateShiftPlan() {
        initiateShifts();



        applyWishes();
//        seedShifts();
        List<List<List<String>>> seededShifts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            seededShifts.add(seedShift());
        }
//        seedWeekendShifts();lavisch
//        seedNormalShifts();
        return shifts;
    }

    private void seedShiftPlans() {

    }


    private List<String> seedShift(int day) {

        Map<String, Integer> shift = shifts.get(day);
        RandomCollection rc = new RandomCollection<String>();
        for (String employee : shift.keySet()) {
            rc.add(shift.get(employee), employee);
        }
        List<String> seededShift = new ArrayList<>();

//        while(shiftLoad != seededShift.size()){
//            String seededEmployee = rc.next();
//        }

        return null;
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
//        applyColleaguePreference();
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


    private List<List<String>> seedShift(){
        List<List<String>> seededShift = new ArrayList<>();
        return seededShift;
    }


    private void seedWeekendShifts() {
        int weekendShiftCount = getWeekendShiftCount();
        List<Integer> weekendDays = getWeekendDays();
        for (Integer weekendDay : weekendDays) {

        }
    }


    private int getWeekendShiftCount() {
        int weekendShiftCount = 0;
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < shifts.size(); i++) {
            calendar.set(year, month, i + 1);
            if (isWeekendShift(year, month, i + 1)) {
                weekendShiftCount++;
            }
        }
        return weekendShiftCount;
    }

    private List<Integer> getWeekendDays() {
        List<Integer> weekendDays = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < shifts.size(); i++) {
            calendar.set(year, month, i + 1);
            if (isWeekendShift(year, month, i + 1)) {
                weekendDays.add(i);
            }
        }
        return weekendDays;
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

    private boolean isWeekendShift(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return Arrays.asList(Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY)
                .contains(calendar.get(Calendar.DAY_OF_WEEK));
    }

//    private boolean isValid(String employee, int day){
//        return shifts.get(day).get(employee) > 0 &&
//                ;
//    }

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

package com.example.dienstgenerator;

import com.example.dienstgenerator.model.Shift;
import com.example.dienstgenerator.model.ShiftPlanGenerator;
import com.example.dienstgenerator.model.wish.DayOff;
import com.example.dienstgenerator.model.wish.DayOn;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DienstGeneratorApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void blabla() {

        //    public ShiftPlanGenerator(int year, int month, int shiftLoad, List<String> employees) {
//        this.shiftLoad = shiftLoad;
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month, 1);
//        this.yearMonth = YearMonth.of(year, month);
//        this.employees = employees;
//        initiateShifts();
//    }

        List<Map<String, Integer>> shifts = ShiftPlanGenerator.builder()
                .shiftLoad(2)
                .yearMonth(YearMonth.of(2018, Calendar.JUNE))
                .employees(Arrays.asList("Tiner", "Schildkröte", "Bitch"))
                .wishes(Arrays.asList(new DayOff("Tiner", 0), new DayOn("Tiner", 1)))
                .build()
                .generateShiftPlan();
        shifts.isEmpty();
    }
}

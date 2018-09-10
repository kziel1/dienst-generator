package com.example.dienstgenerator;

import com.example.dienstgenerator.model.ShiftPlanGenerator;
import com.example.dienstgenerator.model.wish.ColleaguePreference;
import com.example.dienstgenerator.model.wish.DayOff;
import com.example.dienstgenerator.model.wish.DayOn;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        ShiftPlanGenerator shiftPlanGenerator = ShiftPlanGenerator.builder()
                .shiftLoad(2)
                .year(2018)
                .month(Calendar.JUNE)
                .employees(Arrays.asList("Tiner", "Schildkröte", "Bitch", "Bergdoktor", "Mörder"))
                .wishes(Arrays.asList(
                        new DayOff("Tiner", 0),
                        new DayOn("Tiner", 2),
                        new DayOn("Bitch", 4),
                        new DayOff("Bitch", 6),
                        new ColleaguePreference("Tiner", "Bitch"),
                        new ColleaguePreference("Bitch", "Tiner")))
                .build();
        List<List<String>> shiftPlan = shiftPlanGenerator.generateShiftPlan();
        shiftPlanGenerator.printShifts();
        shiftPlanGenerator.printShiftsStatistics();
    }
}
package com.example.dienstgenerator.model;

import lombok.Data;

import java.time.YearMonth;
import java.util.List;

@Data
public class ShiftPlan {
    private YearMonth yearMonth;
    private List<List<Shift>> shifts;
}

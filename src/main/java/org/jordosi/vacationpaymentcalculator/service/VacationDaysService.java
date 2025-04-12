package org.jordosi.vacationpaymentcalculator.service;

import java.time.LocalDate;

public interface VacationDaysService {
    Integer getPaidVacationDays(LocalDate startDate, Integer vacationDays);
}

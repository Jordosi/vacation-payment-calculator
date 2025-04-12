package org.jordosi.vacationpaymentcalculator.service.impl;

import org.jordosi.vacationpaymentcalculator.common.VacationDaysHelper;
import org.jordosi.vacationpaymentcalculator.service.VacationDaysService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;


/**
 * Service implementation for calculating paid vacation days.
 * Excludes weekends and public holidays from the total count.
 */
@Service
public class VacationDaysServiceImpl implements VacationDaysService {

    /**
     * Calculates the number of paid vacation days (excluding holidays/weekends).
     *
     * @param startDate start date of the vacation period
     * @param vacationDays total requested vacation days
     * @return effective number of paid days
     * @see VacationDaysHelper#getHolidays()
     */
    @Override
    public Integer getPaidVacationDays(LocalDate startDate, Integer vacationDays) {
        List<LocalDate> holidaysList = VacationDaysHelper.getHolidays();
        Predicate<LocalDate> holidays = holidaysList::contains;

        List<LocalDate> paidVacationDays = Stream
                .iterate(startDate, date -> date.plusDays(1))
                .limit(vacationDays)
                .filter(date -> !isHolidayOrWeekend(date, holidays))
                .toList();

        return paidVacationDays.size();
    }

    /**
     * Checks if a date is a holiday or weekend.
     *
     * @param date the date to check
     * @param holidays predicate for holiday dates
     * @return true if the date is non-working
     */
    private boolean isHolidayOrWeekend(LocalDate date, Predicate<LocalDate> holidays) {
        return holidays.test(date)
                || date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}

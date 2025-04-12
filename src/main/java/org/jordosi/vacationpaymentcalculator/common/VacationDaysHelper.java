package org.jordosi.vacationpaymentcalculator.common;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Vacation days helper
 * <p>Class which helps to adjust holidays if vacation start date is provided</p>
 * <p>Contains info about following holidays:</p>
 * <ul>
 *     <li>New Year Holidays (01.01 - 08.01)</li>
 *     <li>Fatherland Defender Day (23.02)</li>
 *     <li>International Women's Day (08.03)</li>
 *     <li>Spring and Labor Aay (01.05)</li>
 *     <li>Victory Day (09.05)</li>
 *     <li>Russia Day (12.06)</li>
 *     <li>National Unity Day (04.11)</li>
 * </ul>
 *
 * @author Jordosi
 * @version 0.0.1
 */
public class VacationDaysHelper {
    private static final Integer CURRENT_YEAR = LocalDate.now().getYear();

    public static List<LocalDate> getHolidays() {
        return List.of(
                LocalDate.of(CURRENT_YEAR, Month.JANUARY, 1),
                LocalDate.of(CURRENT_YEAR, Month.JANUARY, 2),
                LocalDate.of(CURRENT_YEAR, Month.JANUARY, 3),
                LocalDate.of(CURRENT_YEAR, Month.JANUARY, 4),
                LocalDate.of(CURRENT_YEAR, Month.JANUARY, 5),
                LocalDate.of(CURRENT_YEAR, Month.JANUARY, 6),
                LocalDate.of(CURRENT_YEAR, Month.JANUARY, 7),
                LocalDate.of(CURRENT_YEAR, Month.JANUARY, 8),
                LocalDate.of(CURRENT_YEAR, Month.FEBRUARY, 23),
                LocalDate.of(CURRENT_YEAR, Month.MARCH, 8),
                LocalDate.of(CURRENT_YEAR, Month.MAY, 1),
                LocalDate.of(CURRENT_YEAR, Month.MAY, 9),
                LocalDate.of(CURRENT_YEAR, Month.JUNE, 12),
                LocalDate.of(CURRENT_YEAR, Month.NOVEMBER, 4)
        );
    }
}

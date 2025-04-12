package org.jordosi.vacationpaymentcalculator.common;

/**
 * Vacation payments helper
 * <p>Contains information about average working days in month and income tax rate</p>
 * @author Jordosi
 * @version 0.0.1
 */

import java.math.BigDecimal;
public class VacationPaymentsHelper {
    public static final BigDecimal AVERAGE_DAYS_PER_MONTH = BigDecimal.valueOf(29.3);

    public static final BigDecimal INCOME_TAX_RATE = BigDecimal.valueOf(0.13);
}

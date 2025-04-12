package org.jordosi.vacationpaymentcalculator.service;

import org.jordosi.vacationpaymentcalculator.model.VacationPaymentResponse;

import java.math.BigDecimal;

public interface VacationPaymentService {
    VacationPaymentResponse calculateVacationPayment(BigDecimal averageSalaryPerYear, Integer vacationDays);
}

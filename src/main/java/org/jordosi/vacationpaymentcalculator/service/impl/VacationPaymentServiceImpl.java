package org.jordosi.vacationpaymentcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jordosi.vacationpaymentcalculator.common.VacationPaymentsHelper;
import org.jordosi.vacationpaymentcalculator.model.VacationPaymentResponse;
import org.jordosi.vacationpaymentcalculator.service.VacationPaymentService;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

import java.math.BigDecimal;

/**
 * Service implementation for calculating vacation payments.
 * Handles salary-to-daily-rate conversion, tax deductions, and final amount calculation.
 */
@Slf4j
@Service
public class VacationPaymentServiceImpl implements VacationPaymentService {

    /**
     * Calculates the final vacation payment after tax deductions.
     *
     * @param averageSalaryPerYear average annual salary (must be positive)
     * @param vacationDays number of vacation days (must be >= 1)
     * @return VacationPaymentResponse with the final amount
     * @throws IllegalArgumentException if salary or days are invalid
     */
    @Override
    public VacationPaymentResponse calculateVacationPayment(BigDecimal averageSalaryPerYear, Integer vacationDays) {
        BigDecimal averageDailySalary =calculateAverageDailySalary(averageSalaryPerYear);
        log.info("Average salary per day = {} RUB", averageDailySalary);

        BigDecimal vacationPaymentWithoutTax = calculateVacationPaymentWithoutTax(averageDailySalary, vacationDays);
        log.info("Vacation payment without tax deduction = {} RUB", vacationPaymentWithoutTax);

        BigDecimal incomeTaxAmount = calculateTax(vacationPaymentWithoutTax);
        log.info("Income tax amount = {} RUB", incomeTaxAmount);

        BigDecimal finalVacationPayment = vacationPaymentWithoutTax.subtract(incomeTaxAmount);
        log.info("Final vacation payment = {} RUB", finalVacationPayment);

        return VacationPaymentResponse
                .builder()
                .vacationPaymentAmount(finalVacationPayment)
                .build();
    }

    /**
     * Calculates average daily salary from annual salary.
     *
     * @param averageSalaryPerYear annual salary
     * @return daily salary rounded to 2 decimal places
     */
    private BigDecimal calculateAverageDailySalary(BigDecimal averageSalaryPerYear) {
        return averageSalaryPerYear.divide(VacationPaymentsHelper.AVERAGE_DAYS_PER_MONTH.multiply(BigDecimal.valueOf(12)), 2, RoundingMode.HALF_EVEN);
    }

    /**
     * Calculates vacation payment before tax.
     *
     * @param averageDailySalary precomputed daily salary
     * @param vacationDays number of days
     * @return gross payment amount
     */
    private BigDecimal calculateVacationPaymentWithoutTax(BigDecimal averageDailySalary, Integer vacationDays) {
        return averageDailySalary.multiply(BigDecimal.valueOf(vacationDays));
    }

    /**
     * Calculates income tax (13%) from the gross payment amount.
     *
     * @param vacationPaymentWithoutTax gross payment amount
     * @return tax amount rounded to whole rubles
     */
    private BigDecimal calculateTax(BigDecimal vacationPaymentWithoutTax) {
        return vacationPaymentWithoutTax.multiply(VacationPaymentsHelper.INCOME_TAX_RATE).setScale(0, RoundingMode.HALF_UP);
    }
}

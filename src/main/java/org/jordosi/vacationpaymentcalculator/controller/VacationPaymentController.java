package org.jordosi.vacationpaymentcalculator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jordosi.vacationpaymentcalculator.model.VacationPaymentRequest;
import org.jordosi.vacationpaymentcalculator.model.VacationPaymentResponse;
import org.jordosi.vacationpaymentcalculator.service.VacationDaysService;
import org.jordosi.vacationpaymentcalculator.service.VacationPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Vacation payment REST controller
 * <p>Exposes a single endpoint: GET / calculate</p>
 * @author Jordosi
 * @version 0.0.1
 * @see VacationDaysService
 * @see VacationPaymentService
 */
@RequiredArgsConstructor
@RestController
public class VacationPaymentController {
    private final VacationPaymentService vacationPaymentsService;
    private final VacationDaysService vacationDaysService;

    /**
     * Method of vacation payment calculation
     * @param paymentRequest request DTO with proper and valid data
     * @return Vacation payment response with following codes*<ul>
     *     <li>200 - successful calculation (contains payment amount)</li>
     *     <li>400 - incorrect request data</li>
     * </ul>
     * @see VacationPaymentRequest
     * @see VacationPaymentResponse
     */
    @GetMapping("/calculate")
    public ResponseEntity<VacationPaymentResponse> calculateVacationPayment(@Valid VacationPaymentRequest paymentRequest) {
        var vacationDays = calculateVacationDays(paymentRequest.getVacationDays(), paymentRequest.getStartVacationDate());
        var vacationPayment = vacationPaymentsService.calculateVacationPayment(paymentRequest.getAverageSalaryPerYear(), vacationDays);
        return ResponseEntity.ok(vacationPayment);
    }

    /**
     * Calculates payable vacation days in case start date is provided
     * @param vacationDays initial amount of vacation days
     * @param startVacationDate date when vacation starts
     * @return final vacation days amount (with holidays and weekends excluded)
     */
    private Integer calculateVacationDays(Integer vacationDays, LocalDate startVacationDate) {
        if (startVacationDate != null) {
            return vacationDaysService.getPaidVacationDays(startVacationDate, vacationDays);
        }
        return vacationDays;
    }
}

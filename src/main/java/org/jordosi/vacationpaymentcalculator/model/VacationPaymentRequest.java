package org.jordosi.vacationpaymentcalculator.model;

import lombok.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Vacation payment request DTO
 * <p>Request includes following data:</p>
 * <ul>
 *     <li>averageSalaryPerYear - average annual salary (required parameter)</li>
 *     <li>vacationDays - vacation length in days (required parameter)</li>
 *     <li>startVacationDate - date when vacation starts (optional:
 *     if is given, then days amount is being adjusted (holidays and weekends are being excluded))</li>
 * </ul>
 * @author Jordosi
 * @version 0.0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class VacationPaymentRequest {
    @NotNull(message="Annual salary can't be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Annual salary must be greater than zero")
    private BigDecimal averageSalaryPerYear;

    @NotNull(message = "Vacation days can't be null")
    @Min(value = 1, message = "Vacation must last at least one day")
    private Integer vacationDays;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate startVacationDate;
}

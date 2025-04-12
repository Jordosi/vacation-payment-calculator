package org.jordosi.vacationpaymentcalculator.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * Vacation payment response DTO
 * <p>Contains following data:</p>
 * <ul>
 *     <li>vacationPaymentAmount - amount of payment for vacation excluding income tax (13%)</li>
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
public class VacationPaymentResponse {
    private BigDecimal vacationPaymentAmount;
}

package org.jordosi.vacationpaymentcalculator.model;

import lombok.*;

import java.util.Map;

/**
 Standardized error response for API exceptions.
 * Contains HTTP status code, error message, and optional detailed errors.
 *
 * <p>Example:
 * <pre>
 * {
 *   "errorCode": 400,
 *   "errorMessage": "Validation failed",
 *   "errors": {
 *     "averageSalaryPerYear": "Average salary must be greater than 0"
 *   }
 * }
 * </pre>
 * @author Jordosi
 * @version 0.0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class ErrorResponse {
    private Integer errorCode;
    private String errorMessage;
    private Map<String, Object> errors;
}

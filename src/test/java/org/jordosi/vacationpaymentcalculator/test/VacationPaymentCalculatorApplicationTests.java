package org.jordosi.vacationpaymentcalculator.test;

import org.jordosi.vacationpaymentcalculator.ResolversCommonConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
class VacationPaymentCalculatorApplicationTests extends ResolversCommonConfiguration {
    @Test
    @DisplayName("Test returns Bad Request when test salary is non-positive")
    void testSalaryIsNonPositive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                get(VACATION_PAY_API)
                .param("averageSalaryPerYear", "0")
                .param("vacationDays", String.valueOf(TEST_VACATION_DAYS))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.averageSalaryPerYear").value("Annual salary must be greater than zero"));
    }

    @Test
    @DisplayName("Test returns Bad Request when the vacation days number is less than 1")
    void testVacationDaysLessThanOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(VACATION_PAY_API)
                .param("averageSalaryPerYear", String.valueOf(TEST_AVERAGE_SALARY))
                .param("vacationDays", String.valueOf(0))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.vacationDays").value("Vacation must last at least one day"));
    }

    @Test
    @DisplayName("Test returns multiple validation errors for invalid salary and vacation days")
    void testInvalidSalaryAndVacationDays() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(VACATION_PAY_API)
                .param("averageSalaryPerYear", String.valueOf(0.0))
                .param("vacationDays", String.valueOf(0))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.averageSalaryPerYear").value("Annual salary must be greater than zero"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.vacationDays").value("Vacation must last at least one day"));
    }

    @Test
    @DisplayName("Test returns Bad Request for employee with invalid start vacation date")
    void testInvalidStartVacationDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(VACATION_PAY_API)
                .param("averageSalaryPerYear", String.valueOf(TEST_AVERAGE_SALARY))
                .param("vacationDays", String.valueOf(TEST_VACATION_DAYS))
                .param("startVacationDate", "invalid")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors.startVacationDate").value("Invalid date format. Expected: yyyy-MM-dd"));
    }

    @Test
    @DisplayName("Test return OK for correct request without start vacation date")
    void testWithoutStartVacationDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(VACATION_PAY_API)
                .param("averageSalaryPerYear", String.valueOf(TEST_AVERAGE_SALARY))
                .param("vacationDays", String.valueOf(TEST_VACATION_DAYS))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vacationPaymentAmount").value("44539.4"));
    }

    @Test
    @DisplayName("Test returns OK for correct request with startVacationDate")
    void testWithStartVacationDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(VACATION_PAY_API)
                .param("averageSalaryPerYear", String.valueOf(TEST_AVERAGE_SALARY))
                .param("vacationDays", String.valueOf(TEST_VACATION_DAYS))
                .param("startVacationDate", TEST_START_VACATION_DATE)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vacationPaymentAmount").value("26723.64"));
    }
}
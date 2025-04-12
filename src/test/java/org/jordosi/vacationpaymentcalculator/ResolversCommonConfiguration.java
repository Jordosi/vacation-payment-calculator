package org.jordosi.vacationpaymentcalculator;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = VacationPaymentCalculatorApplication.class)
@AutoConfigureMockMvc
public class ResolversCommonConfiguration {

    protected static final String VACATION_PAY_API = "/calculate";

    protected static final BigDecimal TEST_AVERAGE_SALARY = new BigDecimal("600000.00");

    protected static final Integer TEST_VACATION_DAYS = 30;

    protected static final String TEST_START_VACATION_DATE = "2025-04-12";

    @Autowired
    protected MockMvc mockMvc;
}

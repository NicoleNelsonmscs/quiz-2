package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContractAdministratorTest {
    ContractAdministrator contractAdmin;

    @BeforeEach
    void setUp() {
        this.contractAdmin = new ContractAdministrator(104, "Fred", "Baker", "fred.baker", LocalDateTime.of(1987,12,14,0,0,0,0), "444-44-4444", "444-444-4444", LocalDateTime.of(2020,4,10,0,0,0,0), 400.0);
    }

    @Test
    void toStringTest() {
        String result = "104 Baker, Fred, birthDate=1987/12/14, ssn='444-44-4444', phone='444-444-4444', employmentStartDate=2020/04/10, monthlyRate=400.00, grossPay=400.00";
        assertEquals(result,this.contractAdmin.toString());
    }

    @Test
    void calcGrossPay() {
        assertEquals(400.00,this.contractAdmin.calcGrossPay());
    }
}
package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TenantTest {
    Tenant tenant = null;

    @BeforeEach
    void setUp() {
        this.tenant = new Tenant(102, "Nicki", "Lindstrom", "nicki.lindstrom",
                LocalDateTime.of(1988,10,30,0,0,0,0), "111-11-1111", "111-111-1111", "Minnesota State College Southeast",
                "Student", 2222.22, LocalDateTime.of(2013,10,30,0,0,0,0));
    }

    @Test
    void toStringTest() {
        String result = "102 Lindstrom, Nicki, birthDate=1988/10/30, ssn='111-11-1111', phone='111-111-1111', employer='Minnesota State College Southeast', occupation='Student', grossPay=2222.22, employmentStartDate=2013/10/30";
        assertEquals(result,this.tenant.toString());
    }
}
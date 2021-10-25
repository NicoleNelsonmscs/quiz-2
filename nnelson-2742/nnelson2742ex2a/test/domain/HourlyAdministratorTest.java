package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HourlyAdministratorTest {
    HourlyAdministrator hourlyAdmin;

    @BeforeEach
    void setUp() {
        this.hourlyAdmin = new HourlyAdministrator(105, "Mark", "Kalis", "mark.kalis", LocalDateTime.of(1958,6,8,0,0,0,0), "555-55-5555", "555-555-5555", LocalDateTime.of(1980,8,1,0,0,0), 50.00);
    }

    @Test
    void addTimeCard() {
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0), LocalDateTime.of(2018, 10, 22, 18, 0));
        this.hourlyAdmin.getTimeCard(0);
        String strTimeCard = this.hourlyAdmin.getTimeCard(0).toString();
        int i = strTimeCard.indexOf("startDateTime");
        strTimeCard = strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 06:00PM, hours=10.00";
        assertEquals(result,strTimeCard);
    }

    @Test
    void removeTimeCard() {
        this.hourlyAdmin.removeTimeCard(0);
        assertEquals(null,this.hourlyAdmin.getTimeCard(0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0), LocalDateTime.of(2018, 10, 22, 18, 0));
        String strTimeCard = this.hourlyAdmin.removeTimeCard(0).copy().toString();
        int i = strTimeCard.indexOf("startDateTime");
        strTimeCard = strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 06:00PM, hours=10.00";
        assertEquals(result,strTimeCard);
        this.hourlyAdmin.removeTimeCard(0);
        this.hourlyAdmin.getTimeCards().size();
        assertEquals(0,this.hourlyAdmin.getTimeCards().size());
    }

    @Test
    void getTimeCard() {
        this.hourlyAdmin.getTimeCard(0);
        assertEquals(null,this.hourlyAdmin.getTimeCard(0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0), LocalDateTime.of(2018, 10, 22, 18, 0));
        this.hourlyAdmin.getTimeCard(0);
        String strTimeCard = this.hourlyAdmin.getTimeCard(0).toString();
        int i = strTimeCard.indexOf("startDateTime");
        strTimeCard = strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 06:00PM, hours=10.00";
        assertEquals(result,strTimeCard);
    }

    @Test
    void getTimeCards() {

        ArrayList<TimeCard> timeCards = new ArrayList<TimeCard>();

        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0), LocalDateTime.of(2018, 10, 22, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 23, 8, 0), LocalDateTime.of(2018, 10, 23, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 24, 8, 0), LocalDateTime.of(2018, 10, 24, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 25, 8, 0), LocalDateTime.of(2018, 10, 25, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 26, 8, 0), LocalDateTime.of(2018, 10, 26, 18, 0));
        this.hourlyAdmin.getTimeCards();
        for (int i=0; i < timeCards.size(); i++){
            TimeCard timeCard1 = timeCards.get(i);
            TimeCard timeCard2 = hourlyAdmin.getTimeCards().get(i);
            assertEquals(timeCard1,timeCard2);
            assertFalse(timeCard1==timeCard2);
        }
    }

    @Test
    void testToString() {
        String result = "105 Kalis, Mark, birthDate=1958/06/08, ssn='555-55-5555', phone='555-555-5555', employmentStartDate=1980/08/01, hourlyRate=50.00, totalHours=0.00, grossPay=0.00";
        assertEquals(result,this.hourlyAdmin.toString());
    }

    @Test
    void calcTotalHours() {
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 23, 8, 0),
                LocalDateTime.of(2018, 10, 23, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 24, 8, 0),
                LocalDateTime.of(2018, 10, 24, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 25, 8, 0),
                LocalDateTime.of(2018, 10, 25, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 26, 8, 0),
                LocalDateTime.of(2018, 10, 26, 18, 0));
        assertEquals(50,this.hourlyAdmin.calcTotalHours());
    }

    @Test
    void calcGrossPay() {
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 23, 8, 0),
                LocalDateTime.of(2018, 10, 23, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 24, 8, 0),
                LocalDateTime.of(2018, 10, 24, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 25, 8, 0),
                LocalDateTime.of(2018, 10, 25, 18, 0));
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 26, 8, 0),
                LocalDateTime.of(2018, 10, 26, 18, 0));
        assertEquals(2500.00,this.hourlyAdmin.calcGrossPay());
    }
}
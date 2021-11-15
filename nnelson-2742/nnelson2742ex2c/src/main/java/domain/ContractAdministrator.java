package domain;

import java.time.LocalDateTime;

public class ContractAdministrator extends Administrator{
    private double monthlyRate = 0.00;

    public ContractAdministrator(int personId, String lastName, String firstName, String userName,
                         LocalDateTime birthDate, String ssn, String phone,
                         LocalDateTime employmentStartDate,double monthlyRate) {
        super(personId, lastName, firstName, userName, birthDate, ssn, phone, employmentStartDate);
        this.monthlyRate = monthlyRate;
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", monthlyRate=" + String.format("%.2f",monthlyRate) +
                ", grossPay=" + String.format("%.2f",calcGrossPay()) ;
    }

    private double getMonthlyRate() {
        return monthlyRate;
    }

    private void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    @Override
    public double calcGrossPay(){
        return this.monthlyRate;
    }
}

package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Administrator extends Person{
    private LocalDateTime birthDate;
    private String ssn;
    private String phone;
    private LocalDateTime employmentStartDate;

    public Administrator(int personId, String lastName, String firstName, String userName,
                  LocalDateTime birthDate, String ssn, String phone,
                  LocalDateTime employmentStartDate) {
        super(personId, lastName, firstName, userName);
        this.birthDate = birthDate;
        this.ssn = ssn;
        this.phone = phone;
        this.employmentStartDate = employmentStartDate;
    }

    private LocalDateTime getBirthDate() {
        return birthDate;
    }

    private void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    private String getSsn() {
        return ssn;
    }

    private void setSsn(String ssn) {
        this.ssn = ssn;
    }

    private String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private LocalDateTime getEmploymentStartDate() {
        return employmentStartDate;
    }

    private void setEmploymentStartDate(LocalDateTime employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    public abstract double calcGrossPay();

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return  super.toString() +
                ", birthDate=" + birthDate.format(formatter) +
                ", ssn='" + ssn + '\'' +
                ", phone='" + phone + '\'' +
                ", employmentStartDate=" + employmentStartDate.format(formatter);
    }
}

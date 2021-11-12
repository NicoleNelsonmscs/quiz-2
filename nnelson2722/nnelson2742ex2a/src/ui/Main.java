package ui;

import domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(101, "Nicole", "Nelson", "nicole.nelson");
       // System.out.println(person);
        Tenant tenant = new Tenant(102, "Nicki", "Lindstrom", "nicki.lindstrom",
                LocalDateTime.now(), "111-11-1111", "111-111-1111", "Minnesota State College Southeast",
                "Student", 2222.22, LocalDateTime.now());
       // System.out.println(tenant);

        tenant.setUserName("BBBB.BBBBB");
        tenant.setPhone("222-222-2222");
       // System.out.println(tenant);
       // System.out.println(tenant.samp());
//
//        Administrator admin = new Administrator(103, "Perry", "Nelson", "perry.nelson",
//                LocalDateTime.now(), "222-22-2222", "222-222-2222", LocalDateTime.now());
//        System.out.println(admin);

        ContractAdministrator contractAdministrator = new ContractAdministrator(104, "Fred", "Baker", "fred.baker", LocalDateTime.now(), "444-44-4444", "444-444-4444", LocalDateTime.now(), 400.0);
        //System.out.println(contractAdministrator);

//        ArrayList<TimeCard> timeCards = new ArrayList<TimeCard>();
//
//        timeCards.add(new TimeCard(LocalDateTime.of(2018, 10, 22, 8, 0), LocalDateTime.of(2018, 10, 22, 18, 0)));
//        timeCards.add(new TimeCard(LocalDateTime.of(2018, 10, 23, 8, 0), LocalDateTime.of(2018, 10, 23, 18, 0)));
//        timeCards.add(new TimeCard(LocalDateTime.of(2018, 10, 24, 8, 0), LocalDateTime.of(2018, 10, 24, 18, 0)));
//        timeCards.add(new TimeCard(LocalDateTime.of(2018, 10, 25, 8, 0), LocalDateTime.of(2018, 10, 25, 18, 0)));
//        timeCards.add(new TimeCard(LocalDateTime.of(2018, 10, 26, 8, 0), LocalDateTime.of(2018, 10, 26, 18, 0)));
//
//        for (TimeCard timeCard : timeCards) {
//            System.out.println("\t" + timeCard);

            HourlyAdministrator hourlyAdministrator = new HourlyAdministrator(105, "Mark", "Kalis", "mark.kalis", LocalDateTime.now(), "555-55-5555", "555-555-5555", LocalDateTime.now(), 50.00);
            //System.out.println(hourlyAdministrator);

            hourlyAdministrator.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0), LocalDateTime.of(2018, 10, 22, 18, 0));
            hourlyAdministrator.addTimeCard(LocalDateTime.of(2018, 10, 23, 8, 0), LocalDateTime.of(2018, 10, 23, 18, 0));
            hourlyAdministrator.addTimeCard(LocalDateTime.of(2018, 10, 24, 8, 0), LocalDateTime.of(2018, 10, 24, 18, 0));
            hourlyAdministrator.addTimeCard(LocalDateTime.of(2018, 10, 25, 8, 0), LocalDateTime.of(2018, 10, 25, 18, 0));
            hourlyAdministrator.addTimeCard(LocalDateTime.of(2018, 10, 26, 8, 0), LocalDateTime.of(2018, 10, 26, 18, 0));

           // System.out.println(hourlyAdministrator);

            ArrayList<Person> people = new ArrayList<Person>();
            people.add(person);
            people.add(tenant);
            people.add(contractAdministrator);
            people.add(hourlyAdministrator);
            for (Person p : people){
                System.out.println(p);
            }


            ArrayList<TimeCard> timeCards = hourlyAdministrator.getTimeCards();
            for (TimeCard timeCard : timeCards) {
                System.out.println("\t" + timeCard);
            }

//            ArrayList<Administrator> administrators = new ArrayList<Administrator>();
//            administrators.add(contractAdministrator);
//            administrators.add(hourlyAdministrator);

            double totalGrossPay = 0.0;
//            for (Administrator a : administrators){
//                totalGrossPay += a.calcGrossPay();
//            }
            for (Person p: people){
                System.out.println(p);
                if (p instanceof Administrator)
                    totalGrossPay += ((Administrator) p).calcGrossPay();
            }
            System.out.println("Total payroll: " + String.format("%.2f",totalGrossPay));
        }
    }
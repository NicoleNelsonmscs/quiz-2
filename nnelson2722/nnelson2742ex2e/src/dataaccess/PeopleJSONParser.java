package dataaccess;

import domain.ContractAdministrator;
import domain.HourlyAdministrator;
import domain.Person;
import domain.Tenant;
import exceptions.PersonIllegalArgumentException;
import exceptions.TimeCardIllegalArgumentException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class PeopleJSONParser {
    private static String json = "{}";
    private static ArrayList<Exception> exceptions = new ArrayList<Exception>();
    private static ArrayList<Exception> domainExceptions = new ArrayList<Exception>();

    public static void readFile(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path))) {
            String line = "";
            StringBuilder sbJSON = new StringBuilder(400);
            while ((line = reader.readLine()) != null) {
                sbJSON.append(line + "\n");
            }
            json = sbJSON.toString();
        }
    }

    public static ArrayList<Person> getPeople() {
        ArrayList<Person> people = new ArrayList<Person>();
        JSONObject obj = new JSONObject(json);
        JSONArray jsonPeople = obj.getJSONArray("people");
        if (jsonPeople != null) {
            for (int i = 0; i < jsonPeople.length(); i++) {
                try {
                    JSONObject jsonPerson = (JSONObject) jsonPeople.get(i);
                    String subclass = jsonPerson.getString("subclass");
                    switch (subclass) {
                        case "person":
                            people.add(getPerson(jsonPerson));
                            break;
                        case "tenant":
                            people.add(getTenant(jsonPerson));
                            break;
                        case "contractAdministrator":
                            people.add(getContractAdministrator(jsonPerson));
                            break;
                        case "hourlyAdministrator":
                            people.add(getHourlyAdministrator(jsonPerson));
                            break;
                    }
                }
                catch (JSONException e) {
                    exceptions.add(e);
                }
                catch (DateTimeParseException e) {
                    exceptions.add(e);
                }
                catch (PersonIllegalArgumentException | TimeCardIllegalArgumentException e){
                    domainExceptions.add(e);
                }
                catch (IllegalArgumentException e) {
                    exceptions.add(e);
            }
            }

        }
        return people;
    }

    public static ArrayList<Exception> getExceptions() {
        return exceptions;
    }

    public static ArrayList<Exception> getDomainExceptions() {
        return domainExceptions;
    }

        public static Person getPerson (JSONObject jsonPerson){
            // JSONObject obj = new JSONObject(json);
            int personId = jsonPerson.getInt("personId");
            String lastName = jsonPerson.getString("lastName");
            String firstName = jsonPerson.getString("firstName");
            String userName = jsonPerson.getString("userName");

            Person person = new Person(personId, lastName, firstName, userName);
            return person;
        }

        public static Tenant getTenant (JSONObject jsonTenant){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            //JSONObject obj = new JSONObject(json);
            Tenant tenant = null;
            //       try {
            int personId = jsonTenant.getInt("personId");
            String lastName = jsonTenant.getString("lastName");
            String firstName = jsonTenant.getString("firstName");
            String userName = jsonTenant.getString("userName");
            String strbirthDate = jsonTenant.getString("birthDate");
            LocalDate birthDate = LocalDate.parse(strbirthDate, formatter);
            LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));
            String ssn = jsonTenant.getString("ssn");
            String phone = jsonTenant.getString("phone");
            String employer = jsonTenant.getString("employer");
            String occupation = jsonTenant.getString("occupation");
            double grossPay = jsonTenant.getDouble("grossPay");
            String stremploymentStartDate = jsonTenant.getString("employmentStartDate");
            LocalDate employmentStartDateTime = LocalDate.parse(stremploymentStartDate, formatter);
            LocalDateTime employmentStartDate = LocalDateTime.of(employmentStartDateTime, LocalTime.of(0, 0));

            tenant = new Tenant(personId, lastName, firstName, userName, birthDateTime, ssn, phone, employer, occupation, grossPay, employmentStartDate);

            return tenant;
        }

        public static ContractAdministrator getContractAdministrator (JSONObject jsonContractAdmin){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            //JSONObject obj = new JSONObject(json);
            int personId = jsonContractAdmin.getInt("personId");
            String lastName = jsonContractAdmin.getString("lastName");
            String firstName = jsonContractAdmin.getString("firstName");
            String userName = jsonContractAdmin.getString("userName");
            String strbirthDate = jsonContractAdmin.getString("birthDate");
            LocalDate birthDate = LocalDate.parse(strbirthDate, formatter);
            LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));
            String ssn = jsonContractAdmin.getString("ssn");
            String phone = jsonContractAdmin.getString("phone");
            String stremploymentStartDate = jsonContractAdmin.getString("employmentStartDate");
            LocalDate employmentStartDateTime = LocalDate.parse(stremploymentStartDate, formatter);
            LocalDateTime employmentStartDate = LocalDateTime.of(employmentStartDateTime, LocalTime.of(0, 0));
            double monthlyRate = jsonContractAdmin.getDouble("monthlyRate");

            ContractAdministrator contractAdministrator = new ContractAdministrator(personId, lastName, firstName, userName, birthDateTime, ssn, phone, employmentStartDate, monthlyRate);
            return contractAdministrator;
        }

        public static HourlyAdministrator getHourlyAdministrator (JSONObject jsonHourlyAdmin){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");
            // JSONObject obj = new JSONObject(json);
            //JSONObject jsonHourlyAdmin = obj.getJSONObject("hourlyAdministrator");
            int personId = jsonHourlyAdmin.getInt("personId");
            String lastName = jsonHourlyAdmin.getString("lastName");
            String firstName = jsonHourlyAdmin.getString("firstName");
            String userName = jsonHourlyAdmin.getString("userName");
            String strbirthDate = jsonHourlyAdmin.getString("birthDate");
            LocalDate birthDate = LocalDate.parse(strbirthDate, formatter);
            LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));
            String ssn = jsonHourlyAdmin.getString("ssn");
            String phone = jsonHourlyAdmin.getString("phone");
            String stremploymentStartDate = jsonHourlyAdmin.getString("employmentStartDate");
            LocalDate employmentStartDateTime = LocalDate.parse(stremploymentStartDate, formatter);
            LocalDateTime employmentStartDate = LocalDateTime.of(employmentStartDateTime, LocalTime.of(0, 0));
            double hourlyRate = jsonHourlyAdmin.getDouble("hourlyRate");

            HourlyAdministrator hourlyAdministrator = new HourlyAdministrator(personId, lastName, firstName, userName, birthDateTime, ssn, phone, employmentStartDate, hourlyRate);

            // Add TimeCards
            JSONArray jsonTimeCards = jsonHourlyAdmin.getJSONArray("timeCards");
            if (jsonTimeCards != null) {
                for (int i = 0; i < jsonTimeCards.length(); i++) {
                    JSONObject jsonTimeCard = (JSONObject) jsonTimeCards.get(i);
                    int id = jsonTimeCard.getInt("id");
                    LocalDateTime startDateTime = LocalDateTime.parse(jsonTimeCard.getString("startDateTime"), dtformatter);
                    LocalDateTime endDateTime = LocalDateTime.parse(jsonTimeCard.getString("endDateTime"), dtformatter);
                    hourlyAdministrator.addTimeCard(id, startDateTime, endDateTime);
                }
            }
            return hourlyAdministrator;
        }
    }

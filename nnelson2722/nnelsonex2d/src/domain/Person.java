package domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Person {
    protected int personId;
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected LocalDateTime updated;

    public Person() {
        this.personId = 0;
        this.firstName = "";
        this.lastName = "";
        this.userName = "";
        this.updated = LocalDateTime.now();
    }
    public Person(int personId, String lastName, String firstName, String userName) {
//        this.personId = personId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.userName = userName;
        this.setPersonId(personId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.updated = LocalDateTime.now();
    }

    public int getPersonId()                    { return this.personId; }
    public String getFirstName()                { return this.firstName; }
    public String getLastName()                 { return lastName; }
    public String getUserName()                 { return userName; }
    public void setUpdated()                    { this.updated = LocalDateTime.now(); }
    public LocalDateTime getUpdated()           { return updated; }

//    public String setPersonId(int personId) {
//        String errMsg = "";
//        if (personId >= 101 && personId <= 999)
//            this.personId = personId;
//        else
//            errMsg = Integer.toString(personId) + " is invalid. PersonId must be >= 101 and <= 999";
//
//        return errMsg;
//    }
    public void setPersonId(int personId) {
        if (personId >= 101 && personId <= 999)
            this.personId = personId;
        else
            throw new IllegalArgumentException(
                    Integer.toString(personId)
                    + " is invalid. PersonId must be >= 101 and <= 999");
    }

//    public String setFirstName(String firstName) {
//        String errMsg = "";
//        if (firstName != null && firstName.length() > 2 && firstName.length() <= 15)
//            this.firstName = firstName;
//        else
//            errMsg = firstName + " is invalid. First name must be > 2 and <= 15 characters";
//
//        return errMsg;
//    }

    public void setFirstName(String firstName) {
        if (firstName != null && firstName.length() > 2 && firstName.length() <= 15)
            this.firstName = firstName;
        else
            throw new IllegalArgumentException(
                    firstName +
                    " is invalid. First name must be > 2 and <= 15 characters");
    }

//    public String setLastName(String lastName) {
//        String errMsg = "";
//        if (lastName != null && lastName.length() >= 2 && lastName.length() <= 30) {
//            this.lastName = lastName;
//        }
//        else
//            errMsg = lastName + " is invalid. Last name must be between 2 and 30 characters";
//
//        return errMsg;
//    }

    public void setLastName(String lastName) {
        if (lastName != null && lastName.length() >= 2 && lastName.length() <= 30) {
            this.lastName = lastName;
        }
        else
            throw new IllegalArgumentException(
                    lastName +
                    " is invalid. Last name must be between 2 and 30 characters");
    }

//    public String setUserName(String userName){
//        String errMsg = "";
//        if (userName!= null && userName.length() >= 5 && userName.length() <= 30) {
//            switch (userName.toLowerCase()) {
//                case "admin":
//                case "administrator":
//                case "supervisor":
//                    errMsg = userName + " is invalid. Admin user names not allowed";
//                    break;
//                default:
//                    this.userName = userName;
//                    break;
//            }
//        }
//        else
//            errMsg = userName + " is invalid. User name must be between 5 and 30 characters";
//
//        return errMsg;
//    }
//
    public void setUserName(String userName){
        if (userName!= null && userName.length() >= 5 && userName.length() <= 30) {
            switch (userName.toLowerCase()) {
                case "admin":
                case "administrator":
                case "supervisor":
                    throw new IllegalArgumentException(userName + " is invalid. Admin user names not allowed");
                default:
                    this.userName = userName;
                    break;
            }
        }
        else
            throw new IllegalArgumentException(
                    userName +
                    "is invalid. User name must be between 5 and 30 characters");
    }

    @Override
    public String toString() {
//        return "Person{" +
//                "personId=" + personId +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", userName='" + userName + '\'' +
//                ", updated=" + updated +
//                '}';
        return Integer.toString(personId) +     //ex1e
                " " + lastName +
                ", " + firstName;
    }

    public String toShortString() {
        return
                personId + " " +
                lastName + ", " +
                firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}


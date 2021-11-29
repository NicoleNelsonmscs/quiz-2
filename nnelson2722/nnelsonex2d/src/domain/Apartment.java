package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Apartment {
    private int apartmentId;
    private String apartmentNum;
    private int squareFeet;
    private int bathrooms;
    private double price;
    private LocalDateTime updated;
    private Person administrator;
    private Person tenant;
    private ArrayList<Invoice> invoices = new ArrayList<>();
    private Invoice invoice;

    public Apartment() {
        this.apartmentId = 0;
        this.apartmentNum = "";
        this.squareFeet = 0;
        this.bathrooms = 0;
        this.price = 0.0;
        this.updated = LocalDateTime.now();
        this.administrator = null;
        this.tenant = null;
        this.invoice = null;
    }

//    public Apartment(int apartmentId, String location, String apartmentNum, int squareFeet, int bathrooms, double price) {
//        this.apartmentId = apartmentId;
//        this.apartmentNum = apartmentNum;
//        this.squareFeet = squareFeet;
//        this.bathrooms = bathrooms;
//        this.price = price;
//        this.updated = LocalDateTime.now();
//    }

    public int getApartmentId() {
        return apartmentId;
    }
    public String getApartmentNum() {
        return apartmentNum;
    }
    public int getSquareFeet() {
        return squareFeet;
    }
    public int getBathrooms() { return this.bathrooms; }
    public double getPrice() {
        return price;
    }
    public LocalDateTime getUpdated() {
        return updated;
    }
    public Person getAdministrator() { return administrator; }
    public Person getTenant() { return tenant; }

    public void setApartmentId(int apartmentId) {
        if (apartmentId >= 100 && apartmentId <= 199)
            this.apartmentId = apartmentId;
        else
            throw new IllegalArgumentException(Integer.toString(apartmentId) + " is invalid. ApartmentId must be >= 101 and <= 199");
    }

    public void setApartmentNum(String apartmentNum) {
        if (apartmentNum != null && apartmentNum.length() >= 1 && apartmentNum.length() <= 4)
            this.apartmentNum = apartmentNum;
        else
            throw new IllegalArgumentException( "ApartmentNum is required");
    }

    public void setSquareFeet(int squareFeet) {
        if (squareFeet >= 200 && squareFeet <= 2000)
            this.squareFeet = squareFeet;
        else
            throw new IllegalArgumentException(Integer.toString(squareFeet) + " is invalid. Square feet must be > 200 and < 2000.");
    }

    public void setBathrooms(int bathrooms) {
        if (bathrooms >= 1 && bathrooms <= 3)
            this.bathrooms = bathrooms;
        else
            throw new IllegalArgumentException( Integer.toString(bathrooms) + " is invalid. Bathrooms must be > 0 and < 4.");
    }

    public void setPrice(double price) {
        if (price > 99.99 && price < 9999.99)
            this.price = price;
        else
            throw new IllegalArgumentException( Double.toString(price) + " is invalid. Price must be > 99.99 and < 9999.99");
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public void setAdministrator(Person administrator) {
        this.administrator = administrator;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
//        return "Apartment:\n" +
//                "\tApartment Id=\t" + this.apartmentId +
//                "\n\tApartment#=\t\t" + this.apartmentNum +
//                "\n\tSquare Feet=\t" + this.squareFeet +
//                "\n\tBathrooms=\t\t" + this.bathrooms +
//                "\n\tPrice=\t\t\t" + this.price +
//                "\n\tUpdated=\t\t" + this.updated +
//                "\n";
                return this.apartmentNum + ", $" + this.price;      //1e1
    }


    public String toShortString() {
        return this.apartmentNum + ", $" + this.price;
    }

    public void addInvoice(Invoice invoice){
        this.invoices.add(invoice.copy());
    }

    public void addInvoice(int index, Invoice invoice) { this.invoices.add(index, new Invoice(invoice)); }

    public Invoice getInvoice() {
        return invoice;
    }

    public Invoice removeInvoice(int index) {
        Invoice invoice = null;
        if (index>=0 && index < this.invoices.size()){
            invoice = this.invoices.get(index).copy();
            this.invoices.remove(index);
        }
        return invoice;
    }

    public Invoice getInvoice(int index) {
        Invoice invoice = null;
        if (index < this.invoices.size())
            invoice = invoices.get(index).copy();
        return invoice;
    }
    public ArrayList<Invoice> getInvoices(){
        ArrayList<Invoice> invoices = new ArrayList<Invoice>();
        for (Invoice invoice : this.invoices){
            invoices.add(invoice.copy());
        }
        return invoices;
    }
}



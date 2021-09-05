package ui;

import domain.GDate;
import domain.Invoice;
import domain.LineItem;

public class Main {
    public static void main(String[] args){
        System.out.println("nnelson2742ex1b1: Composite Invoice Class");

        GDate date1 = new GDate(2019, 9, 1);
        GDate date2 = new GDate(2019, 9, 11);

        LineItem lineItem1 = new LineItem(1000,"2019 September rent");
        System.out.println("lineItem1 = " + lineItem1);
        LineItem lineItem2 = new LineItem(lineItem1);
        System.out.println("lineItem2 = " + lineItem2);
        LineItem lineItem3 = lineItem1.copy();
        System.out.println("lineItem3 = " + lineItem3);
        LineItem lineItem4 = new LineItem(1000,"2019 September rent");
        System.out.println("lineItem4 = " + lineItem4);

        Invoice invoice1 = new Invoice(1,date1,date2);
        System.out.println("invoice1 = " + invoice1);
        Invoice invoice2 = new Invoice(invoice1);
        System.out.println("invoice2 = " + invoice2);
        Invoice invoice3 = invoice1.copy();
        System.out.println("invoice3 = " + invoice3);
        Invoice invoice4 = new Invoice(1,date1,date2);
        System.out.println("invoice4 = " + invoice4);
    }
}

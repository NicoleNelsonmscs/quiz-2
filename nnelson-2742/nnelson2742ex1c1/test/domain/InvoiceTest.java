package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    Invoice invoice1;

    @BeforeEach
    void setUp() {
        this.invoice1 = new Invoice(1, new GDate(), new GDate());
    }

    @Test
    void copyConstructor() {
        this.invoice1.addLineItem(new LineItem(1.0, "description1"));
        this.invoice1.addLineItem(new LineItem(2.0, "description2"));
        this.invoice1.addLineItem(new LineItem(3.0, "description3"));
        Invoice invoice2 = new Invoice(this.invoice1);
        assertEquals(this.invoice1.getInvoiceId(), invoice2.getInvoiceId());
        assertEquals(this.invoice1.getStatus(), invoice2.getStatus());
        assertEquals(this.invoice1.getInvoiceDate().julianDay(), invoice2.getInvoiceDate().julianDay());
        assertEquals(this.invoice1.getDueDate().julianDay(), invoice2.getDueDate().julianDay());
//        assertNotEquals(this.invoice1.getInvoiceDate(), invoice2.getInvoiceDate());
//        assertNotEquals(this.invoice1.getDueDate(), invoice2.getDueDate());
        assertFalse(this.invoice1.getInvoiceDate() == invoice2.getInvoiceDate());
        assertFalse(this.invoice1.getDueDate() == invoice2.getDueDate());

        // ex1c: test lineItems
        ArrayList<LineItem> lineItems1 = this.invoice1.getLineItems();
        ArrayList<LineItem> lineItems2 = invoice2.getLineItems();
        assertEquals(lineItems1.size(), lineItems2.size());
        int i = 0;
        LineItem lineItem2 = null;
        for (LineItem lineItem1 : lineItems1) {
            lineItem2 = lineItems2.get(i);
            assertEquals(lineItem1.getLineItemId(), lineItem2.getLineItemId());
            assertEquals(lineItem1.getAmount(), lineItem2.getAmount());
            assertEquals(lineItem1.getDescription(), lineItem2.getDescription());
            assertFalse(lineItem1 == lineItem2);
            i++;
        }
    }

    @Test
    void copy() {
        // Ex1c: add LineItems
        this.invoice1.addLineItem(new LineItem(1.0, "description1"));
        this.invoice1.addLineItem(new LineItem(2.0, "description2"));
        this.invoice1.addLineItem(new LineItem(3.0, "description3"));

        Invoice invoice2 = this.invoice1.copy();
        assertEquals(this.invoice1.getInvoiceId(), invoice2.getInvoiceId());
        assertEquals(this.invoice1.getStatus(), invoice2.getStatus());
        assertEquals(this.invoice1.getInvoiceDate().julianDay(), invoice2.getInvoiceDate().julianDay());
        assertEquals(this.invoice1.getDueDate().julianDay(), invoice2.getDueDate().julianDay());
//        assertNotEquals(this.invoice1.getInvoiceDate(), invoice2.getInvoiceDate());
//        assertNotEquals(this.invoice1.getDueDate(), invoice2.getDueDate());
        assertFalse(this.invoice1.getInvoiceDate() == invoice2.getInvoiceDate());
        assertFalse(this.invoice1.getDueDate() == invoice2.getDueDate());

        // ex1c: test lineItems
        ArrayList<LineItem> lineItems1 = this.invoice1.getLineItems();
        ArrayList<LineItem> lineItems2 = invoice2.getLineItems();
        assertEquals(lineItems1.size(), lineItems2.size());
        int i = 0;
        LineItem lineItem2 = null;
        for (LineItem lineItem1 : lineItems1) {
            lineItem2 = lineItems2.get(i);
            assertEquals(lineItem1.getLineItemId(), lineItem2.getLineItemId());
            assertEquals(lineItem1.getAmount(), lineItem2.getAmount());
            assertEquals(lineItem1.getDescription(), lineItem2.getDescription());
            assertFalse(lineItem1 == lineItem2);
            i++;
        }
    }

    @Test
    void getLineItems() {
        this.invoice1.addLineItem(new LineItem(1.0, "description1"));
        this.invoice1.addLineItem(new LineItem(2.0, "description2"));
        this.invoice1.addLineItem(new LineItem(3.0, "description3"));
        this.invoice1.addLineItem(new LineItem(4.0, "description4"));

        ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems = this.invoice1.getLineItems();
        assertEquals(4, lineItems.size());
    }

//    @Test
//     void addLineItem() {
//    }

    @Test
    void removeLineItem() {
        LineItem lineItem1 = new LineItem(1.0, "description1");

        // test Invoice.removeLineItem(int index)
        this.invoice1.addLineItem(lineItem1);
        LineItem removedLineItem = this.invoice1.removeLineItem(0);
        assertEquals(lineItem1, removedLineItem);
        assertFalse(lineItem1 == removedLineItem);
        // test removing from empty ArrayList
        removedLineItem = this.invoice1.removeLineItem(0);
        assertEquals(null, removedLineItem);

        // test Invoice.removeLineItem(LineItem lineItem)
        this.invoice1.addLineItem(lineItem1);
        removedLineItem = this.invoice1.removeLineItem(lineItem1);
        assertEquals(lineItem1, removedLineItem);
        assertFalse(lineItem1 == removedLineItem);
        // test removing from empty ArrayList
        removedLineItem = this.invoice1.removeLineItem(lineItem1);
        assertEquals(null, removedLineItem);

}

    @Test
    void total() {
        LineItem lineItem1 = new LineItem(1.0,"item 1");
        LineItem lineItem2 = new LineItem(2.0,"item 2");
        LineItem lineItem3 = new LineItem(3.0,"item 3");
        LineItem lineItem4 = new LineItem(4.0,"item 4");

        this.invoice1.addLineItem(lineItem1);
        this.invoice1.addLineItem(lineItem2);
        this.invoice1.addLineItem(lineItem3);
        this.invoice1.addLineItem(lineItem4);

        double total = this.invoice1.total();
        assertEquals(10,total);
    }

}
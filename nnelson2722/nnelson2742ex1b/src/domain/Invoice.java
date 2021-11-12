package domain;

import java.util.ArrayList;

public class Invoice {
    private int invoiceId;
    private int status;
    private GDate invoiceDate;
    private GDate dueDate;
    private ArrayList<LineItem> lineItems;

    public Invoice(int status, GDate invoiceDate, GDate dueDate) {
        this.invoiceId = DbContext.getNextInvoiceID();
        this.status = status;
        this.invoiceDate = new GDate(invoiceDate);
        this.dueDate = new GDate(dueDate);
    }

    public Invoice(Invoice invoice) {
        this.invoiceId = invoice.invoiceId;
        this.status = invoice.status;
        this.invoiceDate = new GDate(invoice.invoiceDate);
        this.dueDate = new GDate(invoice.dueDate);
    }

    public Invoice copy() {
        Invoice invoice = new Invoice(this.status, this.invoiceDate, this.dueDate);
        invoice.invoiceId = this.invoiceId;
        return invoice;
        }

    public void addLineItem(LineItem lineItem) {
        // TODO - implement Invoice.addLineItem
        throw new UnsupportedOperationException();
    }

    public LineItem removeLineItem(int lineItemId) {
        // TODO - implement Invoice.removeLineItem
        throw new UnsupportedOperationException();
    }

    public double total() {
        // TODO - implement Invoice.total
        throw new UnsupportedOperationException();
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getStatus() {
        return status;
    }

    public GDate getInvoiceDate() {
        return invoiceDate;
    }

    public GDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", status=" + status +
                ", invoiceDate=" + invoiceDate +
                ", dueDate=" + dueDate +
                '}';
    }
}


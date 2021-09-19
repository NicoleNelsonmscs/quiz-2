package domain;

import java.util.ArrayList;

public class Invoice {
    private int invoiceId;
    private int status;
    private GDate invoiceDate;
    private GDate dueDate;
    private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();

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

        for (LineItem lineItem: invoice.lineItems){
            this.lineItems.add(lineItem.copy());
        }
    }

    public Invoice copy() {
//        Invoice invoice = new Invoice(this.status, this.invoiceDate, this.dueDate);
//        invoice.invoiceId = this.invoiceId;
//        return invoice;
        return new Invoice(this);
        }

    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    public LineItem removeLineItem(int index) {
        LineItem lineItem = null;
        if (index>=0 && index < this.lineItems.size()){
            lineItem = this.lineItems.get(index).copy();
            this.lineItems.remove(index);
        }
        return lineItem;
    }

    public LineItem removeLineItem(LineItem lineItem) {
        LineItem removedLineItem = null;
        int index = this.lineItems.indexOf(lineItem);

        if (index !=-1 ){
            removedLineItem = this.lineItems.get(index).copy();
            this.lineItems.remove(index);
        }
        return removedLineItem;
    }

    public double total() {
        double total = 0.0;
        for (int i = 0; i<lineItems.size(); i++){
            double amount = lineItems.get(i).getAmount();
            total += amount;
        }
        return total;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getStatus() {
        return status;
    }

    public GDate getInvoiceDate() {
        return invoiceDate.copy();
    }

    public GDate getDueDate() {
        return dueDate.copy();
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

    public LineItem getLineItem(int index) {
        LineItem lineItems = null;
        for (LineItem lineItem: this.lineItems){
            lineItems.add(lineItem.copy());
        }
        return lineItems;
    }


    public ArrayList<LineItem> getLineItems() {
        ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
        for (LineItem lineItem: this.lineItems){
            lineItems.add(lineItem.copy());
        }
      return lineItems;
    }
}


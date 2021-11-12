package domain;

public class LineItem {
    @Override
    public String toString() {
        return "LineItem{" +
                "lineItemId=" + lineItemId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    private int lineItemId;
    private double amount;
    private String description;

    public LineItem(double amount, String description) {
       this.lineItemId = DbContext.getNextLineItemId();
       this.amount = amount;
       this.description = description;
    }

    public LineItem(LineItem lineItem) {
        this.lineItemId = lineItem.lineItemId;
        this.amount = lineItem.amount;
        this.description = lineItem.description;
    }

    public LineItem copy() {
        return new LineItem(this);
    }

    public int getLineItemId() {
        return lineItemId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

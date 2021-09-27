package ui;

import domain.DbContext;
import domain.GDate;
import domain.Invoice;
import domain.LineItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class InvoiceController {
    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

    @FXML
    private TextField invoiceIdTextField;
    @FXML
    private TextField statusTextField;
    @FXML
    private TextField invoiceDateTextField;
    @FXML
    private TextField dueDateTextField;
    @FXML
    private ComboBox invoicesComboBox;
    @FXML
    private ListView lineItemsListView;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private Button saveInvoiceButton;
    @FXML
    private Button saveLineItemButton;
    @FXML
    private TextField totalTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;

    public InvoiceController() {
        this.invoices = DbContext.getInvoices();
    }

    private void displayInvoice(Invoice invoice){
        this.invoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
        this.statusTextField.setText(Integer.toString(invoice.getStatus()));
        this.invoiceDateTextField.setText(invoice.getInvoiceDate().toString());
        this.dueDateTextField.setText(invoice.getDueDate().toString());
    }

    @FXML
    protected void initialize() {
        for (Invoice invoice : this.invoices){
            invoicesComboBox.getItems().add(invoice.toShortString());
        }
//        Invoice invoice = this.invoices.get(0);
//        invoicesComboBox.getItems().add(invoice.toShortString());
//        invoice = this.invoices.get(1);
//        invoicesComboBox.getItems().add(invoice.toShortString());
        invoicesComboBox.getSelectionModel().selectFirst();
//        invoicesComboBox.getSelectionModel().select(1);

        Invoice invoice = this.invoices.get(0);
        this.displayInvoice(invoice);
        this.displayLineItems(invoice);
    }

    @FXML
    private void invoiceComboBoxItemSelected(ActionEvent actionEvent) {
        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();
        if (index >= 0){
        Invoice invoice = this.invoices.get(index);
        this.displayInvoice(invoice);
        this.displayLineItems(invoice);
        }
    }

    @FXML
    private void lineItemsListClicked(javafx.scene.input.MouseEvent mouseEvent) {
        int index = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        ArrayList<LineItem>lineItems = invoices.get(index).getLineItems();
        int index2 = this.lineItemsListView.getSelectionModel().getSelectedIndex();
        LineItem lineItem = lineItems.get(index2);
        displayLineItem(lineItem);
    }

    private void displayLineItems (Invoice invoice){
        // 1) clear lineItemsListView
        this.lineItemsListView.getItems().clear();
        // 2) lineItems = invoice.getLineItems()
        ArrayList<LineItem> lineItems = invoice.getLineItems();
        // 3) copy each LineItem to lineItemsListView
        for (LineItem lineItem : lineItems){
            lineItemsListView.getItems().add(lineItem.toShortString());
        }
        // 4) clear description and amount TextFields
        this.descriptionTextField.setText("");
        this.amountTextField.setText("");
        this.lineItemsListView.getSelectionModel().selectFirst();
        // 5) select first item in lineItemsListView
        // 6) if lineItems has an item, displayLineItem()
        if (lineItems.size() > 0){
            displayLineItem(lineItems.get(0));
            }
        this.totalTextField.setText(String.format("%.2f",invoice.total()));
    }

    public void displayLineItem(LineItem lineItem){
        //set TextFields using lineItem
        this.descriptionTextField.setText(lineItem.getDescription().toString());
        this.amountTextField.setText(String.format("%.2f",lineItem.getAmount()));
    }

    @FXML
    private void saveInvoiceButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
        int invoiceIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        //get selected invoice
        Invoice invoice = this.invoices.get(invoiceIndex);
        //copy from form controls to invoice
        invoice.setStatus(Integer.parseInt(this.statusTextField.getText()));
        GDate invoiceDate = new GDate (
            Integer.parseInt(this.invoiceDateTextField.getText().substring(0, 4)),
            Integer.parseInt(this.invoiceDateTextField.getText().substring(5,7)),
            Integer.parseInt(this.invoiceDateTextField.getText().substring(8,10))
        );
        invoice.setInvoiceDate(invoiceDate);
         GDate dueDate = new GDate(
                 Integer.parseInt(this.dueDateTextField.getText().substring(0, 4)),
                 Integer.parseInt(this.dueDateTextField.getText().substring(5,7)),
                 Integer.parseInt(this.dueDateTextField.getText().substring(8,10))
         );
         invoice.setDueDate(dueDate);
        //remove selected item from invoiceComboBox
        this.invoicesComboBox.getItems().remove(invoiceIndex);
        // add invoice.toShortString to invoiceComboBox
        this.invoicesComboBox.getItems().add(invoiceIndex,invoice.toShortString());
        //select item in invoiceComboBox
        this.invoicesComboBox.getSelectionModel().select(invoiceIndex);
    }

    @FXML
    private void saveLineItemButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
        int invoiceIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        //get selected invoice
        Invoice invoice = this.invoices.get(invoiceIndex);
        //get index of selected invoice
        int lineItemIndex = lineItemsListView.getSelectionModel().getSelectedIndex();
        //remove selected lineItem from invoice
        invoice.removeLineItem(lineItemIndex);
        //create new LineItem
        LineItem lineItem = new LineItem(Double.parseDouble
                (this.amountTextField.getText()),
                this.descriptionTextField.getText().trim());
        //add LineItem to invoice
        invoice.addLineItem(lineItemIndex, lineItem);
        //remove selected LineItem from ListView
        this.lineItemsListView.getItems().remove(lineItemIndex);
        //add new item to Listview
        this.lineItemsListView.getItems().add(lineItemIndex, lineItem.toShortString());
        this.totalTextField.setText(String.format("%.2f",invoice.total()));
        //select current LineItem in Listview
        this.lineItemsListView.getSelectionModel().select(lineItemIndex);
    }
    @FXML
    private void addButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
        int invoiceIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        //get selected invoice
        Invoice invoice = this.invoices.get(invoiceIndex);
        //create new LineItem
        LineItem lineItem = new LineItem(0.00,"");
        //add LineItem to invoice
        invoice.addLineItem(lineItem);
        //add new item to Listview
        this.lineItemsListView.getItems().add(lineItem.toShortString());
        //select last LineItem in Listview
        this.lineItemsListView.getSelectionModel().selectLast();
        //display LineItem()
        displayLineItem(lineItem);
        //set focus to description
        this.descriptionTextField.requestFocus();
        this.totalTextField.setText(String.format("%.2f",invoice.total()));
    }

    @FXML
    private void deleteButtonClicked(ActionEvent actionEvent) {
        // clear description and amount TextFields
        this.descriptionTextField.setText("");
        this.amountTextField.setText("");
        // get index of selected invoice
        int invoiceIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        //get selected invoice
        // if lineItems is >= 0
        // remove line item from invoice
        // remove item from ListView
        // select last item in listview
        // scroll to last item in listview
        // get last item in invoice
        // displayLineItem
        if (invoiceIndex >=0) {
            Invoice invoice = this.invoices.get(invoiceIndex);
            int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();

            if (lineItemIndex >= 0) {
                invoice.removeLineItem(lineItemIndex);
                this.lineItemsListView.getItems().remove(lineItemIndex);
                this.lineItemsListView.getSelectionModel().selectLast();
                lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();
                if (lineItemIndex >= 0) {
                    this.lineItemsListView.scrollTo(lineItemIndex);
                    LineItem lineItem = invoice.getLineItem(lineItemIndex);
                    displayLineItem(lineItem);
                }
                this.totalTextField.setText(String.format("%.2f", invoice.total()));
            }
        }
    }
}

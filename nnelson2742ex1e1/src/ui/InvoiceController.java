package ui;

import domain.Invoice;
import domain.LineItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
//    @FXML
//    private ComboBox invoicesComboBox;
    @FXML
    private ComboBox<Invoice> invoicesComboBox;     //1e1
    @FXML
//    private ListView lineItemsListView;
    private  ListView<LineItem> lineItemsListView;      //1e1
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

    //public InvoiceController() {
    //    this.invoices = DbContext.getInvoices();
    //}

    public InvoiceController() { }

    public void initData (ArrayList<Invoice> invoices){
        this.invoices = invoices;
    }

    private void displayInvoice(Invoice invoice){
        this.invoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
        this.statusTextField.setText(Integer.toString(invoice.getStatus()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.invoiceDateTextField.setText(invoice.getInvoiceDate().format(formatter));
        this.dueDateTextField.setText(invoice.getDueDate().format(formatter));
    }

    @FXML
    protected void initialize() {
        if (this.invoices.size() > 0){
        for (Invoice invoice : this.invoices){
//            invoicesComboBox.getItems().add(invoice.toShortString());
            invoicesComboBox.getItems().add(invoice);       //1e1
        }
        this.invoices = null;                       //1e1
//        Invoice invoice = this.invoices.get(0);
//        invoicesComboBox.getItems().add(invoice.toShortString());
//        invoice = this.invoices.get(1);
//        invoicesComboBox.getItems().add(invoice.toShortString());
        invoicesComboBox.getSelectionModel().selectFirst();
//        invoicesComboBox.getSelectionModel().select(1);
//            Invoice invoice = this.invoices.get(0);
            Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();      //1e1
            this.displayInvoice(invoice);
            this.displayLineItems(invoice);
        }
    }

    @FXML
    private void invoiceComboBoxItemSelected(ActionEvent actionEvent) {
 //       int index = invoicesComboBox.getSelectionModel().getSelectedIndex();
 //       if (index >= 0){
 //       Invoice invoice = this.invoices.get(index);
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();  //1e1
        if (invoice != null){               //1e1
        this.displayInvoice(invoice);
        this.displayLineItems(invoice);
        }
    }

    @FXML
    private void lineItemsListClicked(javafx.scene.input.MouseEvent mouseEvent) {
//        int index = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
//        ArrayList<LineItem>lineItems = invoices.get(index).getLineItems();
//        int index2 = this.lineItemsListView.getSelectionModel().getSelectedIndex();
//        LineItem lineItem = lineItems.get(index2);
        ArrayList<LineItem> lineItems = this.invoicesComboBox.getSelectionModel().getSelectedItem().getLineItems();

//        int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();
//        LineItem lineItem = lineItems.get(lineItemIndex);
        LineItem lineItem = this.lineItemsListView.getSelectionModel().getSelectedItem();
        if (lineItem != null){
        displayLineItem(lineItem);
        }
    }

    private void displayLineItems (Invoice invoice){
        // 1) clear lineItemsListView
        this.lineItemsListView.getItems().clear();
        // 2) lineItems = invoice.getLineItems()
        ArrayList<LineItem> lineItems = invoice.getLineItems();
        // 3) copy each LineItem to lineItemsListView
        for (LineItem lineItem : lineItems){
//            lineItemsListView.getItems().add(lineItem.toShortString());
            lineItemsListView.getItems().add(lineItem);     //1e1
        }
        // 4) clear description and amount TextFields
        this.descriptionTextField.setText("");
        this.amountTextField.setText("");
        this.lineItemsListView.getSelectionModel().selectFirst();
        // 5) select first item in lineItemsListView
        // 6) if lineItems has an item, displayLineItem()
//        if (lineItems.size() > 0){
        LineItem lineItem = lineItemsListView.getSelectionModel().getSelectedItem();
        if (lineItem != null){
            displayLineItem(lineItem);
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
//        int invoiceIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        //get selected invoice
//        Invoice invoice = this.invoices.get(invoiceIndex);
        Invoice invoice = invoicesComboBox.getSelectionModel().getSelectedItem();       //1e1
        //copy from form controls to invoice
        invoice.setStatus(Integer.parseInt(this.statusTextField.getText()));
        //GDate invoiceDate = new GDate (
        //    Integer.parseInt(this.invoiceDateTextField.getText().substring(0, 4)),
        //    Integer.parseInt(this.invoiceDateTextField.getText().substring(5,7)),
        //    Integer.parseInt(this.invoiceDateTextField.getText().substring(8,10))
        //);
        //invoice.setInvoiceDate(invoiceDate);
        //GDate dueDate = new GDate(
        //         Integer.parseInt(this.dueDateTextField.getText().substring(0, 4)),
        //         Integer.parseInt(this.dueDateTextField.getText().substring(5,7)),
        //         Integer.parseInt(this.dueDateTextField.getText().substring(8,10))
        // );
        // invoice.setDueDate(dueDate);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date;
        date = LocalDate.parse(this.invoiceDateTextField.getText(),formatter);
        invoice.setInvoiceDate(date.atStartOfDay());
        //invoice.setInvoiceDate(LocalDateTime.parse(this.invoiceDateTextField.getText(),formatter));
        date = LocalDate.parse(this.dueDateTextField.getText(),formatter);
        invoice.setDueDate(date.atStartOfDay());
        //invoice.setDueDate(LocalDateTime.parse(this.dueDateTextField.getText(),formatter));

        //remove selected item from invoiceComboBox
        int invoiceIndex = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        this.invoicesComboBox.getItems().remove(invoiceIndex);
        // add invoice.toShortString to invoiceComboBox
       // this.invoicesComboBox.getItems().add(invoiceIndex,invoice.toShortString());
        this.invoicesComboBox.getItems().add(invoiceIndex,invoice);     //1e1
        //select item in invoiceComboBox
        this.invoicesComboBox.getSelectionModel().select(invoiceIndex);
    }

    @FXML
    private void saveLineItemButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
//        int invoiceIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        //get selected invoice
//        Invoice invoice = this.invoices.get(invoiceIndex);
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();      //1e1
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
 //       this.lineItemsListView.getItems().add(lineItemIndex, lineItem.toShortString());
        this.lineItemsListView.getItems().add(lineItemIndex, lineItem);     //1e1
        this.totalTextField.setText(String.format("%.2f",invoice.total()));
        //select current LineItem in Listview
        this.lineItemsListView.getSelectionModel().select(lineItemIndex);
    }
    @FXML
    private void addButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
//        int invoiceIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        //get selected invoice
//        Invoice invoice = this.invoices.get(invoiceIndex);
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();  //1e1
        //create new LineItem
        LineItem lineItem = new LineItem(0.00,"");
        //add LineItem to invoice
        invoice.addLineItem(lineItem);
        //add new item to Listview
 //       this.lineItemsListView.getItems().add(lineItem.toShortString());
        this.lineItemsListView.getItems().add(lineItem);        //1e1
        //select last LineItem in Listview
        this.lineItemsListView.getSelectionModel().selectLast();
        this.lineItemsListView.scrollTo(lineItem);
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
 //       int invoiceIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        //get selected invoice
        // if lineItems is >= 0
        // remove line item from invoice
        // remove item from ListView
        // select last item in listview
        // scroll to last item in listview
        // get last item in invoice
        // displayLineItem
 //       if (invoiceIndex >=0) {
 //           Invoice invoice = this.invoices.get(invoiceIndex);
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();      //1e1
        if (invoice != null){
            int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();

            if (lineItemIndex >= 0) {
                invoice.removeLineItem(lineItemIndex);
                this.lineItemsListView.getItems().remove(lineItemIndex);
                this.lineItemsListView.getSelectionModel().selectLast();
 //               lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();
 //               if (lineItemIndex >= 0) {
//                    this.lineItemsListView.scrollTo(lineItemIndex);
//                    LineItem lineItem = invoice.getLineItem(lineItemIndex);
//                    displayLineItem(lineItem);
                LineItem lineItem = this.lineItemsListView.getSelectionModel().getSelectedItem();
                if (lineItem != null){
                    this.lineItemsListView.scrollTo(lineItem);
                    displayLineItem(lineItem);
                }
                this.totalTextField.setText(String.format("%.2f", invoice.total()));
            }
        }
    }
}

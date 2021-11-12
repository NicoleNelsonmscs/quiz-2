package ui;

import domain.Apartment;
import domain.DbContext;
import domain.Invoice;
import domain.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ApartmentController {
    private ArrayList<Apartment> apartments = new ArrayList<Apartment>();
    private ArrayList<Person> people = new ArrayList<Person>();

    @FXML
    private ComboBox aptComboBox;
    @FXML
    private TextField aptNumTextField;
    @FXML
    private TextField squareFeetTextField;
    @FXML
    private TextField bathroomsTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField updatedTextField;
    @FXML
    private ComboBox adminComboBox;
    @FXML
    private ComboBox tenantComboBox;
    @FXML
    private Button saveAptButton;
    @FXML
    private Button viewInvoiceDlgButton;

    public ApartmentController() {
        this.apartments = DbContext.getApartments();
        this.people = DbContext.getPeople();
    }

    @FXML
    protected void initialize() {
        for (Apartment apartment : this.apartments){
            aptComboBox.getItems().add(apartment.toShortString());
        }
        aptComboBox.getSelectionModel().selectFirst();
        Apartment apt = this.apartments.get(0);

        for (Person person : this.people){
            adminComboBox.getItems().add(person.toShortString());
        }
        for (Person person : this.people){
            tenantComboBox.getItems().add(person.toShortString());
        }
        this.displayApartment(apt);
    }

    public void displayApartment(Apartment apartment){
        //set TextFields using apartment
        this.aptNumTextField.setText(apartment.getApartmentNum());
        this.squareFeetTextField.setText(Integer.toString(apartment.getSquareFeet()));
        this.bathroomsTextField.setText(Integer.toString(apartment.getBathrooms()));
        this.priceTextField.setText(String.format("%.2f",apartment.getPrice()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.updatedTextField.setText(apartment.getUpdated().format(formatter));

        int selectedIndex = -1;
        for (int i = 0; i < this.people.size(); i++){
            Person person = this.people.get(i);
            if (person.equals(apartment.getAdministrator())){
                selectedIndex = i;
                break;
            }
        }
        this.adminComboBox.getSelectionModel().select(selectedIndex);

        selectedIndex = -1;
        for (int i = 0; i < this.people.size(); i++){
            Person person = this.people.get(i);
            if (person.equals(apartment.getTenant())){
                selectedIndex = i;
                break;
            }
        }
        this.tenantComboBox.getSelectionModel().select(selectedIndex);

    }

    @FXML
    private void aptComboBox_ItemSelected(ActionEvent actionEvent) {
        int selectedIndex = aptComboBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            displayApartment(apartments.get(selectedIndex));
        }
    }

    @FXML
    private void saveAptButtonClicked(ActionEvent actionEvent) {
        int aptIndex = this.aptComboBox.getSelectionModel().getSelectedIndex();
        Apartment apartment = this.apartments.get(aptIndex);
        apartment.setApartmentNum(this.aptNumTextField.getText());
        apartment.setSquareFeet(Integer.parseInt(this.squareFeetTextField.getText()));
        apartment.setBathrooms(Integer.parseInt(this.bathroomsTextField.getText()));
        apartment.setPrice(Double.parseDouble(this.priceTextField.getText()));

        apartment.setUpdated(LocalDateTime.now());

        int adminIndex = this.adminComboBox.getSelectionModel().getSelectedIndex();
        Person admin = this.people.get(adminIndex);
        apartment.setAdministrator(admin);

        int tenantIndex = this.tenantComboBox.getSelectionModel().getSelectedIndex();
        Person tenant = this.people.get(tenantIndex);
        apartment.setTenant(tenant);

        this.aptComboBox.getItems().remove(aptIndex);
        this.aptComboBox.getItems().add(aptIndex, apartment.toShortString());
        this.aptComboBox.getSelectionModel().select(aptIndex);

    }

    @FXML
    private void viewInvoiceDlgButtonClicked(ActionEvent actionEvent) {
        int selectedIndex = aptComboBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Apartment apartment = apartments.get(selectedIndex);
            ArrayList<Invoice> invoices = apartment.getInvoices();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InvoiceView.fxml"));
                InvoiceController invoiceController = new InvoiceController();
                invoiceController.initData(invoices);
                fxmlLoader.setController(invoiceController);
                //Parent root1 = fxmlLoader.load();
                Pane pane = (Pane) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Invoice");
                stage.setScene(new Scene(pane,555, 500));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
